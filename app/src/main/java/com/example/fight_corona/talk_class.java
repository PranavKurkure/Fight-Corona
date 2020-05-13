package com.example.fight_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class talk_class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
