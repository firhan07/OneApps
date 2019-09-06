package com.example.apprecycleviewhorizontal;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StaggeredFragment extends Fragment {
    private final ImagesData id = new ImagesData();
    private static final int NUM_COLUMNS = 2;

    public StaggeredFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        id.initImageBitmaps();

        View view = inflater.inflate(R.layout.fragment_staggered, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(getContext(), id.getmTitles(), id.getmImageUrls(),
        id.getmDeskripsi());
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);

        return view;
    }

}