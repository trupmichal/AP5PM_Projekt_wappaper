package com.example.wappaper;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liuguangqiang.swipeback.SwipeBackActivity;

import java.io.IOException;
import java.util.Objects;

public class set_homescreen extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button set;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_homescreen);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        set=findViewById(R.id.set);
        imageView=findViewById(R.id.home_image);
        intent=getIntent();

        String url=intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        set.setOnClickListener(v -> {

            try {
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                wallpaperManager.setBitmap(bitmap);
                Toast.makeText(getApplicationContext(),"Wallpaper Set!", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }



        });
    }
}