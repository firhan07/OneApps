package com.example.apprecycleviewhorizontal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    public static ArrayList<String> mDeskripsi = new ArrayList<>();
    private ArrayList<String> mImageTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;

    public HorizontalAdapter(Context mContext, ArrayList<String> mImageTitles, ArrayList<String> mImages,  ArrayList<String> mDeskripsi) {
        this.mImageTitles = mImageTitles;
        this.mImages = mImages;
        this.mDeskripsi = mDeskripsi;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_horizontal_item, parent, false);
        HorizontalAdapter.ViewHolder holder = new HorizontalAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(viewHolder.image);

        viewHolder.imageTitle.setText(mImageTitles.get(position));

    }

    @Override
    public int getItemCount() {
        return mImageTitles.size();
    }

    public void setOnItemClicListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView imageTitle;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageTitle = itemView.findViewById(R.id.image_title);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
