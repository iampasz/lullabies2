package com.appsforkids.pasz.lullabies;

import android.content.Context;

import com.appsforkids.pasz.lullabies.RealmObjects.AudioFile;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;
import com.appsforkids.pasz.lullabies.RealmObjects.ShopItems;

import io.realm.Realm;

class AddToRealm {

    AddToRealm(Context ctx) {

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        AudioFile audioFile = new AudioFile();
        audioFile.setId(R.raw.sound_file_1);
        audioFile.setNameSong(R.string.name_song_file_1);
        audioFile.setAuthorSong(R.string.author_song_file_1);
        audioFile.setPosition(1);

        AudioFile audioFile2 = new AudioFile();
        audioFile2.setId(R.raw.sound_file_2);
        audioFile2.setNameSong(R.string.author_song_file_2);
        audioFile2.setAuthorSong(R.string.name_song_file_2);
        audioFile2.setPosition(2);

        AudioFile audioFile3 = new AudioFile();
        audioFile3.setId(R.raw.sound_file_3);
        audioFile3.setNameSong(R.string.name_song_file_3);
        audioFile3.setAuthorSong(R.string.author_song_file_3);
        audioFile3.setPosition(3);

        AudioFile audioFile4 = new AudioFile();
        audioFile4.setId(R.raw.sound_file_4);
        audioFile4.setNameSong(R.string.name_song_file_4);
        audioFile4.setAuthorSong(R.string.author_song_file_4);
        audioFile4.setPosition(4);


        AudioFile audioFile5 = new AudioFile();
        audioFile5.setId(R.raw.sound_file_5);
        audioFile5.setNameSong(R.string.name_song_file_5);
        audioFile5.setAuthorSong(R.string.author_song_file_5);
        audioFile5.setPosition(5);

        AudioFile audioFile6 = new AudioFile();
        audioFile6.setId(R.raw.sound_file_6);
        audioFile6.setNameSong(R.string.name_song_file_6);
        audioFile6.setAuthorSong(R.string.author_song_file_6);
        audioFile6.setPosition(6);

        AudioFile audioFile7 = new AudioFile();
        audioFile7.setId(R.raw.sound_file_7);
        audioFile7.setNameSong(R.string.name_song_file_7);
        audioFile7.setAuthorSong(R.string.author_song_file_7);
        audioFile7.setPosition(7);


        realm.copyToRealm(audioFile);
        realm.copyToRealm(audioFile2);
        realm.copyToRealm(audioFile3);
        realm.copyToRealm(audioFile4);
        realm.copyToRealm(audioFile5);
        realm.copyToRealm(audioFile6);
        realm.copyToRealm(audioFile7);


        Nightlight bear = new Nightlight();
        bear.nightlight = R.drawable.an_bear;
        bear.sh_nightlight = R.drawable.sh_bear;
        bear.nightlightName = R.string.bear;
        bear.status = true;

        Nightlight dear = new Nightlight();
        dear.nightlight = R.drawable.an_dear;
        dear.sh_nightlight = R.drawable.sh_dear;
        dear.nightlightName = R.string.dear;
        dear.status = false;
        dear.setCost(100);

        Nightlight fox = new Nightlight();
        fox.nightlight = R.drawable.an_fox;
        fox.sh_nightlight = R.drawable.sh_fox;
        fox.nightlightName = R.string.fox;
        fox.status = false;
        fox.setCost(200);

        Nightlight hedgehog = new Nightlight();
        hedgehog.nightlight = R.drawable.an_hedgehog;
        hedgehog.sh_nightlight = R.drawable.sh_hedgehog;
        hedgehog.nightlightName = R.string.hedgehog;
        hedgehog.status = false;
        hedgehog.setCost(300);

        Nightlight rabbit = new Nightlight();
        rabbit.nightlight = R.drawable.an_rabbit;
        rabbit.sh_nightlight = R.drawable.sh_rabbit;
        rabbit.nightlightName = R.string.rabbit;
        rabbit.status = false;
        rabbit.setCost(350);

        Nightlight squirrel = new Nightlight();
        squirrel.nightlight = R.drawable.an_squirrel;
        squirrel.sh_nightlight = R.drawable.sh_squirrel;
        squirrel.nightlightName = R.string.squirrel;
        squirrel.status = false;
        squirrel.setCost(400);

        Nightlight cheetah = new Nightlight();
        cheetah.nightlight = R.drawable.an_cheetah;
        cheetah.sh_nightlight = R.drawable.sh_cheetah;
        cheetah.nightlightName = R.string.cheetah;
        cheetah.status = false;
        cheetah.setCost(800);

        Nightlight elephan = new Nightlight();
        elephan.nightlight = R.drawable.an_elephan;
        elephan.sh_nightlight = R.drawable.sh_elephan;
        elephan.nightlightName = R.string.elephan;
        elephan.status = false;
        elephan.setCost(1000);

        ShopItems hats0 = new ShopItems();
        hats0.setItemsName("Hats");
        hats0.setCost(50);
        hats0.setStatus(true);
        hats0.setBigImage(0);
        hats0.setImage_item(0);

        ShopItems hats1 = new ShopItems();
        hats1.setItemsName("Hats");
        hats1.setCost(50);
        hats1.setStatus(true);
        hats1.setBigImage(R.drawable.h_1);
        hats1.setImage_item(R.drawable.s_h1);

        ShopItems hats2 = new ShopItems();
        hats2.setItemsName("Hats");
        hats2.setCost(50);
        hats2.setStatus(true);
        hats2.setBigImage(R.drawable.h_2);
        hats2.setImage_item(R.drawable.s_h2);

        ShopItems hats3 = new ShopItems();
        hats3.setItemsName("Hats");
        hats3.setCost(50);
        hats3.setStatus(false);
        hats3.setBigImage(R.drawable.h_3);
        hats3.setImage_item(R.drawable.s_h3);

        ShopItems hats4 = new ShopItems();
        hats4.setItemsName("Hats");
        hats4.setCost(50);
        hats4.setStatus(false);
        hats4.setBigImage(R.drawable.h_4);
        hats4.setImage_item(R.drawable.s_h4);

        ShopItems hats5 = new ShopItems();
        hats5.setItemsName("Hats");
        hats5.setCost(100);
        hats5.setStatus(false);
        hats5.setBigImage(R.drawable.h_5);
        hats5.setImage_item(R.drawable.s_h5);

        ShopItems hats6 = new ShopItems();
        hats6.setItemsName("Hats");
        hats6.setCost(100);
        hats6.setStatus(false);
        hats6.setBigImage(R.drawable.h_6);
        hats6.setImage_item(R.drawable.s_h6);

        ShopItems hats7 = new ShopItems();
        hats7.setItemsName("Hats");
        hats7.setCost(100);
        hats7.setStatus(false);
        hats7.setBigImage(R.drawable.h_7);
        hats7.setImage_item(R.drawable.s_h7);

        ShopItems hats8 = new ShopItems();
        hats8.setItemsName("Hats");
        hats8.setCost(100);
        hats8.setStatus(false);
        hats8.setBigImage(R.drawable.h_8);
        hats8.setImage_item(R.drawable.s_h8);


        ShopItems hats9 = new ShopItems();
        hats9.setItemsName("Hats");
        hats9.setCost(150);
        hats9.setStatus(false);
        hats9.setBigImage(R.drawable.h_9);
        hats9.setImage_item(R.drawable.s_h9);

        ShopItems hats10 = new ShopItems();
        hats10.setItemsName("Hats");
        hats10.setCost(150);
        hats10.setStatus(false);
        hats10.setBigImage(R.drawable.h_10);
        hats10.setImage_item(R.drawable.s_h10);

        ShopItems hats11 = new ShopItems();
        hats11.setItemsName("Hats");
        hats11.setCost(150);
        hats11.setStatus(false);
        hats11.setBigImage(R.drawable.h_11);
        hats11.setImage_item(R.drawable.s_h11);

        ShopItems hats12 = new ShopItems();
        hats12.setItemsName("Hats");
        hats12.setCost(150);
        hats12.setStatus(false);
        hats12.setBigImage(R.drawable.h_12);
        hats12.setImage_item(R.drawable.s_h12);

        ShopItems hats13 = new ShopItems();
        hats13.setItemsName("Hats");
        hats13.setCost(200);
        hats13.setStatus(false);
        hats13.setBigImage(R.drawable.h_13);
        hats13.setImage_item(R.drawable.s_h13);

        ShopItems hats14 = new ShopItems();
        hats14.setItemsName("Hats");
        hats14.setCost(200);
        hats14.setStatus(false);
        hats14.setBigImage(R.drawable.h_14);
        hats14.setImage_item(R.drawable.s_h14);

        ShopItems hats15 = new ShopItems();
        hats15.setItemsName("Hats");
        hats15.setCost(200);
        hats15.setStatus(false);
        hats15.setBigImage(R.drawable.h_15);
        hats15.setImage_item(R.drawable.s_h15);

        ShopItems hats16 = new ShopItems();
        hats16.setItemsName("Hats");
        hats16.setCost(200);
        hats16.setBigImage(R.drawable.h_16);
        hats16.setImage_item(R.drawable.s_h16);

        ShopItems glasses0 = new ShopItems();
        glasses0.setItemsName("Glasses");
        glasses0.setCost(0);
        glasses0.setStatus(true);
        glasses0.setBigImage(0);
        glasses0.setImage_item(0);

        ShopItems glasses1 = new ShopItems();
        glasses1.setItemsName("Glasses");
        glasses1.setCost(50);
        glasses1.setBigImage(R.drawable.gl_1);
        glasses1.setImage_item(R.drawable.sgl_1);

        ShopItems glasses2 = new ShopItems();
        glasses2.setItemsName("Glasses");
        glasses2.setCost(50);
        glasses2.setBigImage(R.drawable.gl_2);
        glasses2.setImage_item(R.drawable.sgl_2);

        ShopItems glasses3 = new ShopItems();
        glasses3.setItemsName("Glasses");
        glasses3.setCost(50);
        glasses3.setBigImage(R.drawable.gl_3);
        glasses3.setImage_item(R.drawable.sgl_3);

        ShopItems glasses4 = new ShopItems();
        glasses4.setItemsName("Glasses");
        glasses4.setCost(50);
        glasses4.setBigImage(R.drawable.gl_4);
        glasses4.setImage_item(R.drawable.sgl_4);

        ShopItems glasses5 = new ShopItems();
        glasses5.setItemsName("Glasses");
        glasses5.setCost(100);
        glasses5.setBigImage(R.drawable.gl_5);
        glasses5.setImage_item(R.drawable.sgl_5);

        ShopItems glasses6 = new ShopItems();
        glasses6.setItemsName("Glasses");
        glasses6.setCost(100);
        glasses6.setBigImage(R.drawable.gl_6);
        glasses6.setImage_item(R.drawable.sgl_6);

        ShopItems glasses7 = new ShopItems();
        glasses7.setItemsName("Glasses");
        glasses7.setCost(100);
        glasses7.setBigImage(R.drawable.gl_7);
        glasses7.setImage_item(R.drawable.sgl_7);

        ShopItems glasses8 = new ShopItems();
        glasses8.setItemsName("Glasses");
        glasses8.setCost(100);
        glasses8.setBigImage(R.drawable.gl_8);
        glasses8.setImage_item(R.drawable.sgl_8);

        ShopItems glasses9 = new ShopItems();
        glasses9.setItemsName("Glasses");
        glasses9.setCost(150);
        glasses9.setBigImage(R.drawable.gl_9);
        glasses9.setImage_item(R.drawable.sgl_9);

        ShopItems glasses10 = new ShopItems();
        glasses10.setItemsName("Glasses");
        glasses10.setCost(150);
        glasses10.setBigImage(R.drawable.gl_10);
        glasses10.setImage_item(R.drawable.sgl_10);

        ShopItems glasses11 = new ShopItems();
        glasses11.setItemsName("Glasses");
        glasses11.setCost(150);
        glasses11.setBigImage(R.drawable.gl_11);
        glasses11.setImage_item(R.drawable.sgl_11);

        ShopItems glasses12 = new ShopItems();
        glasses12.setItemsName("Glasses");
        glasses12.setCost(150);
        glasses12.setBigImage(R.drawable.gl_12);
        glasses12.setImage_item(R.drawable.sgl_12);

        ShopItems glasses13 = new ShopItems();
        glasses13.setItemsName("Glasses");
        glasses13.setCost(200);
        glasses13.setBigImage(R.drawable.gl_13);
        glasses13.setImage_item(R.drawable.sgl_13);

        ShopItems glasses14 = new ShopItems();
        glasses14.setItemsName("Glasses");
        glasses14.setCost(200);
        glasses14.setBigImage(R.drawable.gl_14);
        glasses14.setImage_item(R.drawable.sgl_14);

        ShopItems glasses15 = new ShopItems();
        glasses15.setItemsName("Glasses");
        glasses15.setCost(200);
        glasses15.setBigImage(R.drawable.gl_15);
        glasses15.setImage_item(R.drawable.sgl_15);

        ShopItems glasses16 = new ShopItems();
        glasses16.setItemsName("Glasses");
        glasses16.setCost(200);
        glasses16.setBigImage(R.drawable.gl_16);
        glasses16.setImage_item(R.drawable.sgl_16);

        ShopItems glasses17 = new ShopItems();
        glasses17.setItemsName("Glasses");
        glasses17.setCost(250);
        glasses17.setBigImage(R.drawable.gl_17);
        glasses17.setImage_item(R.drawable.sgl_17);

        ShopItems glasses18 = new ShopItems();
        glasses18.setItemsName("Glasses");
        glasses18.setCost(250);
        glasses18.setBigImage(R.drawable.gl_18);
        glasses18.setImage_item(R.drawable.sgl_18);

        ShopItems star0 = new ShopItems();
        star0.setItemsName("Stars");
        star0.setStatus(true);
        star0.setCost(0);
        star0.setBigImage(0);
        star0.setImage_item(0);

        ShopItems star1 = new ShopItems();
        star1.setItemsName("Stars");
        star1.setCost(250);
        star1.setBigImage(R.drawable.sm_1);
        star1.setImage_item(R.drawable.sm_1);

        ShopItems star2 = new ShopItems();
        star2.setItemsName("Stars");
        star2.setCost(250);
        star2.setBigImage(R.drawable.sm_2);
        star2.setImage_item(R.drawable.sm_2);

        ShopItems star3 = new ShopItems();
        star3.setItemsName("Stars");
        star3.setCost(250);
        star3.setBigImage(R.drawable.sm_3);
        star3.setImage_item(R.drawable.sm_3);

        ShopItems star4 = new ShopItems();
        star4.setItemsName("Stars");
        star4.setCost(250);
        star4.setBigImage(R.drawable.sm_4);
        star4.setImage_item(R.drawable.sm_4);

        ShopItems star6 = new ShopItems();
        star6.setItemsName("Stars");
        star6.setCost(250);
        star6.setBigImage(R.drawable.sm_6);
        star6.setImage_item(R.drawable.sm_6);

        ShopItems star7 = new ShopItems();
        star7.setItemsName("Stars");
        star7.setCost(250);
        star7.setBigImage(R.drawable.sm_7);
        star7.setImage_item(R.drawable.sm_7);

        ShopItems star8 = new ShopItems();
        star8.setItemsName("Stars");
        star8.setCost(250);
        star8.setBigImage(R.drawable.sm_8);
        star8.setImage_item(R.drawable.sm_8);

        ShopItems star9 = new ShopItems();
        star9.setItemsName("Stars");
        star9.setCost(250);
        star9.setBigImage(R.drawable.sm_9);
        star9.setImage_item(R.drawable.sm_9);

        ShopItems star10 = new ShopItems();
        star10.setItemsName("Stars");
        star10.setCost(250);
        star10.setBigImage(R.drawable.sm_10);
        star10.setImage_item(R.drawable.sm_10);

        ShopItems star11 = new ShopItems();
        star11.setItemsName("Stars");
        star11.setCost(250);
        star11.setBigImage(R.drawable.sm_11);
        star11.setImage_item(R.drawable.sm_11);

        ShopItems star12 = new ShopItems();
        star12.setItemsName("Stars");
        star12.setCost(250);
        star12.setBigImage(R.drawable.sm_12);
        star12.setImage_item(R.drawable.sm_12);

        ShopItems star13 = new ShopItems();
        star13.setItemsName("Stars");
        star13.setCost(250);
        star13.setBigImage(R.drawable.sm_13);
        star13.setImage_item(R.drawable.sm_13);

        ShopItems star14 = new ShopItems();
        star14.setItemsName("Stars");
        star14.setCost(250);
        star14.setBigImage(R.drawable.sm_14);
        star14.setImage_item(R.drawable.sm_14);

        ShopItems star15 = new ShopItems();
        star15.setItemsName("Stars");
        star15.setCost(250);
        star15.setBigImage(R.drawable.sm_15);
        star15.setImage_item(R.drawable.sm_15);

        ShopItems star16 = new ShopItems();
        star16.setItemsName("Stars");
        star16.setCost(250);
        star16.setBigImage(R.drawable.sm_16);
        star16.setImage_item(R.drawable.sm_16);

        ShopItems star17 = new ShopItems();
        star17.setItemsName("Stars");
        star17.setCost(250);
        star17.setBigImage(R.drawable.sm_17);
        star17.setImage_item(R.drawable.sm_17);

        ShopItems star18 = new ShopItems();
        star18.setItemsName("Stars");
        star18.setCost(250);
        star18.setBigImage(R.drawable.sm_18);
        star18.setImage_item(R.drawable.sm_18);

        ShopItems star19 = new ShopItems();
        star19.setItemsName("Stars");
        star19.setCost(250);
        star19.setBigImage(R.drawable.sm_19);
        star19.setImage_item(R.drawable.sm_19);

        ShopItems star20 = new ShopItems();
        star20.setItemsName("Stars");
        star20.setCost(250);
        star20.setBigImage(R.drawable.sm_20);
        star20.setImage_item(R.drawable.sm_20);

        ShopItems star21 = new ShopItems();
        star21.setItemsName("Stars");
        star21.setCost(250);
        star21.setBigImage(R.drawable.sm_21);
        star21.setImage_item(R.drawable.sm_21);

        ShopItems star22 = new ShopItems();
        star22.setItemsName("Stars");
        star22.setCost(250);
        star22.setBigImage(R.drawable.sm_22);
        star22.setImage_item(R.drawable.sm_22);

        ShopItems star23 = new ShopItems();
        star23.setItemsName("Stars");
        star23.setCost(250);
        star23.setBigImage(R.drawable.sm_23);
        star23.setImage_item(R.drawable.sm_23);

        ShopItems star24 = new ShopItems();
        star24.setItemsName("Stars");
        star24.setCost(250);
        star24.setBigImage(R.drawable.sm_24);
        star24.setImage_item(R.drawable.sm_24);

        ShopItems star25 = new ShopItems();
        star25.setItemsName("Stars");
        star25.setCost(250);
        star25.setBigImage(R.drawable.sm_25);
        star25.setImage_item(R.drawable.sm_25);

        ShopItems star26 = new ShopItems();
        star26.setItemsName("Stars");
        star26.setCost(250);
        star26.setBigImage(R.drawable.sm_26);
        star26.setImage_item(R.drawable.sm_26);

        ShopItems basis0 = new ShopItems();
        basis0.setItemsName("Basis");
        basis0.setStatus(true);
        basis0.setCost(0);
        basis0.setBigImage(0);
        basis0.setImage_item(0);

        ShopItems basis1 = new ShopItems();
        basis1.setItemsName("Basis");
        basis1.setCost(50);
        basis1.setBigImage(R.drawable.btm_1);
        basis1.setImage_item(R.drawable.s_btm_1);

        ShopItems basis2 = new ShopItems();
        basis2.setItemsName("Basis");
        basis2.setCost(50);
        basis2.setBigImage(R.drawable.btm_2);
        basis2.setImage_item(R.drawable.s_btm_2);

        ShopItems basis3 = new ShopItems();
        basis3.setItemsName("Basis");
        basis3.setCost(50);
        basis3.setBigImage(R.drawable.btm_3);
        basis3.setImage_item(R.drawable.s_btm_3);

        ShopItems basis4 = new ShopItems();
        basis4.setItemsName("Basis");
        basis4.setCost(50);
        basis4.setBigImage(R.drawable.btm_4);
        basis4.setImage_item(R.drawable.s_btm_4);

        ShopItems basis5 = new ShopItems();
        basis5.setItemsName("Basis");
        basis5.setCost(50);
        basis5.setBigImage(R.drawable.btm_5);
        basis5.setImage_item(R.drawable.s_btm_5);

        ShopItems basis6 = new ShopItems();
        basis6.setItemsName("Basis");
        basis6.setCost(50);
        basis6.setBigImage(R.drawable.btm_6);
        basis6.setImage_item(R.drawable.s_btb_6);

        ShopItems basis7 = new ShopItems();
        basis7.setItemsName("Basis");
        basis7.setCost(50);
        basis7.setBigImage(R.drawable.btm_7);
        basis7.setImage_item(R.drawable.s_btm_7);

        ShopItems color = new ShopItems();
        color.setItemsName("Color");
        color.setStatus(true);
        color.setCost(0);
        color.setBigImage(R.drawable.color3);
        color.setImage_item(R.drawable.color3);

        ShopItems color1 = new ShopItems();
        color1.setItemsName("Color");
        color1.setStatus(true);
        color1.setCost(0);
        color1.setBigImage(R.drawable.color1);
        color1.setImage_item(R.drawable.color1);

        ShopItems color2 = new ShopItems();
        color2.setItemsName("Color");
        color2.setStatus(true);
        color2.setCost(0);
        color2.setBigImage(R.drawable.color2);
        color2.setImage_item(R.drawable.color2);

        ShopItems color3 = new ShopItems();
        color3.setItemsName("Color");
        color3.setStatus(true);
        color3.setCost(0);
        color3.setBigImage(R.drawable.color3);
        color3.setImage_item(R.drawable.color3);

        ShopItems color4 = new ShopItems();
        color4.setItemsName("Color");
        color4.setStatus(true);
        color4.setCost(0);
        color4.setBigImage(R.drawable.color4);
        color4.setImage_item(R.drawable.color4);

        ShopItems color5 = new ShopItems();
        color5.setItemsName("Color");
        color5.setStatus(true);
        color5.setCost(0);
        color5.setBigImage(R.drawable.color5);
        color5.setImage_item(R.drawable.color5);

        ShopItems color6 = new ShopItems();
        color6.setItemsName("Color");
        color6.setStatus(true);
        color6.setCost(0);
        color6.setBigImage(R.drawable.color6);
        color6.setImage_item(R.drawable.color6);

        ShopItems color7 = new ShopItems();
        color7.setItemsName("Color");
        color7.setStatus(true);
        color7.setCost(0);
        color7.setBigImage(R.drawable.color7);
        color7.setImage_item(R.drawable.color7);

        ShopItems color8 = new ShopItems();
        color8.setItemsName("Color");
        color8.setStatus(true);
        color8.setCost(0);
        color8.setBigImage(R.drawable.color8);
        color8.setImage_item(R.drawable.color8);

        ShopItems color9 = new ShopItems();
        color9.setItemsName("Color");
        color9.setStatus(true);
        color9.setCost(0);
        color9.setBigImage(R.drawable.color9);
        color9.setImage_item(R.drawable.color9);

        ShopItems color10 = new ShopItems();
        color10.setItemsName("Color");
        color10.setStatus(true);
        color10.setCost(0);
        color10.setBigImage(R.drawable.color10);
        color10.setImage_item(R.drawable.color10);

        ShopItems color11 = new ShopItems();
        color11.setItemsName("Color");
        color11.setStatus(true);
        color11.setCost(0);
        color11.setBigImage(R.drawable.color11);
        color11.setImage_item(R.drawable.color11);


        ShopItems color13 = new ShopItems();
        color13.setItemsName("Color");
        color13.setStatus(true);
        color13.setCost(0);
        color13.setBigImage(R.drawable.color13);
        color13.setImage_item(R.drawable.color13);

        ShopItems color14 = new ShopItems();
        color14.setItemsName("Color");
        color14.setStatus(true);
        color14.setCost(0);
        color14.setBigImage(R.drawable.color14);
        color14.setImage_item(R.drawable.color14);

        ShopItems color15 = new ShopItems();
        color15.setItemsName("Color");
        color15.setStatus(true);
        color15.setCost(0);
        color15.setBigImage(R.drawable.color15);
        color15.setImage_item(R.drawable.color15);

        ShopItems color16 = new ShopItems();
        color16.setItemsName("Color");
        color16.setStatus(true);
        color16.setCost(0);
        color16.setBigImage(R.drawable.color16);
        color16.setImage_item(R.drawable.color16);

        ShopItems color17 = new ShopItems();
        color17.setItemsName("Color");
        color17.setStatus(true);
        color17.setCost(0);
        color17.setBigImage(R.drawable.color17);
        color17.setImage_item(R.drawable.color17);

        ShopItems color18 = new ShopItems();
        color18.setItemsName("Color");
        color18.setStatus(true);
        color18.setCost(0);
        color18.setBigImage(R.drawable.color18);
        color18.setImage_item(R.drawable.color18);

        ShopItems color19 = new ShopItems();
        color19.setItemsName("Color");
        color19.setStatus(true);
        color19.setCost(0);
        color19.setBigImage(R.drawable.color19);
        color19.setImage_item(R.drawable.color19);

        ShopItems color20 = new ShopItems();
        color20.setItemsName("Color");
        color20.setStatus(true);
        color20.setCost(0);
        color20.setBigImage(R.drawable.color20);
        color20.setImage_item(R.drawable.color20);

        ShopItems color21 = new ShopItems();
        color21.setItemsName("Color");
        color21.setStatus(true);
        color21.setCost(0);
        color21.setBigImage(R.drawable.color21);
        color21.setImage_item(R.drawable.color21);

        ShopItems color22 = new ShopItems();
        color22.setItemsName("Color");
        color22.setStatus(true);
        color22.setCost(0);
        color22.setBigImage(R.drawable.color22);
        color22.setImage_item(R.drawable.color22);

        ShopItems color23 = new ShopItems();
        color23.setItemsName("Color");
        color23.setStatus(true);
        color23.setCost(0);
        color23.setBigImage(R.drawable.color23);
        color23.setImage_item(R.drawable.color23);

        ShopItems color24 = new ShopItems();
        color24.setItemsName("Color");
        color24.setStatus(true);
        color24.setCost(0);
        color24.setBigImage(R.drawable.color24);
        color24.setImage_item(R.drawable.color24);

        ShopItems color25 = new ShopItems();
        color25.setItemsName("Color");
        color25.setStatus(true);
        color25.setCost(0);
        color25.setBigImage(R.drawable.color25);
        color25.setImage_item(R.drawable.color25);

        ShopItems color26 = new ShopItems();
        color26.setItemsName("Color");
        color26.setStatus(true);
        color26.setCost(0);
        color26.setBigImage(R.drawable.color26);
        color26.setImage_item(R.drawable.color26);

        ShopItems color27 = new ShopItems();
        color27.setItemsName("Color");
        color27.setStatus(true);
        color27.setCost(0);
        color27.setBigImage(R.drawable.color27);
        color27.setImage_item(R.drawable.color27);

        ShopItems color28 = new ShopItems();
        color28.setItemsName("Color");
        color28.setStatus(true);
        color28.setCost(0);
        color28.setBigImage(R.drawable.color28);
        color28.setImage_item(R.drawable.color28);

        ShopItems color29 = new ShopItems();
        color29.setItemsName("Color");
        color29.setStatus(true);
        color29.setCost(0);
        color29.setBigImage(R.drawable.color29);
        color29.setImage_item(R.drawable.color29);

        ShopItems color30 = new ShopItems();
        color30.setItemsName("Color");
        color30.setStatus(true);
        color30.setCost(0);
        color30.setBigImage(R.drawable.color30);
        color30.setImage_item(R.drawable.color30);


        MySettings mySettings = new MySettings();
        mySettings.setAnimationPosition(0);
        mySettings.setBright(0.5f);
        mySettings.setBackgroundColor(R.drawable.color3);
        mySettings.createCoinse(15000);
        mySettings.setRate(0);
        mySettings.setAdds(false);
        mySettings.setCurrentMusicPosition(-1);

        realm.copyToRealm(bear);
        realm.copyToRealm(dear);
        realm.copyToRealm(fox);
        realm.copyToRealm(hedgehog);
        realm.copyToRealm(rabbit);
        realm.copyToRealm(squirrel);
        realm.copyToRealm(cheetah);
        realm.copyToRealm(elephan);
        realm.copyToRealm(mySettings);

        realm.copyToRealm(hats0);
        realm.copyToRealm(hats1);
        realm.copyToRealm(hats2);
        realm.copyToRealm(hats3);
        realm.copyToRealm(hats4);
        realm.copyToRealm(hats5);
        realm.copyToRealm(hats6);
        realm.copyToRealm(hats7);
        realm.copyToRealm(hats8);
        realm.copyToRealm(hats9);
        realm.copyToRealm(hats10);
        realm.copyToRealm(hats11);
        realm.copyToRealm(hats12);
        realm.copyToRealm(hats13);
        realm.copyToRealm(hats14);
        realm.copyToRealm(hats15);
        realm.copyToRealm(hats16);

        realm.copyToRealm(glasses0);
        realm.copyToRealm(glasses1);
        realm.copyToRealm(glasses2);
        realm.copyToRealm(glasses3);
        realm.copyToRealm(glasses4);
        realm.copyToRealm(glasses5);
        realm.copyToRealm(glasses6);
        realm.copyToRealm(glasses7);
        realm.copyToRealm(glasses8);
        realm.copyToRealm(glasses9);
        realm.copyToRealm(glasses10);
        realm.copyToRealm(glasses11);
        realm.copyToRealm(glasses12);
        realm.copyToRealm(glasses13);
        realm.copyToRealm(glasses14);
        realm.copyToRealm(glasses15);
        realm.copyToRealm(glasses16);
        realm.copyToRealm(glasses17);
        realm.copyToRealm(glasses18);

        realm.copyToRealm(star0);
        realm.copyToRealm(star1);
        realm.copyToRealm(star2);
        realm.copyToRealm(star3);
        realm.copyToRealm(star4);
        realm.copyToRealm(star6);
        realm.copyToRealm(star7);
        realm.copyToRealm(star8);
        realm.copyToRealm(star9);
        realm.copyToRealm(star10);
        realm.copyToRealm(star11);
        realm.copyToRealm(star12);
        realm.copyToRealm(star13);
        realm.copyToRealm(star14);
        realm.copyToRealm(star15);
        realm.copyToRealm(star16);
        realm.copyToRealm(star17);
        realm.copyToRealm(star18);
        realm.copyToRealm(star19);
        realm.copyToRealm(star20);
        realm.copyToRealm(star21);
        realm.copyToRealm(star22);
        realm.copyToRealm(star23);
        realm.copyToRealm(star24);
        realm.copyToRealm(star25);
        realm.copyToRealm(star26);

        realm.copyToRealm(basis0);
        realm.copyToRealm(basis1);
        realm.copyToRealm(basis2);
        realm.copyToRealm(basis3);
        realm.copyToRealm(basis4);
        realm.copyToRealm(basis5);
        realm.copyToRealm(basis6);
        realm.copyToRealm(basis7);

        realm.copyToRealm(color);
        realm.copyToRealm(color1);
        realm.copyToRealm(color2);
        realm.copyToRealm(color3);
        realm.copyToRealm(color4);
        realm.copyToRealm(color5);
        realm.copyToRealm(color6);
        realm.copyToRealm(color7);
        realm.copyToRealm(color8);
        realm.copyToRealm(color9);
        realm.copyToRealm(color10);
        realm.copyToRealm(color11);
        realm.copyToRealm(color13);
        realm.copyToRealm(color14);
        realm.copyToRealm(color15);
        realm.copyToRealm(color16);
        realm.copyToRealm(color17);
        realm.copyToRealm(color18);
        realm.copyToRealm(color19);
        realm.copyToRealm(color20);
        realm.copyToRealm(color21);
        realm.copyToRealm(color22);
        realm.copyToRealm(color23);
        realm.copyToRealm(color24);
        realm.copyToRealm(color25);
        realm.copyToRealm(color26);
        realm.copyToRealm(color27);
        realm.copyToRealm(color28);
        realm.copyToRealm(color29);
        realm.copyToRealm(color30);


        realm.commitTransaction();

    }
}
