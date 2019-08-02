package com.example.apprecycleviewhorizontal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        CircleImageView civ = view.findViewById(R.id.btnTakePicture);
        CardView cardView = view.findViewById(R.id.Account);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AccountActivity.class);
                startActivity(i);
            }
        });

        cardView = view.findViewById(R.id.Pesanan);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PesananActivity.class);
                startActivity(i);
            }
        });

        cardView = view.findViewById(R.id.Penjualan);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PenjualanActivity.class);
                startActivity(i);
            }
        });

        cardView = view.findViewById(R.id.Bantuan);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), BantuanActivity.class);
                startActivity(i);
            }
        });


        civ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
