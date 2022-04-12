package com.appsforkids.pasz.lullabies;

import android.content.Context;

import com.appsforkids.pasz.lullabies.RealmObjects.AudioFile;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;
import com.appsforkids.pasz.lullabies.RealmObjects.ShopItems;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyObjects {

    Context ctx;

    public MyObjects(Context ctx){
        this.ctx = ctx;
    }

    public RealmResults<Nightlight> getNightlightersArrayList(){
            Realm.init(ctx);
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            RealmResults nightlightsResult = realm.where(Nightlight.class).findAll();
            realm.commitTransaction();
            return nightlightsResult;
        }

    public RealmResults<AudioFile> getMelodyArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults melodyResult = realm.where(AudioFile.class).findAll();
        realm.commitTransaction();
        return melodyResult;
    }

    public RealmResults<ShopItems> getHatsArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults shopItemsRealmResults = realm.where(ShopItems.class).equalTo("itemsName","Hats").findAll();
        realm.commitTransaction();
        return shopItemsRealmResults;
    }

    public RealmResults<ShopItems> getGlassesArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults shopItemsRealmResults = realm.where(ShopItems.class).equalTo("itemsName","Glasses").findAll();
        realm.commitTransaction();
        return shopItemsRealmResults;
    }

    public RealmResults<ShopItems> getStarsArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults shopItemsRealmResults = realm.where(ShopItems.class).equalTo("itemsName","Stars").findAll();
        realm.commitTransaction();
        return shopItemsRealmResults;
    }

    public RealmResults<ShopItems> getBasisArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults shopItemsRealmResults = realm.where(ShopItems.class).equalTo("itemsName","Basis").findAll();
        realm.commitTransaction();
        return shopItemsRealmResults;
    }

    public RealmResults<ShopItems> getColorsArrayList(){
        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults shopItemsRealmResults = realm.where(ShopItems.class).equalTo("itemsName","Color").findAll();
        realm.commitTransaction();
        return shopItemsRealmResults;
    }







    public int[]   getColors(){
            int[] colors =  {
                    R.drawable.color9,
                    R.drawable.color2,
                    R.drawable.color1,
                    R.drawable.color7,
                    R.drawable.color10,
                    R.drawable.color6,
                    R.drawable.color4,
                    R.drawable.color3,
                    R.drawable.color8,
                    R.drawable.color10,
                    R.drawable.color13,
            };
            return colors;
        }

    public int[]   getSmHats(){
        int[] hats =  {
                R.drawable.s_h1,
                R.drawable.s_h2,
                R.drawable.s_h3,
                R.drawable.s_h4,
                R.drawable.s_h5,
                R.drawable.s_h6,
                R.drawable.s_h7,
                R.drawable.s_h8,
                R.drawable.s_h9,
                R.drawable.s_h10,
                R.drawable.s_h11,
                R.drawable.s_h12,
                R.drawable.s_h13,
                R.drawable.s_h14,
                R.drawable.s_h15,
                R.drawable.s_h16
        };
        return hats;
    }

    public int[]   getHats(){
        int[] hats =  {
                R.drawable.h_1,
                R.drawable.h_2,
                R.drawable.h_3,
                R.drawable.h_4,
                R.drawable.h_5,
                R.drawable.h_6,
                R.drawable.h_7,
                R.drawable.h_8,
                R.drawable.h_9,
                R.drawable.h_10,
                R.drawable.h_11,
                R.drawable.h_12,
                R.drawable.h_13,
                R.drawable.h_14,
                R.drawable.h_15,
                R.drawable.h_16,
                R.drawable.h_16
        };
        return hats;
    }

    public int[]   getGlasses(){
        int[] glasses =  {
                R.drawable.gl_1,
                R.drawable.gl_2,
                R.drawable.gl_3,
                R.drawable.gl_4,
                R.drawable.gl_5,
                R.drawable.gl_6,
                R.drawable.gl_7,
                R.drawable.gl_8,
                R.drawable.gl_9,
                R.drawable.gl_10,
                R.drawable.gl_11,
                R.drawable.gl_12,
                R.drawable.gl_13,
                R.drawable.gl_14,
                R.drawable.gl_15,
                R.drawable.gl_16,
                R.drawable.gl_17,
                R.drawable.gl_18,
                R.drawable.gl_18
        };
        return glasses;
    }

    public int[]   getBottom(){

        int[] bottoms =  {
                R.drawable.btm_1,
                R.drawable.btm_2,
                R.drawable.btm_3,
                R.drawable.btm_4,
                R.drawable.btm_5,
                R.drawable.btm_6,
                R.drawable.btm_7,
                R.drawable.cloud1,
                R.drawable.cloud2,
                R.drawable.cloud3
        };

        return bottoms;
    }

    public int[]   getAnimationImage(){

        int[] randomImage =  {
                R.drawable.sm_1,
                R.drawable.sm_2,
                R.drawable.sm_3,
                R.drawable.sm_4,
                R.drawable.sm_6,
                R.drawable.sm_7,
                R.drawable.sm_8,
                R.drawable.sm_9,
                R.drawable.sm_10,
                R.drawable.sm_11,
                R.drawable.sm_12,
                R.drawable.sm_13,
                R.drawable.sm_14,
                R.drawable.sm_15,
                R.drawable.sm_16,
                R.drawable.sm_17,
                R.drawable.sm_18,
                R.drawable.sm_19,
                R.drawable.sm_20,
                R.drawable.sm_21,
                R.drawable.sm_22,
                R.drawable.sm_23,
                R.drawable.sm_24,
                R.drawable.sm_25,
                R.drawable.sm_26
        };

        return randomImage;

    };

    public float[] getBrights(){

        float[] brights =  {
                0.0f,
                0.3f,
                0.5f,
                0.8f,
                1f
        };

        return brights;
    }

    public ArrayList<Card> getCards(){
        ArrayList<Card> cardArrayList = new ArrayList<>();
        Card card1 = new Card();
        card1.setImage(R.drawable.an_bear);
        card1.setName("an_bear");
        Card card2 = new Card();
        card2.setImage(R.drawable.an_dear);
        card2.setName("an_dear");
        Card card3 = new Card();
        card3.setImage(R.drawable.an_rabbit);
        card3.setName("an_rabbit");
        Card card4 = new Card();
        card4.setImage(R.drawable.an_elephan);
        card4.setName("an_elephan");
        Card card5 = new Card();
        card5.setImage(R.drawable.an_fox);
        card5.setName("an_fox");
        Card card6 = new Card();
        card6.setImage(R.drawable.an_cheetah);
        card6.setName("an_cheetah");
        Card card7 = new Card();
        card7.setImage(R.drawable.an_squirrel);
        card7.setName("an_squirrel");
        Card card8 = new Card();
        card8.setImage(R.drawable.an_hedgehog);
        card8.setName("an_hedgehog");
        cardArrayList.add(card1);
        cardArrayList.add(card1);
        cardArrayList.add(card2);
        cardArrayList.add(card2);
        cardArrayList.add(card3);
        cardArrayList.add(card3);
        cardArrayList.add(card4);
        cardArrayList.add(card4);
        cardArrayList.add(card5);
        cardArrayList.add(card5);
        cardArrayList.add(card6);
        cardArrayList.add(card6);
        cardArrayList.add(card7);
        cardArrayList.add(card7);
        cardArrayList.add(card8);
        cardArrayList.add(card8);

        Collections.shuffle(cardArrayList);
        return cardArrayList;
    }


}
