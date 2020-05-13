package com.example.fight_corona;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import pl.droidsonroids.gif.InputSource;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Model> models;

    public MyAdapter(Context c,ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDes.setText(models.get(position).getDescription());
        holder.mImaeView.setImageResource(models.get(position).getImg());


        holder.setItemClickListener(new ItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemClickListener(View v, int position) {
                String gTitle,gDesc;
               // Intent intent;

                if(models.get(position).getTitle().equals("Wear Mask")) {
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Don't Touch")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Wash Hands")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Safe Distance")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Flat Curve")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Google")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
                if(models.get(position).getTitle().equals("Take Rest")){
                    gTitle = models.get(position).getTitle();
                    gDesc = models.get(position).getDescription();
                    apply(gTitle,gDesc);

                }
            }

            private void apply(String gTitle, String gDesc) {
                Intent intent;
                intent = new Intent(c, News_Feed.class);
                intent.putExtra("iTitle",gTitle);
                intent.putExtra("iDesc",gDesc);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount()

    {
        return models.size();
    }
}
