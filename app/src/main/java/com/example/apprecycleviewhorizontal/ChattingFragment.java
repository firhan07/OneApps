package com.example.apprecycleviewhorizontal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static java.security.AccessController.getContext;

public class ChattingFragment extends Fragment {


    private ImagesData mImagesData;

    public ChattingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mImagesData = new ImagesData();
        mImagesData.initImageBitmaps();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        CircleAdapter adapter = new CircleAdapter(getContext(), mImagesData.getmTitles(),
                mImagesData.getmImageUrls());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}