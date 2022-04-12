package com.appsforkids.pasz.lullabies.Fragments;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.MyInterfaces.DoThisAction;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.AudioFile;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class NightlightFragment extends Fragment  implements View.OnClickListener {

    @BindView(R.id.home_button)
    ImageView home_button;

    @BindView(R.id.close_button)
    ImageView lock_button;

    @BindView(R.id.nightlight_constrain)
    ConstraintLayout nightlight_constrain;

    @BindView(R.id.timer_text2)
    TextView timer_text2;

    @BindView(R.id.buttons)
    LinearLayout buttons;


    @BindView(R.id.color_bt)
    ImageView color_bt;

    @BindView(R.id.bottom_bt)
    ImageView light_bt;


    @BindView(R.id.glasses_bt)
    ImageView glasses_bt;


    @BindView(R.id.star_bt)
    ImageView star_bt;

    @BindView(R.id.glasses_img)
    ImageView glasses;

    @BindView(R.id.hat_bt)
    ImageView hat_bt;

    @BindView(R.id.hats_img)
    ImageView hats;


    @BindView(R.id.bottom_img)
    ImageView bottom_img;

    ImageView currentBottomImage;

    RealmResults<Nightlight> Nightlightes;
    int currentColor = 0;
    int currentAudio = 0;
    int currentHat = 0;
    int currentGlass = 0;
    int currentBottom = 0;
    int currentAnimation = 0;
    boolean isPlaying = false;
    Boolean lockStatus = false;
    CountDownTimer countDownTimer;
    CountDownTimer countDownTimerApp;
    long  mytime = 0;
    MyObjects myObjects;
    int i = 0;
    Random random;
    RealmResults<AudioFile> audioFileAll;


    static NightlightFragment  init(int currentNightlight){
        NightlightFragment nightlightFragment =  new NightlightFragment();
        Bundle bundle = new Bundle();



        bundle.putInt("currentNightlight", currentNightlight);
        nightlightFragment.setArguments(bundle);
      return nightlightFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nightlight_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mytime = getArguments().getLong("mytime");


        color_bt.setOnClickListener(this::onClick);
        light_bt.setOnClickListener(this::onClick);

        glasses_bt.setOnClickListener(this::onClick);

        star_bt.setOnClickListener(this::onClick);
        hat_bt.setOnClickListener(this::onClick);


        random = new Random();


        audioFileAll = getAudios(getContext());



        buttons.setAlpha(0f);
        home_button.setAlpha(0f);
        lock_button.setAlpha(0f);

        buttons.animate().setDuration(1000).alpha(1f).start();
        home_button.animate().setDuration(1000).alpha(1f).start();
        lock_button.animate().setDuration(1000).alpha(1f).start();





        myObjects =  new MyObjects(getContext());

        Nightlightes = myObjects.getNightlightersArrayList();

        ImageView currentNightLight = view.findViewById(R.id.nightlight_img);
        ImageView currentNightLighShadow = view.findViewById(R.id.shine_img);

        Log.i("NGHT", "current "+ getArguments().getInt("currentNightlight"));
        Log.i("NGHT", "current "+ Nightlightes.get(3).nightlightName);

        currentNightLight.setImageResource(Nightlightes.get(getArguments().getInt("currentNightlight")).getNightlight());
        currentNightLighShadow.setImageResource(Nightlightes.get(getArguments().getInt("currentNightlight")).getSh_nightlight());




        if(mytime!=0){
            timer_text2.setText(mytime+" time");
        }


        closeApps(mytime);

        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                lock_button.setVisibility(View.INVISIBLE);
                home_button.setVisibility(View.INVISIBLE);
                timer_text2.setVisibility(View.INVISIBLE);
                hideBottomNav();
                this.cancel();
            }
        };

        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countDownTimer.cancel();

                closeApps(0);


                FragmentManager fm = getParentFragmentManager();



                fm.beginTransaction().add(R.id.main, MessageFragment.init(getResources().getString(R.string.are_u_sure), new DoThisAction() {
                    @Override
                    public void doThis() {

                        buttons.animate().translationX(buttons.getWidth()).setDuration(500).start();
                        lock_button.animate().translationX(lock_button.getWidth()).setDuration(500).start();
                        home_button.animate().translationY(home_button.getHeight()).setListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {

                                fm.beginTransaction()
                                        //.setCustomAnimations( R.animator.slide_in_left, R.animator.slide_in_right)
                                        .replace(R.id.main, new MainFragment(), "MainFragment").commit();

                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        }).setDuration(500).start();


                    }
                })).commit();


            }
        });

        lock_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View view) {
                if(lockStatus){
                    lock_button.setImageResource(R.drawable.bt_unlock);
                    lock_button.setAlpha(1f);
                    lockStatus = false;

                    timer_text2.setVisibility(View.VISIBLE);
                    //buttons.setVisibility(View.VISIBLE);
                    buttons.animate().translationX(0).start();
                    home_button.animate().translationY(0).start();


                }else {
                    lock_button.setImageResource(R.drawable.bt_lock);
                    lock_button.setAlpha(0.3f);
                    lockStatus = true;
                    //home_button.setVisibility(View.GONE);
                    timer_text2.setVisibility(View.GONE);
                    //buttons.setVisibility(View.GONE);


                    buttons.animate().translationX(buttons.getWidth()).start();
                    home_button.animate().translationY(home_button.getHeight()).start();



                }
            }
        });

        nightlight_constrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(lockStatus){
                if(lockStatus){
                    lock_button.setVisibility(View.VISIBLE);
                    timer_text2.setVisibility(View.VISIBLE);
                }else{
                    lock_button.setVisibility(View.VISIBLE);
                    home_button.setVisibility(View.VISIBLE);
                    timer_text2.setVisibility(View.VISIBLE);
                }


                //}
            }
        });
    }

    public void closeApps(long time){

        Log.i("TIME", time+" time");
        if(time==0){
            if(countDownTimerApp!=null){
                countDownTimerApp.cancel();
                Log.i("TIME", "CANCEL");
            }

        }else{
            if(countDownTimerApp!=null){
                countDownTimerApp.cancel();
                Log.i("TIME", "CANCEL");
            }

            countDownTimerApp = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long l) {
                    Log.i("TIME", l+" NightlightFragment");
                    timer_text2.setText(TimeUnit.MILLISECONDS.toMinutes(l)+":"+(TimeUnit.MILLISECONDS.toSeconds(l)-(TimeUnit.MILLISECONDS.toMinutes(l)*60)));
                    mytime = l;
                }

                @Override
                public void onFinish() {
                    ((MainActivity)getActivity()).finishThisApp();
                    Log.i("what", "4to tyt");
                }
            }.start();
        }
    }

    private void hideBottomNav(){
        if(getActivity().getWindow()!=null){
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    @Override
    public void onClick(View view) {

        ShopFragment shopFragment;

        switch (view.getId()){


            case R.id.color_bt:

                shopFragment = ShopFragment.init("Color");
                shopFragment.setCallBack(new ChoseItem() {
                    @Override
                    public void setImage(int currentItemPosition) {
                        getActivity().findViewById(R.id.main_constrain).setBackgroundResource(myObjects.getColorsArrayList().get(currentItemPosition).image_item);
                    }
                });

                getParentFragmentManager().beginTransaction().add(R.id.main, shopFragment).commit();

                break;

            case R.id.bottom_bt:

                shopFragment = ShopFragment.init("Basis");

                shopFragment.setCallBack(new ChoseItem() {
                    @Override
                    public void setImage(int currentItemPosition) {
                        bottom_img.setImageResource(myObjects.getBasisArrayList().get(currentItemPosition).big_image);
                    }
                });

                getParentFragmentManager().beginTransaction().add(R.id.main, shopFragment).commit();

//                bottom_img.setImageResource(myObjects.getBottom()[currentBottom]);
//                currentBottom++;
//                if(currentBottom==myObjects.getBottom().length){
//                    currentBottom=0;
//                    bottom_img.setImageResource(0);
//                }
                break;


            case R.id.glasses_bt:

//                glasses.setImageResource(myObjects.getGlasses()[currentGlass]);
//                currentGlass++;
//                if(currentGlass==myObjects.getGlasses().length){
//                    currentGlass=0;
//                    glasses.setImageResource(0);
//                }

                 shopFragment = ShopFragment.init("Glasses");
                shopFragment.setCallBack(new ChoseItem() {
                    @Override
                    public void setImage(int currentItemPosition) {
                        glasses.setImageResource(myObjects.getGlassesArrayList().get(currentItemPosition).big_image);
                    }
                });

                getParentFragmentManager().beginTransaction().add(R.id.main, shopFragment).commit();

                break;

            case R.id.hat_bt:

                shopFragment = ShopFragment.init("Hats");
                shopFragment.setCallBack(new ChoseItem() {
                    @Override
                    public void setImage(int currentItemPosition) {
                        hats.setImageResource(myObjects.getHatsArrayList().get(currentItemPosition).big_image);

                    }
                });

                getParentFragmentManager().beginTransaction().add(R.id.main, shopFragment).commit();

                break;


            case R.id.star_bt:

//                currentAnimation++;
//                if(currentAnimation==myObjects.getAnimationImage().length){
//                    currentAnimation=0;
//                    ((MainActivity)getActivity()).revolutionAnimationView.pause();
//                    ((MainActivity)getActivity()).revolutionAnimationView.setAlpha(0);
//                }else{
//                    ((MainActivity)getActivity()).revolutionAnimationView.resume();
//                    ((MainActivity)getActivity()).revolutionAnimationView.setAlpha(1f);
//                }
//
//                ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(),  myObjects.getAnimationImage()[currentAnimation]));


                shopFragment = ShopFragment.init("Stars");
                shopFragment.setCallBack(new ChoseItem() {
                    @Override
                    public void setImage(int currentItemPosition) {

                        if(currentItemPosition==0){
                            ((MainActivity)getActivity()).deleteAnimation();
                        }else{
                            ((MainActivity)getActivity()).startAnimation(myObjects.getStarsArrayList().get(currentItemPosition).big_image);
                        }
                    }
                });

                getParentFragmentManager().beginTransaction().add(R.id.main, shopFragment).commit();

                break;
        }
    }

    public RealmResults<AudioFile> getAudios(Context ctx){

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        audioFileAll = realm.where(AudioFile.class).findAll();
        realm.commitTransaction();
        return audioFileAll;
    }



    public interface ChoseItem {

        public void setImage(int currentItemPosition);
    }
}

