package com.example.apprecycleviewhorizontal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mImageTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    public CircleAdapter(Context mContext, ArrayList<String> mImageTitles, ArrayList<String> mImages) {
        this.mImageTitles = mImageTitles;
        this.mImages = mImages;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_circle_item,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(viewHolder.image);
        viewHolder.imageTitle.setText(mImageTitles.get(position));

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageTitles.get(position));
                Toast.makeText(mContext,mImageTitles.get(position), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mContext, Chatinfo.class);
                i.putExtra("title", mImageTitles.get(position));
                i.putExtra("image",mImages.get(position));
                mContext.startActivity(i);
            }
        });
        viewHolder.imageTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mImageTitles.get(position));
                Toast.makeText(mContext,mImageTitles.get(position), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mContext, ChatActivity.class);
                i.putExtra("title", mImageTitles.get(position));
                i.putExtra("image",mImages.get(position));
                mContext.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mImageTitles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView imageTitle,imageTitle2;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageTitle = itemView.findViewById(R.id.image_title);
            imageTitle2 = itemView.findViewById(R.id.image_title_2);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}