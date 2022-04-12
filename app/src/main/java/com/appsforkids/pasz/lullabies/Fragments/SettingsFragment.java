package com.appsforkids.pasz.lullabies.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RevolutionAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.bt_adds)
    ImageView bt_adds;

    @BindView(R.id.save_button)
    FrameLayout save;

    @BindView(R.id.bt_colors)
    ImageView bt_colors;

    @BindView(R.id.st_background)
    FrameLayout st_background;

    @BindView(R.id.bt_star)
    ImageView bt_star;

    @BindView(R.id.set_img)
    ImageView set_img;

    @BindView(R.id.set_shadow)
    ImageView set_shadow;

    @BindView(R.id.bt_timer)
    ImageView bt_timer;

    @BindView(R.id.timer_text)
    TextView timer_text;

    @BindView(R.id.bt_lamp)
    ImageView bt_lamp;

    MyObjects myObjects;
    RevolutionAnimationView revolutionAnimationView = null;

    int backgroundColor = 0;
    int currentAnimation = 0;
    int colorCounter = 0;
    int brightCounter = 0;
    int timerCounter = 0;
    float bright = 0.5f;

    int[] timerTime = {0, 60*5, 60*10, 60*60};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        bt_adds.setOnClickListener(this);
        save.setOnClickListener(this);
        bt_colors.setOnClickListener(this);
        bt_star.setOnClickListener(this);
        set_img.setOnClickListener(this);
        bt_timer.setOnClickListener(this);
        bt_lamp.setOnClickListener(this);

        myObjects = new MyObjects(getContext());
        setSettings();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_colors:
                 changeColor();
                break;

            case R.id.bt_lamp:
                changeBright();
                break;

            case R.id.bt_timer:
              setTimer();
                break;

            case R.id.bt_star:
                changeAnimation();
                break;

            case R.id.save_button:
                saveSettings();
                getParentFragmentManager().beginTransaction().remove(this).commit();
                break;
        }
    }

    private void setTimer(){

        timerCounter++;
        if(timerCounter==timerTime.length){
            timerCounter = 0;
        }
        timer_text.setText(timerTime[timerCounter]/60+" minutes");
    }

    private void changeColor() {

        colorCounter++;
        if(colorCounter==myObjects.getColors().length){
            colorCounter=0;
        }
        backgroundColor = myObjects.getColors()[colorCounter];
        st_background.setBackgroundResource(backgroundColor);
        ConstraintLayout constraintLayout = getActivity().findViewById(R.id.main);
        constraintLayout.setBackgroundResource(backgroundColor);

    }

    private void changeAnimation() {

        currentAnimation++;

        if(currentAnimation==myObjects.getAnimationImage().length){
            ((MainActivity)getActivity()).deleteAnimation();
            currentAnimation=-1;
        }else{
            ((MainActivity)getActivity()).startAnimation(myObjects.getAnimationImage()[currentAnimation]);
            Log.i("anim", "currentAnimation" + currentAnimation);
        }









//        float alfa = ((MainActivity)getActivity()).revolutionAnimationView.getAlpha();
//
//        if(alfa==0){
//            ((MainActivity)getActivity()).revolutionAnimationView.setAlpha(1f);
//            ((MainActivity)getActivity()).revolutionAnimationView.resume();
//        }

//        currentAnimation++;
//        animationImg = myObjects.getAnimationImage()[currentAnimation];
//
//            if(revolutionAnimationView==null){
//                revolutionAnimationView = new RevolutionAnimationView(getContext());
//                revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), animationImg));
//                int width =  st_background.getLayoutParams().width;
//                int height = st_background.getLayoutParams().height;
//                ConstraintSet set = new ConstraintSet();
//                st_background.addView(revolutionAnimationView);
//            }else{
//                revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), animationImg));
//                ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), animationImg));
//
//            }


        }

    private void changeBright(){
        //Изменяем яркость екрана
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.screenBrightness = myObjects.getBrights()[brightCounter];
        getActivity().getWindow().setAttributes(lp);
        //Сохраняем переменную с новым параметром
        bright = myObjects.getBrights()[brightCounter];
        //Берем следующую яркость
        brightCounter++;
        //Если переменные закончились, начинаем заново
        if(brightCounter==myObjects.getBrights().length){
            brightCounter=0;
        }
        Log.i("Settings", lp.screenBrightness+" яркость "+ brightCounter+"  " +myObjects.getBrights().length);
    }

    private void saveSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();

        settings.setBackgroundColor(backgroundColor);
        settings.setAnimationPosition(currentAnimation);
        settings.setBright(bright);
        realm.commitTransaction();
    }


    public void setSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();

        backgroundColor = settings.getBackgroundColor();
        currentAnimation = settings.getAnimationPosition();

        Log.i("setSettings", currentAnimation+"  currentAnimation in SettingsFragment");

        bright =  settings.getBright();

        realm.commitTransaction();
    }
}