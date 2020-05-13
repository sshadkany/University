//package com.yazd;
//
//import android.app.SearchManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.SearchView;
//import android.widget.TextView;
//
//
//import java.util.ArrayList;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.ActivityOptionsCompat;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
//
//    private static CustomAdapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private static RecyclerView recyclerView;
//    static ArrayList<DataModel> data;
//    static View.OnClickListener myOnClickListener;
//    private static ArrayList<Integer> removedItems;
//    private MenuItem searchMenuItem;
//    private SearchView searchView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        myOnClickListener = new MyOnClickListener(this);
//
//        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
////        data = new ArrayList<DataModel>();
////        for (int i = 0; i < MyData.nameArray.length; i++) {
////            data.add(new DataModel(
////                    MyData.nameArray[i],
////                    MyData.SubDataCount[i],
////                    MyData.id_[i],
////                    MyData.drawableArray[i]
////            ));
////        }
//
//        removedItems = new ArrayList<Integer>();
//
//        adapter = new CustomAdapter(data);
//        recyclerView.setAdapter(adapter);
//
//
//        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
//        recyclerView.setLayoutManager(layoutManager);
//
//
//
//    }
//
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        adapter.getFilter().filter(newText);
//        return false;
//    }
//
//
//    private class MyOnClickListener implements View.OnClickListener {
//
//        private final Context context;
//
//        private MyOnClickListener(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void onClick(View v) {
//            nextActivity(v);
//        }
//
//        private void nextActivity(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.card_title);
//            View viewById = viewHolder.itemView.findViewById(R.id.card_image);
//            //*****
//            Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
//            myIntent.putExtra("id",getpositiononlist(v));
//            //****
////            String transitionName = getString(R.string.transition);
//
//            ActivityOptionsCompat options =
//
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
//                            viewById,   // Starting view
//                            "sajjad"    // The String
//                    );
//
//            ActivityCompat.startActivity(MainActivity.this, myIntent, options.toBundle());
//            //*******
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                finishAfterTransition();
//            }else {
//                finish();
//            }
//        }
//        private int getpositiononlist(View v){
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.card_title);
//            String selectedName = (String) textViewName.getText();
//
//            int selectedItemId = 0;
//            for (int i = 0; i < MyData.nameArray.length; i++) {
//                if (selectedName.equals(getResources().getString(MyData.nameArray[i]))) {
//                    selectedItemId = MyData.id_[i];
//                }
//            }
//            return selectedItemId;
//        }
//
////        private void removeItem(View v) {
////            int selectedItemPosition = recyclerView.getChildPosition(v);
////            RecyclerView.ViewHolder viewHolder
////                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
////            TextView textViewName
////                    = (TextView) viewHolder.itemView.findViewById(R.id.card_text);
////            String selectedName = (String) textViewName.getText();
////            int selectedItemId = -1;
////            for (int i = 0; i < MyData.nameArray.length; i++) {
////                if (selectedName.equals(MyData.nameArray[i])) {
////                    selectedItemId = MyData.id_[i];
////                }
////            }
////            removedItems.add(selectedItemId);
////            data.remove(selectedItemPosition);
////            adapter.notifyItemRemoved(selectedItemPosition);
////        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu, menu);
//
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(this);
//
//
//        return true;
//    }
//
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        super.onOptionsItemSelected(item);
////        if (item.getItemId() == R.id.add_item) {
////            //check if any items to add
////            if (removedItems.size() != 0) {
////                addRemovedItemToList();
////            } else {
////                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
////            }
////        }
////        return true;
////    }
//
////    private void addRemovedItemToList() {
////        int addItemAtListPosition = 3;
////        data.add(addItemAtListPosition, new DataModel(
////                MyData.nameArray[removedItems.get(0)],
////                MyData.versionArray[removedItems.get(0)],
////                MyData.id_[removedItems.get(0)],
////                MyData.drawableArray[removedItems.get(0)]
////        ));
////        adapter.notifyItemInserted(addItemAtListPosition);
////        removedItems.remove(0);
////    }
//
//}
//
