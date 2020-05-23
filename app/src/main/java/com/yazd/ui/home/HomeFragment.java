package com.yazd.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yazd.AutoFitGridLayoutManager;
import com.yazd.CustomAdapter;
import com.yazd.DataModel;
import com.yazd.Main2Activity;
import com.yazd.MyData;
import com.yazd.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private HomeViewModel homeViewModel;


    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private MenuItem searchMenuItem;
    private SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //********
        myOnClickListener = new MyOnClickListener(getContext());

        recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = MyData.getAllData(getContext());

        removedItems = new ArrayList<Integer>();

        Main2Activity.setAdapter(new CustomAdapter(data));
        recyclerView.setAdapter(Main2Activity.getAdapter());


        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), 500);
        recyclerView.setLayoutManager(layoutManager);
        //********for better scrolling
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        //************************

        return root;
    }


    private class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            nextActivity(v);
        }

        private void nextActivity(View v) {
            int selectedItemPosition = recyclerView.getChildAdapterPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.card_title);
            View viewById = viewHolder.itemView.findViewById(R.id.card_image);
            //*****
//            Intent myIntent = new Intent(getActivity(), DetailActivity.class);
//            myIntent.putExtra("id",getpositiononlist(v));
            //****
//            String transitionName = getString(R.string.transition);

//            ActivityOptionsCompat options =
////
////                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
////                            viewById,   // Starting view
////                            "sajjad"    // The String
////                    );
//            ActivityCompat.startActivity(getActivity(), myIntent, options.toBundle());
            ((Main2Activity)getActivity()).startNextActivty(viewById,getpositiononlist(v));
            //*******
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                getActivity().finishAfterTransition();
//            }else {
//                getActivity().finish();
//            }
        }
        private int getpositiononlist(View v){
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.card_title);
            String selectedName = (String) textViewName.getText();

            int selectedItemId = 0;
            ArrayList<DataModel> allData = MyData.getAllData(context);
            for (int i = 0; i < allData.size(); i++) {
                if (selectedName.equals(allData.get(i).getName())) {
                    selectedItemId = allData.get(i).getId();
                }
            }
            return selectedItemId;
        }

//        private void removeItem(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.card_text);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < MyData.nameArray.length; i++) {
//                if (selectedName.equals(MyData.nameArray[i])) {
//                    selectedItemId = MyData.id_[i];
//                }
//            }
//            removedItems.add(selectedItemId);
//            data.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
    }

}