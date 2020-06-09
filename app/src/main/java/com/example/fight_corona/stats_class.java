package com.example.fight_corona;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class stats_class extends AppCompatActivity {

    public TextView result;
    private TextView textView4;
    private RequestQueue mq;
    public String val;
    int flag = 0;

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_class);

        getSupportActionBar().setTitle("Fight Corona");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        result = findViewById(R.id.textView4);
        result.setMovementMethod(new ScrollingMovementMethod());
        Button parse = findViewById(R.id.button);

        mq = Volley.newRequestQueue(this);
        final EditText text = (EditText)findViewById(R.id.state_ip);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = text.getText().toString();
                Log.d("Initial_state",val);
                val = val.toLowerCase();
                //String cap = val.substring(0, 1).toUpperCase() + val.substring(1);

                jsonParse(val);
            }

        });
    }
    private void jsonParse(final String val){
        result.setText("");

        String url = "https://api.covid19india.org/v2/state_district_wise.json";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    //JSONArray jsonArray = response.getJSONArray("statewise");
                    int str_len = response.length();
                    String lenn = Integer.toString(str_len);
                    Log.d("lenn",lenn);
                    for (int i = 0; i < response.length(); i++)
                    {
                        JSONObject st = response.getJSONObject(i);

                        String statename = st.getString("state");
                        statename = statename.toLowerCase();
                        Log.d("ip_state",val);
                        Log.d("curr_state",statename);
                        if(statename.equals(val))
                        {
                            JSONArray jsonArray = st.getJSONArray("districtData");
                            result.append(statename+"\n--------------------------\n");
                            for(int j=0;j<jsonArray.length();j++)
                            {
                                JSONObject dis = jsonArray.getJSONObject(j);
                                String districtname = dis.getString("district");
                                String active = dis.getString("active");
                                String confirmed = dis.getString("confirmed");
                                String deaths = dis.getString("deceased");
                                String recovered = dis.getString("recovered");
                                districtname = "District : "+ districtname;
                                active = "Active Cases : " + active;
                                confirmed = "Confirmed Cases : " + confirmed;
                                deaths = "Total Deaths : " + deaths;
                                recovered = "Total Recovered : " + recovered;

                                result.append(districtname+"\n" + String.valueOf(confirmed) + "\n" + String.valueOf(active) + "\n" + String.valueOf(deaths) + "\n" + String.valueOf(recovered) + "\n\n\n");

                            }

                        }


                    }
                }
                catch (JSONException e) {
                    result.append("Oops ! Enter a valid input");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result.append("Oops ! Enter a valid input");
                error.printStackTrace();
            }


        });
        mq.add(request);
}
}
