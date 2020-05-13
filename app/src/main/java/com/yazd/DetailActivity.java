package com.yazd;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.ramotion.foldingcell.FoldingCell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DetailAdapter.ItemClickListener {
    DataModel Data;
    DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        int i = getIntent().getIntExtra("id",0);
        Data =  MyData.getAllData(this).get(i);
        //**************    modifying the view ********************
        AppCompatTextView headerText = findViewById(R.id.headerText);
        headerText.setText(Data.name);
        AppCompatImageView headerImage = findViewById(R.id.headerFrontImage);
        headerImage.setImageResource(Data.image);
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //***************************************
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.detail_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DetailAdapter(this, Data.subData,i);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        //******************header back*********************
        setRepeatingBackground(findViewById(R.id.headerPattern));
        setRepeatingBackground(findViewById(R.id.imageView7));
        //***************************************
//        ImageView expandedImage = findViewById(R.id.expandedImage);
//        expandedImage.setImageDrawable(getResources().getDrawable(Data.image));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //********
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Objects.requireNonNull(getSupportActionBar()).setTitle(Data.name);
//        }else {
//
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.transition));
//            expandedImage.setTransitionName("sajjad");
//        }
//            mainImage.setTransitionName(R.string.transition);
        //*******

        //******
//        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
//        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    isShow = true;
////                    showOption(R.id.action_info);
//                } else if (isShow) {
//                    isShow = false;
////                    hideOption(R.id.action_info);
//                }
//            }
//        });
        //*********
//        final FoldingCell fc = findViewById(R.id.folding_cell);
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });
        //*********


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemClick(View view, int position,FoldingCell fc) {
        fc.toggle(true);
//        Intent mi = new Intent(this,DetailActivity2.class);
//        mi.putExtra("position",position);
//        startActivity(mi);
        Toast.makeText(this, "You clicked " + adapter.getItem(position).name + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
    public void setRepeatingBackground(View v){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        v.setBackground(bitmapDrawable);
    }

}


