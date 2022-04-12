package com.appsforkids.pasz.lullabies;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Card  {

   String name;
   int image;
   boolean open = true;

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getStatus(){
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }


}
