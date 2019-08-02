package com.example.apprecycleviewhorizontal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class GiftsFragment extends Fragment {

    private ImagesData mImagesData;

    public GiftsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mImagesData = new ImagesData();
        mImagesData.initImageBitmaps();

        View view = inflater.inflate(R.layout.fragment_types, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecycleViewAdapter adapter = new RecycleViewAdapter(getContext(),mImagesData.getmTitles(),
                mImagesData.getmImageUrls());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;

    }
}
