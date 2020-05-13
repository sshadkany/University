package com.yazd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

public class DetailActivity2 extends AppCompatActivity {
    DataModel Data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_2);
        //***************************************
        int i1 = getIntent().getIntExtra("i1", 0);
        int i2 = getIntent().getIntExtra("i2", 0);
        int i3 = getIntent().getIntExtra("i3", 0);
//        Data =  MyData.getAllData(this).get(i1).getSubData().get(i2).getSubData().get(i3);
        Data = MyData.getAllData(this).get(i1);
        String p1 = Data.name;
        String p2 = Data.getSubData().get(i2).name;
        Data = Data.getSubData().get(i2).getSubData().get(i3);
        //**************************************
        AppCompatTextView headerText = findViewById(R.id.headerText);
        AppCompatTextView nav1 = findViewById(R.id.nav_1_text);
        AppCompatTextView nav2 = findViewById(R.id.nav_1_text2);
        headerText.setText(Data.name);
        if (p1.length() > 10) {
            nav1.setText(String.format("%s...", p1.substring(0, 10)));
        }else{
            nav1.setText(p1);
        }

        if (p2.length() > 10) {
            nav2.setText(String.format("%s...", p2.substring(0, 10)));
        }else{
            nav2.setText(p2);
        }
        //************************************
        setRepeatingBackground(findViewById(R.id.headerPattern));

    }
    public void setRepeatingBackground(View v){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        v.setBackground(bitmapDrawable);
    }
}