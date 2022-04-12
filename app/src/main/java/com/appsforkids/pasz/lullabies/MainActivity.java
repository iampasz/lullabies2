package com.appsforkids.pasz.lullabies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewManager;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appsforkids.pasz.lullabies.Fragments.MainFragment;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class MainActivity extends AppCompatActivity{

    MediaPlayer mediaPlayer ;
    public RevolutionAnimationView revolutionAnimationView;
    int currentMusicPosition = -1;
    ConstraintLayout main_constrain;
    ConstraintLayout main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Делаем приложение на весь екран устройства
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.main);

        main_constrain = (ConstraintLayout) findViewById(R.id.main_constrain);
        main = (ConstraintLayout) findViewById(R.id.main);

        //Подключаем библиотеку ButterKnife
        //ButterKnife.bind(this);
        setSettings();

        //Устанавливаем главный фрагмент
        getSupportFragmentManager().beginTransaction().add(R.id.main, new MainFragment(), "MainFragment").commit();

        renewCoins(0);
    }

    public void playMusic(int id, Boolean play_status){

        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }

       if(play_status){
           mediaPlayer = MediaPlayer.create(this, id);
           mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
           mediaPlayer.setLooping(true);
           mediaPlayer.start();
       }else{

       }
    }

    public void finishThisApp(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
       finish();
    }

    public void startAnimation(int imageViewAnimation) {

        if(revolutionAnimationView==null){
            //Создаем анимацию
            revolutionAnimationView = new RevolutionAnimationView(this);
            main.addView(revolutionAnimationView);
        }else{
            if(revolutionAnimationView.getParent() == null) {
                Log.i("anim", "Вью не добавлена" );
                main.addView(revolutionAnimationView);
            }
            revolutionAnimationView.changeImage(ContextCompat.getDrawable(MainActivity.this,  imageViewAnimation));
        }
        Log.i("anim", "revolutionAnimationView" + revolutionAnimationView);
    }

    public void deleteAnimation(){
        //Удаляем созданную вьюху с анимацией
        ((ViewManager)revolutionAnimationView.getParent()).removeView(revolutionAnimationView);
        Log.i("anim", "анимация удалена");

    }

    //setSettings . current music position and animation
    public void setSettings(){
        int currentAnimationPosition = 0;

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        realm.commitTransaction();

//        realm.addChangeListener(new RealmChangeListener<Realm>() {
//            @Override
//            public void onChange(Realm realm) {
//               realm.deleteAll();
//            }
//        });



        if(settings==null){
            new AddToRealm(this);
            //Запускаем первую анимацию (первый запуск приложения)
            startAnimation(R.drawable.sm_1);
        }else{

            MyObjects myObjects = new MyObjects(this);
            currentMusicPosition = settings.getCurrentMusicPosition();

            if(currentMusicPosition!=-1){
                playMusic(myObjects.getMelodyArrayList().get(currentMusicPosition).getId(), true);
            }

            if(currentAnimationPosition==-1){
                //Анимация отключена в настройках
            }else{
                //Запускаем сохраненную анимацию
                startAnimation(myObjects.getAnimationImage()[currentAnimationPosition]);
            }
        }
    }

    //Обновляем количество монет после просмотра рекламы
    public void renewCoins( int coins){
        //int coins = 0;
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        // if(firstStart){
        //  text_coins.setText(realm.where(MySettings.class).findFirst().getCoins()+"");
        //  }else{
        realm.where(MySettings.class).findFirst().addCoins(coins);
        TextView text_coins = (TextView) findViewById(R.id.coins);
        text_coins.setText(realm.where(MySettings.class).findFirst().getCoins()+"");
        realm.commitTransaction();
    }
}