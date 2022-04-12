package com.appsforkids.pasz.lullabies.Fragments;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appsforkids.pasz.lullabies.Adapters.ShopAdapter;
import com.appsforkids.pasz.lullabies.MainActivity;
import com.appsforkids.pasz.lullabies.MyInterfaces.GetActionFromAdapter;
import com.appsforkids.pasz.lullabies.MyObjects;
import com.appsforkids.pasz.lullabies.R;
import com.appsforkids.pasz.lullabies.RealmObjects.ShopItems;

import java.util.ArrayList;

import io.realm.RealmResults;

public class ShopFragment extends Fragment  {

    GridLayoutManager gm;

    NightlightFragment.ChoseItem choseItem;

    public static ShopFragment init(String items){
        ShopFragment shopFragment = new ShopFragment();
        Bundle bundle = new Bundle();
        bundle.putString("items", items);
        shopFragment.setArguments(bundle);
        return shopFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.shop_item_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int spanCount = 4;

        gm = new GridLayoutManager(getContext(),spanCount, RecyclerView.VERTICAL, false);

        RecyclerView rv_cards = view.findViewById(R.id.rv_cards);
        rv_cards.setLayoutManager(gm);

        MyObjects myObjects = new MyObjects(getContext());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.widthPixels;
        height = height/spanCount;


        RealmResults realmResults;

            switch (getArguments().getString("items")){
                case "Hats":
                    realmResults = myObjects.getHatsArrayList();
                    break;
                case "Glasses":
                    realmResults = myObjects.getGlassesArrayList();
                    break;
                case "Stars":
                    realmResults = myObjects.getStarsArrayList();
                    break;
                case "Basis":
                    realmResults = myObjects.getBasisArrayList();
                    break;
                case "Nightlighters":
                    realmResults = myObjects.getNightlightersArrayList();
                    break;
                case "Color":
                    realmResults = myObjects.getColorsArrayList();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + getArguments().getString("items"));
            }


        rv_cards.setAdapter(new ShopAdapter(realmResults, height, new GetActionFromAdapter() {
            @Override
            public void usePosition(int position) {
                closeFragment(position);
            }

            @Override
            public void doAnother(int position) {

                ((MainActivity)getActivity()).renewCoins(0);

            }
        }));

        ImageView close_button = view.findViewById(R.id.close_button);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getParentFragmentManager().beginTransaction().remove(ShopFragment.this).commit();

            }
        });

    }


    public void setCallBack(NightlightFragment.ChoseItem choseItem){
        this.choseItem = choseItem;
    }

    public void closeFragment(int currentItemPosition){
        getParentFragmentManager().beginTransaction().remove(ShopFragment.this).commit();
        choseItem.setImage(currentItemPosition);
    }
}
