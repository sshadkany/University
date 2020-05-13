package com.yazd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final View vi;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        View login = findViewById(R.id.login_button);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            public void run() {
                onclickbutton();
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((MotionLayout)findViewById(R.id.motion_login)).transitionToEnd();
                handler.postDelayed(runnable, 200);
            }
        });
        setRepeatingBackground(findViewById(R.id.imageView5));
    }
    public void setRepeatingBackground(View v){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tile);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        v.setBackground(bitmapDrawable);
    }

    public static void fixBackgroundRepeat(View view) {
        Drawable bg = view.getBackground();
        if (bg != null) {
            if (bg instanceof BitmapDrawable) {
                BitmapDrawable bmp = (BitmapDrawable) bg;
                bmp.mutate(); // make sure that we aren't sharing state anymore
                bmp.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            ((MotionLayout)findViewById(R.id.motion_login)).transitionToEnd();
        else
            ((MotionLayout)findViewById(R.id.motion_login)).transitionToStart();
        return super.onTouchEvent(event);

    }

    public void onclickbutton(){
        startActivity(new Intent(LoginActivity.this, Main2Activity.class));
        finish();
    }
}
