package com.appsforkids.pasz.lullabies.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;
import com.appsforkids.pasz.lullabies.R;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.FadeOutAnimation;
import com.easyandroidanimations.library.ShakeAnimation;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class UnlockFragment extends Fragment {

    @BindView(R.id.no_button)
    FrameLayout close;

    @BindView(R.id.save_button)
    FrameLayout buy;

    @BindView(R.id.cost)
    TextView cost_txt;

    @BindView(R.id.set_img)
    ImageView set_img;

    @BindView(R.id.set_shadow)
    ImageView set_shadow_txt;

    @BindView(R.id.linear_for_adds)
    LinearLayout linear_for_adds;



    public static UnlockFragment init(int currentNightlight, boolean addLoadedStatus){
        UnlockFragment unlockFragment = new UnlockFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("currentNightlight", currentNightlight);
        bundle.putBoolean("addLoadedStatus", addLoadedStatus);
        unlockFragment.setArguments(bundle);
        return unlockFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unlock_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // startAdds();

        Nightlight currentNightlight = getNightlight(getArguments().getInt("currentNightlight"));
        if (currentNightlight != null) {
            set_img.setImageResource(currentNightlight.nightlight);
            set_shadow_txt.setImageResource(currentNightlight.sh_nightlight);
            cost_txt.setText(currentNightlight.getCost() + " ");
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getParentFragmentManager();
                fm.beginTransaction().remove(UnlockFragment.this).commit();
            }
        });

        buy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        scaleView(buy, 1f, 0.95f);
                        buyNightlight(currentNightlight);
                        break;
                    case MotionEvent.ACTION_UP:
                        scaleView(buy, 0.95f, 1f);
                        break;
                }
                return true;
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // buyNightlight(currentNightlight);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FadeOutAnimation(UnlockFragment.this.getView()).setDuration(200).setListener(new AnimationListener() {
                    @Override
                    public void onAnimationEnd(com.easyandroidanimations.library.Animation animation) {
                        FragmentManager fm = getParentFragmentManager();
                        fm.beginTransaction().remove(UnlockFragment.this).commit();
                    }
                }).animate();
            }
        });

        //show linear layout if nightlight cost enoyght for buing linear_for_adds
        if(chekCost(currentNightlight) && getArguments().getBoolean("addLoadedStatus")){
            linear_for_adds.setVisibility(View.VISIBLE);
            Log.i("unlock", "status "+chekCost(currentNightlight));
            linear_for_adds.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showAds();

                    MainFragment mainFragment = (MainFragment)  getParentFragmentManager().findFragmentByTag("MainFragment");
                    mainFragment.showAddsForOneNightlight();
                    getParentFragmentManager().beginTransaction().remove(UnlockFragment.this).commit();

                   // ((MainActivity)getActivity()).startAnimation(myObjects.getAnimationImage()[currentAnimation]);
                }
            });

        }else{
            Log.i("unlock", "status "+chekCost(currentNightlight));
        }
    }

    //Animation.RELATIVE_TO_SELF
    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale, startScale, endScale, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(10);
        v.startAnimation(anim);
    }

    private Nightlight getNightlight(int position){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Nightlight> nightlightArrayList = realm.where(Nightlight.class).findAll();
        realm.commitTransaction();
        return nightlightArrayList.get(position);
    }

    private void buyNightlight(Nightlight nightlight){

        final Runnable r = new Runnable() {
            public void run() {

                MainFragment mainFragment = (MainFragment) getParentFragmentManager().findFragmentByTag("MainFragment");
                Realm.init(getContext());
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                MySettings mySettings = realm.where(MySettings.class).findFirst();
                int myCoins =  mySettings.getCoins();

                if(myCoins>=nightlight.getCost()){
                    mySettings.spendMoney(nightlight.getCost());
                    nightlight.status = true;
                    //Toast.makeText(getContext(), getResources().getString(R.string.congradilation), Toast.LENGTH_SHORT).show();
                    //
                    FragmentManager fm = getParentFragmentManager();
                    fm.beginTransaction().remove(UnlockFragment.this).commit();

                }else{
                    //Toast.makeText(getContext(), getResources().getString(R.string.sorry), Toast.LENGTH_SHORT).show();
                    //new ShakeAnimation(buy).setDuration(100).animate();
                    new ShakeAnimation(cost_txt).setDuration(150).animate();
                    //  new ScaleAnimation()
                }
                RealmResults<Nightlight> nightlightArrayList = realm.where(Nightlight.class).findAll();
                realm.commitTransaction();


                    if(mainFragment!=null){
                        mainFragment.chekNightllightStatus();
                    }


                ((MainActivity)getActivity()).renewCoins(0);



            }
        };

        r.run();


    }

    private boolean chekCost(Nightlight nightlight){
     boolean answer = false;

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings mySettings = realm.where(MySettings.class).findFirst();
        int myCoins =  mySettings.getCoins();
        realm.commitTransaction();

       if( myCoins<nightlight.getCost()){
           answer=true;
       }

        return answer;
    }


}
