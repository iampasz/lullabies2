package com.appsforkids.pasz.lullabies.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.Adapters.MainPagerAdapter;
import com.appsforkids.pasz.lullabies.MyInterfaces.DoThisAction;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;
import com.appsforkids.pasz.lullabies.RevolutionAnimationView;
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
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.settings_button)
    ImageView settings_button;

    @BindView(R.id.melodies_button)
    ImageView melodies_button;

    @BindView(R.id.turn_on_button)
    FrameLayout turn_on_button;

    @BindView(R.id.leaf_button)
    ImageView leaf_button;

    @BindView(R.id.constrain_layout)
    ConstraintLayout constrain_layout;

    @BindView(R.id.timer_text)
    TextView timer_text;

    @BindView(R.id.game_button)
    ImageView game_button;

    @BindView(R.id.turn_on_text)
    TextView turn_on_text;



  //  ImageView nightlight;
    Boolean statusAnimation = false;

    long  mytime = 0;

    CountDownTimer countDownTimer;

    //ADS
    private RewardedAd mRewardedAd;
    private AdRequest adRequest;
    private AdView mAdView;
    boolean addLoadedStatus = false;

    FragmentManager fm;
    String TAG = "MYADD";

    MyObjects myObjects;
    RealmResults myNightlights;

    @BindView(R.id.viewpager)
    public ViewPager2 viewpager;

    Nightlight currentNightlight;
    int currentPosition = 0;
    //SharedPreferences sharedPreferences;
   // SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        //Создаем клас с обьектами
        myObjects = new MyObjects(getContext());

        //Получаем родительский фрагмент менеджер
        fm = getParentFragmentManager();

        //Делаем кнопку для получения листочков за рекламу невидемой
        leaf_button.setVisibility(View.INVISIBLE);

        if(hasConnection(getContext())){
            startAdds();
        }



        //Старт анимации
        startFirstAnimation();



        //Создаем файл с обьектами из которых берем нужные обьекты и настройки
        MyObjects myObjects = new MyObjects(getContext());
        myNightlights = myObjects.getNightlightersArrayList();
        //Создаем адаптер для пейджера и вкладываем туда наши ночники
        MainPagerAdapter adapter = new MainPagerAdapter(myNightlights, getContext());
        viewpager.setAdapter(adapter);

        //Каждый раз при скролинге, проверяем куплен ночник или нет
        chekNightlightStatus();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings_button.setOnClickListener(this);
        melodies_button.setOnClickListener(this);
        turn_on_button.setOnClickListener(this);
        leaf_button.setOnClickListener(this);
        game_button.setOnClickListener(this);

        //Устанавливаем созданенные параметры при выходе
        setSettings();

       // chekNightllightStatus();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.settings_button:
                fm.beginTransaction().setCustomAnimations(R.animator.appearance, R.animator.disappearance)
                        .add(R.id.main, new SettingsFragment()).commit();
                break;
            case R.id.melodies_button:
                fm.beginTransaction()
                        .add(R.id.main, new MelodyListFragment()).commit();
                break;
            case R.id.game_button:
                fm.beginTransaction().setCustomAnimations(R.animator.appearance, R.animator.disappearance)
                        .replace(R.id.main, new FlipGameFragment(), "FlipGameFragment").commit();

                //viewpager.setCurrentItem(3);
                //viewpager.setCurrentItem(3, false);
                break;

            case R.id.turn_on_button:

                if(turn_on_text.getText()=="Turn on"){
                    fm.beginTransaction()
                             .replace(R.id.main, NightlightFragment.init(viewpager.getCurrentItem()), "NightlightFragment").commit();
                }else{
                    fm.beginTransaction()
                             .add(R.id.main, UnlockFragment.init(viewpager.getCurrentItem(), addLoadedStatus), "UnlockFragment").commit();
                    };

                break;

            case R.id.leaf_button:

                fm.beginTransaction()
                        .add(R.id.main, MessageFragment.init(getResources().getString(R.string.get_coins), new DoThisAction() {
                            @Override
                            public void doThis() {
                               showAddsForReward();
                            }
                        }), "UnlockFragment")
                        .commit();
                break;
            }
        }

  //  @Override
  //  public void onStart() {
  //      super.onStart();
        //refreshParam();
   // }




    public void sleepTimer(int time){
        if(time==0){
            if(countDownTimer!=null){
                countDownTimer.cancel();
                timer_text.setText("");
                mytime = 0;
            }
        }else{
            if(countDownTimer!=null){
                countDownTimer.cancel();
            }
            countDownTimer = new CountDownTimer(time*1000, 1000) {
                @Override
                public void onTick(long l) {
                    timer_text.setText(TimeUnit.MILLISECONDS.toMinutes(l)+":"+(TimeUnit.MILLISECONDS.toSeconds(l)-(TimeUnit.MILLISECONDS.toMinutes(l)*60)));
                    mytime = l;
                }
                @Override
                public void onFinish() {
                    ((MainActivity)getActivity()).finishThisApp();
                   // Log.i("what", "4to tyt");
                }
            }.start();
        }
    }



//    //Получаем статус просмотреной рекламы
//    public void getCallBack(){
//        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//            @Override
//            public void onAdShowedFullScreenContent() {
//                Log.d(TAG, "Ad was shown.");
//            }
//            @Override
//            public void onAdFailedToShowFullScreenContent(AdError adError) {
//                Log.d(TAG, "Ad failed to show.");
//            }
//            @Override
//            public void onAdDismissedFullScreenContent() {
//                Log.d(TAG, "Ad was dismissed.");
//                mRewardedAd = null;
//            }
//        });
//    }

    public void startFirstAnimation(){
       // top_constrain.setAlpha(0f);
       // bottomLinearMenu.setAlpha(0f);
       // top_constrain.animate().setDuration(1000).alpha(1f).start();
        //bottomLinearMenu.animate().setDuration(1000).alpha(1f).start();
    }

    private void changeAnimation(int image) {
        //Log.i("MyAnimation", image+ " image");
        if(statusAnimation){
            ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), image));
        }else {

            if(((MainActivity)getActivity()).revolutionAnimationView==null){
                ConstraintLayout myLayout = getActivity().findViewById(R.id.main_constrain);
                ((MainActivity)getActivity()).revolutionAnimationView = new RevolutionAnimationView(getContext());
                ((MainActivity)getActivity()).revolutionAnimationView.setLayoutParams(new RelativeLayout.LayoutParams(
                        myLayout.getLayoutParams().MATCH_PARENT,
                        myLayout.getLayoutParams().MATCH_PARENT));
                ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), image));
                myLayout.addView(((MainActivity)getActivity()).revolutionAnimationView);
                statusAnimation = true;
            }else{
            }
        }

        if(((MainActivity)getActivity()).revolutionAnimationView==null){

        }else{
            ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), image));
        }
    }

    public Boolean getNightlightStatus(){
        Boolean nightlightStatus=true;
        return nightlightStatus;
    }

    public void chekNightllightStatus(){
        Nightlight currentNightlight = getNightlight(viewpager.getCurrentItem());
        if (currentNightlight != null) {
            if(currentNightlight.status){
                if(turn_on_text!=null){
                    turn_on_text.setText("Turn on");
                }
            }else {
                if (turn_on_text != null) {
                    turn_on_text.setText("Unlock");
                }
            }
        }
    }

    private Nightlight getNightlight(int position){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Nightlight> nightlightArrayList = realm.where(Nightlight.class).findAll();
        realm.commitTransaction();
        return nightlightArrayList.get(position);
    }

    public void  chekNightlightStatus(){
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                currentNightlight = (Nightlight) myNightlights.get(position);
                currentPosition = position;
                if(currentNightlight.status){
                    turn_on_text.setText("Turn on");
                }else{
                    turn_on_text.setText("Unlock");
                }
            }
        });
    }

    public void setSettings(){

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        Log.i("Setting", "текущая сохраненная позиция"+ settings.getNightlightPosition());
        viewpager.setCurrentItem(settings.getNightlightPosition(), false);

        ConstraintLayout constraintLayout = getActivity().findViewById(R.id.main_constrain);
        constraintLayout.setBackgroundResource(settings.getBackgroundColor());

        int currentAnimation = settings.getAnimationPosition();
        int currentRate = settings.getRate();
        int currentBackgroundColor = settings.getBackgroundColor();
        int currentNightlightPosition = settings.getNightlightPosition();
        int currentNightligh = settings.getNightlight();
        boolean currentAdds = settings.getAdds();
        float currentBright = settings.getBright();

        Log.i("setSettings", currentAnimation+"  currentAnimation");
        //Log.i("setSettings", currentRate+"  currentRate");
        //Log.i("setSettings", currentBackgroundColor+"  currentBackgroundColor");
       // Log.i("setSettings", currentNightlightPosition+"  currentNightlightPosition");
        //Log.i("setSettings", currentCoins+"  currentCoins");
       // Log.i("setSettings", currentNightligh+"  currentNightligh");
       // Log.i("setSettings", currentAdds+"  currentAdds");
        //Log.i("setSettings", currentBright+"  currentBright");

        //Показываем фрагмент где предлагается проголосовать за приложение и поставить оценку
        switch (currentRate){
            case -1:

                break;

            case 3:
            //проверяем интернет подключение
                if(hasConnection(getContext())){
                    getParentFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.animator.appearance, R.animator.disappearance)
                            .add(R.id.main, new RateFragment())
                            .commit();

                    settings.setRate(0);
                }

                break;

            default:
                currentRate++;
                settings.setRate(currentRate);
                break;
        }

        //Устанавливаем анимацию
        Log.i("setSettings"," currentAnimation " + currentAnimation);
        if(currentAnimation!=-1){
            ((MainActivity)getActivity()).startAnimation(myObjects.getAnimationImage()[currentAnimation]);
        }

       // ((MainActivity)getActivity()).revolutionAnimationView.changeImage(ContextCompat.getDrawable(getContext(), myObjects.getAnimationImage()[currentAnimation]));
        realm.commitTransaction();
    }

    @Override
    public void onStop() {
        super.onStop();
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setNightlightPosition(viewpager.getCurrentItem());
        realm.commitTransaction();
    }

    public void startAdds(){
        //Реклама
        adRequest = new AdRequest.Builder().build();
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        loadAdsForReward();
    }

    //Загружаем рекламу
    public void loadAdsForReward(){
        RewardedAd.load(getContext(), getResources().getString(R.string.reward_ad_unit_id),
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("UnlockFragment", loadAdError.getMessage());
                        mRewardedAd = null;
                    }
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d("UnlockFragment", "Ad was loaded.");
                        leaf_button.setVisibility(View.VISIBLE);
                        addLoadedStatus = true;
                    }
                });
    }

    //Проверка интернет подключения
    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        return wifiInfo != null && wifiInfo.isConnected();
    }

    //Показываем рекламу за вознаграждение
    public void showAddsForReward(){
        if (mRewardedAd != null) {
            Log.d("UnlockFragment", "You are here.");

            leaf_button.setVisibility(View.INVISIBLE);
            loadAdsForReward();

            mRewardedAd.show(getActivity(), new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("UnlockFragment", "The user earned the reward.");
                    ((MainActivity)getActivity()).renewCoins(50);
                }
            });
        } else {
            Log.d("UnlockFragment", "The rewarded ad wasn't ready yet.");
        }
    }

    //Показываем рекламу за вознаграждение. Open nightlight for one time
    public void showAddsForOneNightlight(){
        if (mRewardedAd != null) {
            Log.d("UnlockFragment", "You are here.");
            addLoadedStatus=false;
            loadAdsForReward();
            mRewardedAd.show(getActivity(), new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("UnlockFragment", "The user earned the reward.");
                    turn_on_text.setText(getString(R.string.turn_on));
                }
            });
        } else {
            Log.d("UnlockFragment", "The rewarded ad wasn't ready yet.");
        }
    }

}