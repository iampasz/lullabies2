package com.appsforkids.pasz.lullabies.RealmObjects;

import com.appsforkids.pasz.lullabies.R;

import io.realm.RealmObject;

public class MySettings  extends RealmObject {

    int nightlight;
    int animationPosition = 0;
    int nightlightPosition;
    int coins;
    int backgroundColor;
    float bright;
    int rate;
    boolean adds;
    int currentMelody;

    public void setCurrentMusicPosition(int currentMelody) {
        this.currentMelody = currentMelody;
    }

    public int getCurrentMusicPosition() {
        return currentMelody;
    }

    public void setAdds(boolean adds) {
        this.adds = adds;
    }

    public boolean getAdds() {
        return adds;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setNightlight(int nightlight) {
        this.nightlight = nightlight;
    }

    public int getNightlight() {
        return nightlight;
    }

    public void setAnimationPosition(int currentAnimation) {
        this.animationPosition = currentAnimation;
    }

    public int getAnimationPosition() {
        return animationPosition;
    }

    public int getNightlightPosition() {
        return nightlightPosition;
    }

    public void setNightlightPosition(int nightlightPosition) {
        this.nightlightPosition = nightlightPosition;
    }

    public void createCoinse(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins = this.coins+coins;
    }

    public void spendMoney(int coins) {
        this.coins = this.coins - coins;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBright(float bright) {
    }

    public float getBright(){

        return bright;
    }
}
