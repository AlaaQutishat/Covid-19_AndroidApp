package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    ImageView flag;
    TextView todaydeath,todaycase,todayRecovered,Recovered,Death,totalcase,name,date,active,critical,tests;
    String selectitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        totalcase=findViewById(R.id.totalcase);
        name=findViewById(R.id.name);
        tests=findViewById(R.id.tests);
        critical=findViewById(R.id.critical);
        flag = findViewById(R.id.flag);
        todaydeath = findViewById(R.id.todaydeath);
        active = findViewById(R.id.active);
        date = findViewById(R.id.date);
        todaycase = findViewById(R.id.todaycases);

        Recovered = findViewById(R.id.Recovered);
        todayRecovered = findViewById(R.id.todayRecovered);
        Death = findViewById(R.id.Death);
        Intent startingIntent = getIntent();
        selectitem=startingIntent.getStringExtra("countryname");
        if(selectitem!=null){
            name.setText(selectitem);
            fetchdata();
        }

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getdate();
                handler.postDelayed(this, 1000);
            }
        };

//Start
        handler.postDelayed(runnable, 1000);

    }
    public  void  getdate(){
        Date dt = new Date();
        String monthString  = (String) DateFormat.format("MMM",  dt);// Jun
        String day          = (String) DateFormat.format("dd",   dt); // 20
        String year         = (String) DateFormat.format("yyyy", dt); // 2013
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        date.setText(monthString+" "+day+","+" "+year+","+" "+hours+":"+minutes+" "+"GMT");
    }
        private void fetchdata()
    {
    // Create a String request
    // using Volley Library
    String url = "https://corona.lmao.ninja/v2/Countries";

    StringRequest request
            = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {

                    // Handle the JSON object and
                    // handle it inside try and catch
                    try {
                        // Creating object of JSONObject
                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject row = array.getJSONObject(i);
                            if(row.getString("country").equalsIgnoreCase(selectitem)){
                                todaydeath.setText(row.getString("todayDeaths"));
                                todaycase.setText(row.getString("todayCases"));
                                totalcase.setText(row.getString("cases"));
                                todayRecovered.setText(row.getString("todayRecovered"));
                                Recovered.setText(row.getString("recovered"));
                                Death.setText(row.getString("deaths"));
                                active.setText(row.getString("active"));
                                critical.setText(row.getString("critical"));
                               tests.setText(row.getString("tests"));
                                Picasso.get().load(row.getJSONObject("countryInfo").getString("flag")).into(flag);

                            }

                        }




                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();
                }
            });

    RequestQueue requestQueue
            = Volley.newRequestQueue(this);
            requestQueue.add(request);}

    @Override
    protected void onStart() {
        super.onStart();

    }
}