package com.yazd;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.yazd.ui.home.HomeFragment;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>implements Filterable{

    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> complatedataSet;
    private Context context;
    private DataFilter dataFilter;
    private BitmapFactory.Options options;
    private Resources resources;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.card_title);
            this.textViewVersion = (TextView) itemView.findViewById(R.id.card_text);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
        this.complatedataSet = data;
        getFilter();
    }

    @Override
    public Filter getFilter() {
        if (dataFilter == null) {
            dataFilter = new DataFilter();
        }

        return dataFilter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        view.setOnClickListener(HomeFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        resources = context.getResources();


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

//        ShapeAppearanceModel.Builder corners = ShapeAppearanceModel.builder().setAllCorners(new RoundedCornerTreatment((float) 5));
//        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(corners.build());
//        materialShapeDrawable.setTint(context.getResources().getColor(R.color.dark_grey));
//        materialShapeDrawable.setPaintStyle(Paint.Style.FILL);
//
//        textViewName.setBackground(materialShapeDrawable);

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(String.valueOf(dataSet.get(listPosition).subDataCount()));

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, dataSet.get(listPosition).getImage(),options);
        imageView.setImageBitmap(bitmap);
//        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private class DataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<DataModel> tempList = new ArrayList<DataModel>();

                // search content in friend list
                for (DataModel data : complatedataSet) {

                    if (data.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(data);
                    }
                }

                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = complatedataSet.size();
                filterResults.values = complatedataSet;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet = (ArrayList<DataModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
