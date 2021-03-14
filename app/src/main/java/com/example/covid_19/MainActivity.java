package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 Button gotoglobale,gotolocal,moreinformation,gotoaman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoglobale=findViewById(R.id.gotoglobale);
        gotolocal=findViewById(R.id.gotolocal);
        moreinformation=findViewById(R.id.moreinformation);
        gotoaman=findViewById(R.id.gotoaman);
        gotolocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),speek.class));
            }
        });
        gotoaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/details?id=jo.gov.moh.aman&hl=ar"));
                intent.setPackage("com.android.vending");
                startActivity(intent);
            }
        });
        moreinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.who.int/emergencies/diseases/novel-coronavirus-2019"));
                startActivity(intent);
            }
        });
        gotoglobale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });

    }
}