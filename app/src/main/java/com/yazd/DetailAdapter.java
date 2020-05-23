package com.yazd;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.foldingcell.FoldingCell;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<DataModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;
    private int i1;
    ViewGroup add_view;

    // data is passed into the constructor
    DetailAdapter(Context context, List<DataModel> data, int i) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        i1 = i;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.detail_folding_card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = mData.get(position).name;
        holder.title1.setText(name);
        holder.title2.setText(name);
        if (mData.get(position).getSubDataCount()>0) {
            ArrayList<DataModel> subData = mData.get(position).getSubData();
            for (int i = 0; i < subData.size(); i++) {
                View inflate = mInflater.inflate(R.layout.add_to_card, null);
                TextView tv = inflate.findViewById(R.id.cell_content_text_card);
                tv.setText(subData.get(i).name);
                inflate.setOnClickListener(new onInnerItemClick(subData,position,i));
                add_view.addView(inflate);
            }

        }else {
            holder.title1.setOnClickListener(new onInnerItemClick(position));
            holder.title2.setOnClickListener(new onInnerItemClick(position));
        }
    }

    public class onInnerItemClick implements View.OnClickListener {
        ArrayList<DataModel> subData;
        int position;
        int i=1000;
        onInnerItemClick(ArrayList<DataModel> subData, int position, int i){
            this.i = i;
            this.subData = subData;
            this.position=position;
        }
        onInnerItemClick(int position){
            this.position=position;
        }

        @Override
        public void onClick(View v) {
            Intent mi = new Intent(context,DetailActivity2.class);
            mi.putExtra("i1",i1);
            mi.putExtra("i2",position);
            mi.putExtra("i3",i);
            context.startActivity(mi);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View fc_close;
        View fc_title;
        TextView title1;
        TextView title2;
        FoldingCell fc;

        ViewHolder(View itemView) {
            super(itemView);
            title1 = itemView.findViewById(R.id.cell_title_text);
            title2 = itemView.findViewById(R.id.cell_content_text);
            fc = itemView.findViewById(R.id.folding_cell);
            add_view = itemView.findViewById(R.id.add_view);
            fc_title = itemView.findViewById(R.id.cell_title_view);
            fc_close = itemView.findViewById(R.id.fc_close);
            fc_title.setOnClickListener(this);
            fc_close.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition(),fc);
        }
    }

    // convenience method for getting data at click position
    DataModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position,FoldingCell fc);
    }
}