package com.appsforkids.pasz.lullabies;

import android.content.Context;
import android.media.MediaPlayer;

public class MyPlayer  {

    Context ctx;

    public MyPlayer MyPlayer(Context ctx){

        this.ctx = ctx;

        return new MyPlayer();
    }


    public void playMusic(){

        MediaPlayer mediaPlayer = MediaPlayer.create(ctx, R.raw.sound_file_1);
        mediaPlayer.start();


    }


}
