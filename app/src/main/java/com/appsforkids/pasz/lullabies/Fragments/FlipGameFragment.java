package com.appsforkids.pasz.lullabies.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.Card;
import com.appsforkids.pasz.lullabies.Adapters.CardsAdapter;
import com.appsforkids.pasz.lullabies.MyInterfaces.GetActionFromAdapter;
import com.appsforkids.pasz.lullabies.MyInterfaces.DoThisAction;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class FlipGameFragment extends Fragment implements View.OnClickListener {

    RecyclerView rv_cards;
    ImageView home_button;
    int heightOfView;
    GridLayoutManager gm;
    ArrayList<Card> cardArrayList;
    int oldPosition = -1;
    FrameLayout lockFrame;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flip_game_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_cards = view.findViewById(R.id.rv_cards);
        lockFrame = view.findViewById(R.id.lockFrame);
        home_button = view.findViewById(R.id.home_button);
        rv_cards.setHasFixedSize(true);
        gm = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
        rv_cards.setLayoutManager(gm);
        home_button.setOnClickListener(this::onClick);
        cardArrayList = new MyObjects(getContext()).getCards();

        rv_cards.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                heightOfView = rv_cards.getHeight() / 4;
                CardsAdapter cardsAdapter = new CardsAdapter(getContext(), cardArrayList, heightOfView, new GetActionFromAdapter() {
                    @Override
                    public void usePosition(int position) {

                        if (position == oldPosition) {

                        } else {
                            if (oldPosition == -1) {
                                flipCard(position, true);
                            } else {
                                lockFrame.setClickable(true);
                                flipCard(position, true);
                                flipTwoCards(position, oldPosition);
                            }
                            oldPosition = position;

                        }
                        Log.i("Flip", oldPosition + " old and new " + position);
                    }

                    @Override
                    public void doAnother(int position) {

                    }
                });
                rv_cards.setAdapter(cardsAdapter);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.home_button:
                getParentFragmentManager().beginTransaction().add(R.id.main, MessageFragment.init(getResources().getString(R.string.are_u_sure), new DoThisAction() {
                    @Override
                    public void doThis() {
                        getParentFragmentManager().beginTransaction().setCustomAnimations(R.animator.appearance , R.animator.disappearance).replace(R.id.main, new MainFragment()).commit();
                    }
                })).commit();
                break;
        }
    }

    public void flipCard(int flipPosition, boolean open){
        Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.flip_to_middle);
        Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.flip_from_middle);

        animation1.setDuration(500);
        animation2.setDuration(500);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                ImageView image_card = gm.findViewByPosition(flipPosition).findViewById(R.id.image_card);
                if(open){
                    image_card.setImageResource(cardArrayList.get(flipPosition).getImage());

                }else {
                    image_card.setImageResource(R.drawable.g_hidden_bear);
                }
                gm.findViewByPosition(flipPosition).setAnimation(animation2);
                animation2.start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        Log.i("Flip", "Позиция "+flipPosition);
        gm.findViewByPosition(flipPosition).startAnimation(animation1);
    }

    public void flipTwoCards(int currentPosition, int currentOldPosition){
        new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long l) {

            }
            @Override
            public void onFinish() {
                if(cardArrayList.get(currentPosition).getName()==cardArrayList.get(currentOldPosition).getName()){

                    gm.findViewByPosition(currentOldPosition).setClickable(false);
                    gm.findViewByPosition(currentPosition).setClickable(false);

                    ImageView image = (ImageView) gm.findViewByPosition(currentOldPosition).findViewById(R.id.image_card_bg);
                    image.setImageResource(0);

                    ImageView image2 = (ImageView) gm.findViewByPosition(currentPosition).findViewById(R.id.image_card_bg);
                    image2.setImageResource(0);


                }else{
                    flipCard(currentOldPosition, false);
                    flipCard(currentPosition, false);

                }
                oldPosition=-1;
                lockFrame.setClickable(false);
            }
        }.start();
    }
}
