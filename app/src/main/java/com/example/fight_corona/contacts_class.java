package com.example.fight_corona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static androidx.appcompat.app.AlertDialog.*;
import static com.example.fight_corona.R.*;

public class contacts_class extends AppCompatActivity {



    private static final String[] states = new String[]{
            "Andhra Pradesh (AP)",
            "Arunachal Pradesh (AR)",
            "Assam (AS)",
            "Bihar (BR)",
            "Chhattisgarh (CG)",
            "Goa (GA)",
            "Gujarat (GJ)",
            "Haryana (HR)",
            "Himachal Pradesh (HP)",
            "Jharkhand (JH)",
            "Karnataka (KA)",
            "Kerala (KL)",
            "Madhya Pradesh (MP)",
            "Maharashtra (MH)",
            "Manipur (MN)",
            "Meghalaya (ML)",
            "Mizoram (MZ)",
            "Nagaland (NL)",
            "Odisha(OR)",
            "Punjab (PB)",
            "Rajasthan (RJ)",
            "Sikkim (SK)",
            "Tamil Nadu (TN)",
            "Telangana (TS)",
            "Tripura (TR)",
            "Uttar Pradesh (UP)",
            "Uttarakhand (UK)",
            "West Bengal (WB)",
            "Andaman and Nicobar Islands(AN)",
            "Chandigarh (CH)",
            "Dadra and Nagar Haveli (DN) Daman and Diu (DD)",
            "Delhi (DL)",
            "Jammu and Kashmir (JK)",
            "Ladakh (LK)",
            "Lakshadweep (LD)",
            "Pondicherry (PY)",
            "pranav"

    };

    private static final String[] numbers = new String[]{
            "0866-2410978",
            "9436055743",
            "6913347770",
            "104",
            "104",
            "104",
            "104",
            "8558893911",
            "104",
            "104",
            "104",
            "0471-2552056",
            "104",
            "020-26127394",
            "3852411668",
            "108",
            "102",
            "7005539653",
            "9439994859",
            "104",
            "0141-2225624",
            "104",
            "044-29510500",
            "104",
            "0381-2315879",
            "104",
            "18001805145",
            "1800313444222",
            "03192-232102",
            "9779558282",
            "104",
            "011-22307145",
            "01912520982",
            "01982256462",
            "104",
            "104",
            "9637014513"
    } ;

    private static final int REQUEST_CALL=1;
    public String temp;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                makePhoneCall();
            } else{
                Toast.makeText(contacts_class.this,"Permission DENIED",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void makePhoneCall() {
        String num = temp;
        if(num.trim().length()>0)
        {
            if(ContextCompat.checkSelfPermission(contacts_class.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(contacts_class.this,
                        new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{

                String dial = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else{
            Toast.makeText(contacts_class.this,"Please select a state",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_contact_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final HashMap <String, String>map = new HashMap<>();
        for(int i=0;i<states.length;i++)
        {
            map.put(states[i],numbers[i]);
        }

        final TextView central = findViewById(id.centralHelp);
        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCentralPhoneCall();
            }

            private void makeCentralPhoneCall() {
                String num = "0000";        //replace by central helpline number
                if(num.trim().length()>0)
                {
                    if(ContextCompat.checkSelfPermission(contacts_class.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(contacts_class.this,
                                new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
                    }else{
                        String dial = "tel:" + num;
                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                    }
                }
                else{
                    //Toast.makeText(contacts_class.this,"Please select a state",Toast.LENGTH_LONG).show();
                }
            }                       //it is used for calling CENTRAL HELPLINE NUMBER 1075
        });

        final TextView tv = findViewById(R.id.phone);
       ImageButton search = findViewById(R.id.search);


        final AutoCompleteTextView actv1 = (AutoCompleteTextView)findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,states);
        actv1.setAdapter(adapter);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = actv1.getText().toString();
                tv.setText(text);
               // text = text.toLowerCase();


                String s1 = map.get(text);
                tv.setText(s1);
                temp = s1;

            }
        });

        ImageButton call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new Builder(contacts_class.this);
                altdial.setMessage("Do you really want to make a call?").setCancelable(false)
                        .setPositiveButton("Yes", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    makePhoneCall();

                            }
                        })
                        .setNegativeButton("No", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = altdial.create();
                alert.setTitle("Be Sure!");
                alert.show();
            }
        });

    }
}
