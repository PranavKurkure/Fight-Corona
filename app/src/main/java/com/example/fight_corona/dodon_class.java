package com.example.fight_corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class dodon_class extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodon_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this, getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }


    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Wear Mask");
        m.setDescription("This is wear mask description");
        m.setImg(R.drawable.mask);
        models.add(m);

        m = new Model();
        m.setTitle("Take Rest");
        m.setDescription("This is take rest description");
        m.setImg(R.drawable.rest);
        models.add(m);

        m = new Model();
        m.setTitle("Don't Touch");
        m.setDescription("This is don't touch description");
        m.setImg(R.drawable.touch);
        models.add(m);

        m = new Model();
        m.setTitle("Wash Hands");
        m.setDescription("This is wash hands description");
        m.setImg(R.drawable.wash);
        models.add(m);

        m = new Model();
        m.setTitle("Safe Distance");
        m.setDescription("This is safe distance description");
        m.setImg(R.drawable.safedistance);
        models.add(m);

        m = new Model();
        m.setTitle("Flat Curve");
        m.setDescription("This is falt curve description");
        m.setImg(R.drawable.curveflat);
        models.add(m);

        m = new Model();
        m.setTitle("Google");
        m.setDescription("This is google description");
        m.setImg(R.drawable.google);
        models.add(m);

        return models;

    }
}
