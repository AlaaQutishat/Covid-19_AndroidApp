package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    TextView tvCases, tvRecovered,
            tvCritical, tvActive,
            tvTodayCases, tvTotalDeaths,
            tvTodayDeaths,tvTodayRecovered,
            tvAffectedCountries,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvCases
                = findViewById(R.id.tvCases);
        tvTodayRecovered
                = findViewById(R.id.tvTodayRecovered);
        date
                = findViewById(R.id.date);
        tvRecovered
                = findViewById(R.id.tvRecovered);
        tvCritical
                = findViewById(R.id.tvCritical);
        tvActive
                = findViewById(R.id.tvActive);
        tvTodayCases
                = findViewById(R.id.tvTodayCases);
        tvTotalDeaths
                = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths
                = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries
                = findViewById(R.id.tvAffectedCountries);
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



        // Creating a method fetchdata()
        fetchdata();
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
        String url = "https://corona.lmao.ninja/v2/all";

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
                            JSONObject jsonObject
                                    = new JSONObject(
                                    response.toString());

                            // Set the data in text view
                            // which are available in JSON format
                            // Note that the parameter inside
                            // the getString() must match
                            // with the name given in JSON format
                            tvCases.setText(
                                    jsonObject.getString(
                                            "cases"));
                            tvRecovered.setText(
                                    jsonObject.getString(
                                            "recovered"));
                            tvCritical.setText(
                                    jsonObject.getString(
                                            "critical"));
                            tvActive.setText(
                                    jsonObject.getString(
                                            "active"));
                            tvTodayCases.setText(
                                    jsonObject.getString(
                                            "todayCases"));
                            tvTotalDeaths.setText(
                                    jsonObject.getString(
                                            "deaths"));
                            tvTodayDeaths.setText(
                                    jsonObject.getString(
                                            "todayDeaths"));
                            tvAffectedCountries.setText(
                                    jsonObject.getString(
                                            "affectedCountries"));
                                    tvTodayRecovered.setText(
                                    jsonObject.getString(
                                            "todayRecovered"));
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
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        RequestQueue requestQueue
                = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    }
