package com.doldam;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class AttentionFragment extends Fragment{

    private int[] imageIDs = new int[]{
            R.drawable.team7,
            R.drawable.team9,
            R.drawable.team5,
            R.drawable.team4,
            R.drawable.team10,
            R.drawable.team1,
            R.drawable.team2,
            R.drawable.team8,
            R.drawable.team3,
            R.drawable.team11,
            R.drawable.team6,
            R.drawable.team12,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attention, null);

        GridView gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(getContext(), imageIDs);
        gridViewImages.setAdapter(imageGridAdapter);

        return view;
    }


}

