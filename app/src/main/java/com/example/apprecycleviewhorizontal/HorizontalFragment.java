package com.example.apprecycleviewhorizontal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;




public class HorizontalFragment extends Fragment {

    private ImagesData mImagesData;
    int position;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            position = viewHolder.getAdapterPosition();
            String s = mImagesData.getImagePosition(position);
            TextView tvHarga = getActivity().findViewById(R.id.tvHarga);
            tvHarga.setText("Rp. " + mImagesData.getHarga(position));
            ImageView iv1 = getActivity().findViewById(R.id.iv1);
            Glide.with(getContext())
                    .asBitmap()
                    .load(s)
                    .into(iv1);
        }
    };

    public HorizontalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mImagesData = new ImagesData();
        mImagesData.initImageBitmaps();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horizontal, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_horizontal);
        HorizontalAdapter adapter = new HorizontalAdapter(getContext(), mImagesData.getmTitles(),
                mImagesData.getmImageUrls());
        recyclerView.setAdapter(adapter);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter.setOnItemClicListener(onItemClickListener);

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvHarga = getActivity().findViewById(R.id.tvHarga);
                String harga = tvHarga.getText().toString();
                Intent i = new Intent(getContext(), OrderDetail.class);
                i.putExtra("tv", harga);
                i.putExtra("gambar", mImagesData.getImagePosition(position));
                getContext().startActivity(i);
            }
        });
        return view;
    }
}
