package com.appsforkids.pasz.lullabies;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.appsforkids.pasz.lullabies.Fragments.FlipGameFragment;
import com.easyandroidanimations.library.FadeOutAnimation;

public class FlipAnimation implements Animation.AnimationListener {
    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;
    private Context ctx;
    private FlipEnd flipped;

    ImageView img;

    Card card;

    public FlipAnimation(Context ctx) {

        animation1 = AnimationUtils.loadAnimation(ctx, R.anim.flip_to_middle);
       // animation1.setAnimationListener(this);
        animation2 = AnimationUtils.loadAnimation(ctx, R.anim.flip_from_middle);
        animation2.setAnimationListener(this);

        Log.i("Flip", "FlipAnimation");

    }

    public FlipAnimation(View view){


    }

    @Override
    public void onAnimationStart(Animation animation) {

        Log.i("Flip", "onAnimationStart");

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        Log.i("Flip", "onAnimationEnd");

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

        Log.i("Flip", "onAnimationRepeat");

    }
    public interface FlipEnd {
        void flipEnd();

    }
    public void flipCard(View view){

        view.setAnimation(animation1);
        view.startAnimation(animation1);

        Log.i("Flip", "flipCard");

    }

    public void doubleFlip(){

        Log.i("Flip", "doubleFlip");

    }

    public void flipCardWithDelay(){

        Log.i("Flip", "flipCardWithDelay");

    }
}