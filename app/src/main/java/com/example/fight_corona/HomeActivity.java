package com.example.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

import javax.net.ssl.SNIHostName;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener {

    private CardView donate,talk,contact,report,stats,dondonts;
    ToggleButton btnToggle;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        loadingDialog = new LoadingDialog(HomeActivity.this);

        btnToggle = findViewById(R.id.toggleButton);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnToggle.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        donate = (CardView) findViewById(R.id.bankcardId);
        talk = (CardView) findViewById(R.id.talkId);
        contact = (CardView) findViewById(R.id.contactId);
        report = (CardView) findViewById(R.id.reportId);
        stats = (CardView) findViewById(R.id.statId);
        dondonts = (CardView) findViewById(R.id.dodonId);

        donate.setOnClickListener(HomeActivity.this);
        talk.setOnClickListener(HomeActivity.this);
        contact.setOnClickListener(HomeActivity.this);
        report.setOnClickListener(HomeActivity.this);
        stats.setOnClickListener(HomeActivity.this);
        dondonts.setOnClickListener(HomeActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.dark_mode:
                Toast.makeText(this, "dark_mode pressed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contact_developer:
                // Toast.makeText(this,"Developer pressed",Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://x6zblro75bz3puu1zzkqmg-on.drv.tw/www.mywebsite.com/"));
                startActivity(browserIntent);
                return true;
            case R.id.faq:
                Toast.makeText(this, "FAQ pressed", Toast.LENGTH_LONG).show();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {


        switch (v.getId())
        {
            case R.id.bankcardId :
               Intent i = new Intent(this,donate.class);
               startActivity(i);
                break;
            case R.id.contactId :
                Intent i1 = new Intent(this,contacts_class.class);
                startActivity(i1);
                break;
            case R.id.talkId :
                Intent i2 = new Intent(this,talk_class.class);
                startActivity(i2);
                break;
            case R.id.reportId :
                goToFunction();
                Intent i3 = new Intent(this,report_class.class);
                startActivity(i3);
                break;
            case R.id.statId :
                Intent i4 = new Intent(this,stats_class.class);
                startActivity(i4);
                break;
            case R.id.dodonId :
                Intent i5 = new Intent(this,dodon_class.class);
                startActivity(i5);
                break;
        }
    }

    private void goToFunction() {
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
            }
        },3000);


    }
}
