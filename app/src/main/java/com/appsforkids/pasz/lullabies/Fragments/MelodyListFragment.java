package com.appsforkids.pasz.lullabies.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsforkids.pasz.lullabies.Adapters.ListMusicAdapter;
import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.MyInterfaces.PlayMyMusic;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.AudioFile;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MelodyListFragment extends Fragment {

    ImageView close_button;
    ListMusicAdapter listMusicAdapter;
    RecyclerView rv_melody;
    int currentMusicPosition = -1;
    RealmResults<AudioFile> audioFileAll;


    @Nullable
    @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //Set View container and add buterknife library
        View view = inflater.inflate(R.layout.my_music_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        rv_melody = (RecyclerView) view.findViewById(R.id.rv_images);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_melody.setLayoutManager(llm);




        listMusicAdapter = new ListMusicAdapter(new PlayMyMusic() {
            @Override
            public void pressPosition(int position, Boolean play_status) {
                ((MainActivity)getActivity()).playMusic(audioFileAll.get(position).getId(), play_status);

                if(play_status){
                    currentMusicPosition = position;
                }else{
                    currentMusicPosition = -1;
                }

            }

        }, getContext());

        rv_melody.setAdapter(listMusicAdapter);

        //Set current music position
        setSettings();

        MyObjects myObjects = new MyObjects(getContext());
        audioFileAll = myObjects.getMelodyArrayList();

        //Find button and Close Melody list Fragment after click
        close_button = (ImageView) view.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(MelodyListFragment.this).commit();
            }
        });
    }

   //Play choosing melody in Main Activity
   public void playMusic(int musicId){
        ((MainActivity)getActivity()).playMusic(musicId, true);
    };

   //Save current music position
   private void saveSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        settings.setCurrentMusicPosition(currentMusicPosition);
        realm.commitTransaction();
        Log.i("MelodyListFragment",  "saveSettings in MelodyListFragment. Current position was save. CurrentPosition is "+ currentMusicPosition);
    }

   //Get currentMusicPosition from saved settings
   public void setSettings(){
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MySettings settings = realm.where(MySettings.class).findFirst();
        currentMusicPosition = settings.getCurrentMusicPosition();
        realm.commitTransaction();
    }

    @Override
   public void onDestroy() {
        super.onDestroy();
        saveSettings();
        Log.i("MelodyListFragment",  "onDestroy in MelodyListFragment");
    }
}