package com.example.apprecycleviewhorizontal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KaosAdapter extends RecyclerView.Adapter<KaosAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<KaosDataModel> imageModelArrayList;
    private View.OnClickListener mOnItemClickListener;

    public KaosAdapter(Context ctx, ArrayList<KaosDataModel> imageModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public KaosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(KaosAdapter.MyViewHolder holder, int position) {
        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.item.setText(imageModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}