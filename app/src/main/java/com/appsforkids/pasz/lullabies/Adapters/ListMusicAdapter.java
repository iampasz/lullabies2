package com.appsforkids.pasz.lullabies.Adapters;//package com.appsforkids.pasz.lullabies;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.MyInterfaces.PlayMyMusic;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.AudioFile;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListMusicAdapter extends RecyclerView.Adapter<ListMusicAdapter.ListMusicHolder> {

    PlayMyMusic playMyMusic;
    RealmResults<AudioFile> audioFileAll;
    int pressedPosition = -1;
    int currentMusicPosition = -1;

    public ListMusicAdapter(PlayMyMusic playMyMusic, Context ctx){
        this.playMyMusic = playMyMusic;
        audioFileAll = getAudios(ctx);
    };

    @NonNull
    @Override
    public ListMusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_music, parent, false);
        return new ListMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicHolder holder, int position) {

        //holder.play_item.setTag(UUID.randomUUID().toString());
        holder.music_name.setText(audioFileAll.get(holder.getAdapterPosition()).nameSong);
        holder.music_author.setText(audioFileAll.get(holder.getAdapterPosition()).authorSong);




        holder.play_item.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {

               //Log.i("Play", position +" it is i, and  listMusicHolder.getAdapterPosition() " +holder.getAdapterPosition());
               currentMusicPosition=-1;

               if(pressedPosition==holder.getAdapterPosition()){
                   //holder.play_item.setImageResource(R.drawable.bt_play);
                   Log.i("Play", pressedPosition+ " тут должен плей поставить " +  holder.getAdapterPosition());
                   playMyMusic.pressPosition(holder.getAdapterPosition(), false);
                   pressedPosition=-1;

               }else{
                   playMyMusic.pressPosition(holder.getAdapterPosition(), true);
                   //holder.play_item.setImageResource(R.drawable.bt_pause);
                   Log.i("Play", pressedPosition+ " тут должен стоп поставить " +  holder.getAdapterPosition());
                   pressedPosition =  holder.getAdapterPosition();
               }
               notifyDataSetChanged();
            }
        });
        //Log.i("Play", pressedPosition +" presed and new " +holder.getAdapterPosition());
        if(holder.getAdapterPosition()==pressedPosition){
            holder.play_item.setImageResource(R.drawable.bt_pause);

        }else{
            holder.play_item.setImageResource(R.drawable.bt_play);
        }

        Log.i("ListMusicAdapter", currentMusicPosition+" currentMusicPosition and holder.getAdapterPosition() "+ holder.getAdapterPosition());

        if(holder.getAdapterPosition()==currentMusicPosition && currentMusicPosition!=-1){
            holder.play_item.setImageResource(R.drawable.bt_pause);
            Log.i("ListMusicAdapter", currentMusicPosition+" we are here");

        }
    }

    @Override
    public int getItemCount() {
        return audioFileAll.size();
    }

    public class ListMusicHolder extends RecyclerView.ViewHolder {

    private ImageView play_item;
    private TextView music_name;
    private TextView music_author;

        public ListMusicHolder(@NonNull View itemView) {
            super(itemView);

            play_item = itemView.findViewById(R.id.play_item);
            music_name = itemView.findViewById(R.id.music_name);
            music_author = itemView.findViewById(R.id.music_author);

        }
    }

        public RealmResults<AudioFile> getAudios(Context ctx){

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        audioFileAll = realm.where(AudioFile.class).findAll();
        pressedPosition = realm.where(MySettings.class).findFirst().getCurrentMusicPosition();
        realm.commitTransaction();
        return audioFileAll;
    }
}
