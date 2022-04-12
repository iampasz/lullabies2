package com.appsforkids.pasz.lullabies.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.Fragments.UnlockFragment;
import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.MyInterfaces.GetActionFromAdapter;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.MySettings;
import com.appsforkids.pasz.lullabies.RealmObjects.ShopItems;
import com.easyandroidanimations.library.ShakeAnimation;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder>{

    RealmResults<ShopItems> items;
    int size;
    GetActionFromAdapter getItemPosition;


    public ShopAdapter(RealmResults<ShopItems> items, int size, GetActionFromAdapter getItemPosition){
        this.items = items;
        this.size = size;
        this.getItemPosition = getItemPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item, parent, false);
        //renewPoint(true, view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.con_lay_items.setLayoutParams(new ConstraintLayout.LayoutParams(size, size));

        holder.con_lay_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Realm.init(holder.price_text.getContext());
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    MySettings mySettings = realm.where(MySettings.class).findFirst();
                    int myCoins =  mySettings.getCoins();


                    if(items.get(holder.getAdapterPosition()).getStatus()){
                        realm.commitTransaction();
                        getItemPosition.usePosition(holder.getAdapterPosition());
                    }else{
                        if(myCoins>=items.get(holder.getAdapterPosition()).getCost()){

                            mySettings.addCoins(items.get(holder.getAdapterPosition()).getCost()*(-1));

                            holder.item_img.setImageResource(items.get(holder.getAdapterPosition()).getImage_item());
                            holder.price_text.setText("");
                            items.get(holder.getAdapterPosition()).setStatus(true);

                            realm.commitTransaction();

                            getItemPosition.doAnother(holder.getAdapterPosition());


                        }else{
                            new ShakeAnimation(holder.price_text).setDuration(150).animate();
                            realm.commitTransaction();
                        }

                    }
            }
        });

        if(items.get(position).status){
            holder.item_img.setImageResource(items.get(position).getImage_item());
        }else{
            holder.price_text.setText(items.get(position).getCost()+"");
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView shop_item_bg;
        ImageView item_img;
        TextView price_text;
        ConstraintLayout con_lay_items;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shop_item_bg = itemView.findViewById(R.id.shop_item_bg);
            item_img = itemView.findViewById(R.id.item_img);
            price_text = itemView.findViewById(R.id.price_text);
            con_lay_items = itemView.findViewById(R.id.con_lay_items);
        }

    }


}
