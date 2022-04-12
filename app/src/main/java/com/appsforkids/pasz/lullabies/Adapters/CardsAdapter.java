package com.appsforkids.pasz.lullabies.Adapters;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.Card;
import com.appsforkids.pasz.lullabies.FlipAnimation;
import com.appsforkids.pasz.lullabies.MyInterfaces.GetActionFromAdapter;
import com.appsforkids.pasz.lullabies.R;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>{

    ArrayList<Card> cards;
    int heightOfView;
    FlipAnimation flipAnimation;
    //TextView coins;
    GetActionFromAdapter getActionFromAdapter;

    public CardsAdapter(Context ctx, ArrayList<Card> cards, int heightOfView, GetActionFromAdapter getActionFromAdapter){
        this.cards = cards;
        this.heightOfView = heightOfView;
        this.getActionFromAdapter = getActionFromAdapter;
        flipAnimation = new FlipAnimation(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        //renewPoint(true, view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image_card.setImageResource(cards.get(position).getImage());
        holder.frame_card.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightOfView));
        holder.frame_card.setClickable(false);

        new CountDownTimer(2000, 2000) {
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
            holder.flipCard();
              //  holder.itemView.setClickable(true);
            }
        }.start();

        holder.frame_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActionFromAdapter.usePosition(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frame_card;
        ImageView image_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frame_card = itemView.findViewById(R.id.frame_card);
            image_card = itemView.findViewById(R.id.image_card);
        }

        public void flipCard(){
            Animation animation1 = AnimationUtils.loadAnimation(frame_card.getContext(), R.anim.flip_to_middle);
            Animation animation2 = AnimationUtils.loadAnimation(frame_card.getContext(), R.anim.flip_from_middle);

            animation1.setDuration(500);
            animation2.setDuration(500);

            animation1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                        image_card.setImageResource(R.drawable.g_hidden_bear);
                        //image_card.setImageResource(cards.get(getAdapterPosition()).getImage());
                    frame_card.setAnimation(animation2);
                    animation2.start();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            frame_card.startAnimation(animation1);
        }
    }


}
