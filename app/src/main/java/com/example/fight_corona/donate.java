package com.example.fight_corona;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class donate extends AppCompatActivity  {

    EditText amount, note, name, upiid;
    Button pay;
    final int UPI_PAYMENT = 0;
    private TextView auto_donate;
    public String upitxt;
    String select;
    ImageView fab;


    CharSequence iCharSequence[]={"CHARITIESAIDFOUNDATION.28171105@hdfcbank","seeds@sc","cmrfmahacovid19@sbi","apcmrelief@andb","odcares@icici","pmcares@sbi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate2);




        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(donate.this, "QR code pressed!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(donate.this, ReaderActivity.class);
                startActivity(intent);

            }
        });



        initializeMethod();

        upiid.setText(getIntent().getStringExtra("UPI_ID"));

//        if(amount.length() == 0){
//            amount.setError("Please enter amount");
//        } else if(upiid.length() == 0)
//        {
//            upiid.setError("Please enter UPI id");
//        }
        auto_donate = findViewById(R.id.auto_donate);
        auto_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(donate.this);
              //  builder.setMessage("Please select any one!");
                builder.setTitle("Welcome!");
                builder.setIcon(R.drawable.ic_donate_icon_background);

                builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        upiid.setText(select);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                           // startActivity(new Intent(donate.this,donate.class));
                    }
                });

                builder.setMultiChoiceItems(iCharSequence, new boolean[iCharSequence.length], new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                       // Toast.makeText(donate.this, iCharSequence[i]+(b?" Checked":" Unchecked"), Toast.LENGTH_SHORT).show();
                            select = iCharSequence[i].toString();

                    }
                });


                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();
//               upitxt = "pmcares@sbi";
//               upiid.setText(upitxt);

            }


        });

        
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amounttxt = amount.getText().toString();
                String notetxt = note.getText().toString();
                String nametxt = name.getText().toString();
                upitxt = upiid.getText().toString();
                if(amount.length()==0)
                {
                    amount.setError("Please enter amount!");
                } else if(upiid.length()==0){
                    upiid.setError("Please enter valid UPI!");
                }
                else {
                    payUsingupi(amounttxt, notetxt, nametxt, upitxt);
                }
            }


        });
    }






    private void payUsingupi(String amounttxt, String notetxt, String nametxt, String upitxt) {
        Uri uri = Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa", upitxt)
                .appendQueryParameter("pn", nametxt)
                .appendQueryParameter("tn", notetxt)
                .appendQueryParameter("am", amounttxt)
                .appendQueryParameter("cu", "INR").build();
        Intent upi_payment = new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);
        Intent chooser = Intent.createChooser(upi_payment, "Pay with");
        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(donate.this, "No UPI app found!", Toast.LENGTH_LONG).show();
        }
    }

    private void initializeMethod() {
        pay = findViewById(R.id.send);
        amount = findViewById(R.id.amount);
        note = findViewById(R.id.note);
        name = findViewById(R.id.name);
        upiid = findViewById(R.id.UPI_ID);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode || (resultCode == 1))) {
                    if (data != null) {
                        String txt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + txt);
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    } else {
                        Log.d("UPI", "onActivityResult:" + "Return data is null");
                        ArrayList<String> dataLst = new ArrayList<>();
                        dataLst.add("Nothing");
                        upipaymentdataoperation(dataLst);
                    }
                } else {
                    Log.d("UPI", "onActivityResult:" + "Return data is null");
                    ArrayList<String> dataLst = new ArrayList<>();
                    dataLst.add("Nothing");
                    upipaymentdataoperation(dataLst);
                }
                break;
        }

    }

    private void upipaymentdataoperation(ArrayList<String> data) {
        if (isConnectionAvailable(donate.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upipaymentoperation: " + str);
            String paymentCancel = "";
            if (str == null) str = "discard";
            String status = "";
            String approvalref = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("approval Ref".toLowerCase()) ||
                            equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalref = equalStr[1];
                    }
                } else {
                    paymentCancel = "Payment cancel by user";
                    if (status.equals("success")) {
                        Toast.makeText(donate.this, "Transaction Success", Toast.LENGTH_LONG).show();
                        Log.d("UPI", "responsestr:" + approvalref);
                    } else if ("Payment cancel by user".equals(paymentCancel)) {
                        Toast.makeText(donate.this, "Payment cancel by user", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(donate.this, "Transaction Failed.Please try again!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        } else {
            Toast.makeText(donate.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }


    private boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null)
        {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isConnected() && networkInfo.isConnectedOrConnecting() &&
                networkInfo.isAvailable()){
                return true;
            }}
            return false;

    }


}