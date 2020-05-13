package com.example.fight_corona;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class News_Feed extends AppCompatActivity {

    TextView mTitleTv,mDescTv;
    GifImageView mImageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__feed);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        //ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.title);
        mDescTv  = findViewById(R.id.description);
        mImageIv = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("iTitle");
        String mDescription = intent.getStringExtra("iDesc");
        //actionBar.setTitle(mTitle);
        mTitleTv.setText(mTitle);
        mDescTv.setText(mDescription);
        if(mTitle.equals("Wear Mask")){
            mImageIv.setImageResource(R.drawable.mask);
        }else if(mTitle.equals("Don't Touch")){
            mImageIv.setImageResource(R.drawable.touch);
        }else if(mTitle.equals("Wash Hands")){
            mImageIv.setImageResource(R.drawable.wash);
        }else if(mTitle.equals("Safe Distance")){
            mImageIv.setImageResource(R.drawable.safedistance);
        }else if(mTitle.equals("Flat Curve")){
            mImageIv.setImageResource(R.drawable.curveflat);
        }else if(mTitle.equals("Google")){
            mImageIv.setImageResource(R.drawable.google);
        }


    }
}
