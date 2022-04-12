package com.appsforkids.pasz.lullabies.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.MyInterfaces.ChangeImage;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.Nightlight;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainPagerAdapter extends RecyclerView.Adapter<MainPagerAdapter.MyViewHolder> {

    RealmResults nightlightersArrayList;
    ChangeImage changeImage;
    Boolean first = false;

    RealmResults<Nightlight> nightlightAll;


      public MainPagerAdapter(RealmResults nightlightersArrayList, Context ctx){
        this.nightlightersArrayList = nightlightersArrayList;
        this.changeImage = changeImage;

          nightlightAll = getNightlightAll(ctx);

    }


    class MyViewHolder extends RecyclerView.ViewHolder{
          ImageView itemNightlight;



        ImageView sh_nightlight;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNightlight = (ImageView) itemView.findViewById(R.id.big_nightlight);
            sh_nightlight = (ImageView) itemView.findViewById(R.id.sh_nightlight);

           // Log.i("MyLog", "MyViewHolder");
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);



       // Log.i("MyLog", "onCreateViewHolder");

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

          Nightlight my_nightlight = (Nightlight) nightlightersArrayList.get(position);

          holder.sh_nightlight.setImageResource(my_nightlight.sh_nightlight);
          holder.itemNightlight.setImageResource(my_nightlight.nightlight);

          Log.i("MyLog", "My position in Main Pager Adapter is: "+ position);

                   if(my_nightlight.status){


                }else{
                     // holder.itemNightlight.setColorFilter(Color.parseColor("#C5000000"));
                      //Log.i("MyLog", "Только что я затемнил: " + position);
                      //holder.itemNightlight.getDrawable().setColorFilter(Color.parseColor("#C5000000"), PorterDuff.Mode.SRC_ATOP);



              }

        //Log.i("Play", "hi "+ nightlightAll);

        if(nightlightAll.get(holder.getAdapterPosition()).getStatus()){
           // holder.itemNightlight.setColorFilter(null);
            Log.i("Play", nightlightAll.get(holder.getAdapterPosition()).getStatus()+"  А сейчас тут " + holder.getAdapterPosition() + "");
        }else{

            //holder.itemNightlight.setColorFilter(R.color.colorPrimary);
            Log.i("Play", nightlightAll.get(holder.getAdapterPosition()).getStatus()+" Мы тут " + holder.getAdapterPosition());
        }


        //holder.sh_nightlight.setVisibility(View.INVISIBLE);

    }


    @Override
    public int getItemCount() {
        return nightlightersArrayList.size();
    }


    public RealmResults<Nightlight> getNightlightAll(Context ctx){

        Realm.init(ctx);
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        nightlightAll = realm.where(Nightlight.class).findAll();
        realm.commitTransaction();
        return nightlightAll;
    }

  }