package com.appsforkids.pasz.lullabies.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.appsforkids.pasz.lullabies.DownloadImageTask;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import fr.nicolaspomepuy.discreetapprate.AppRate;
import io.realm.Realm;


public class RateFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.rate_app, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView rate_img =  (ImageView) view.findViewById(R.id.rate_img);
        CheckBox checkBox =  (CheckBox) view.findViewById(R.id.checkBox);

        new DownloadImageTask(rate_img).execute("https://raw.githubusercontent.com/iampasz/koko-oko/main/rate.jpg");


        FrameLayout rate_me= (FrameLayout) view.findViewById(R.id.rate_me) ;
        FrameLayout rate_never= (FrameLayout) view.findViewById(R.id.rate_never) ;



        final String packageName = getActivity().getPackageName();

        rate_me.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName));
                getActivity().startActivity(intent);

                getParentFragmentManager().beginTransaction().remove(RateFragment.this).commit();

                if(checkBox.isChecked()){
                    rateNever();
                }

            }
        });


        rate_never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getParentFragmentManager().beginTransaction().remove(RateFragment.this).commit();

                if(checkBox.isChecked()){
                    rateNever();
                }
            }
        });





    }

    public void rateNever(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();

        settings.setRate(-1);


        realm.commitTransaction();
    }
}
