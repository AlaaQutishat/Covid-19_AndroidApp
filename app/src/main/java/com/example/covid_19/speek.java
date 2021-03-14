package com.example.covid_19;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class speek extends AppCompatActivity {
 Switch switch1;
 Button speek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speek);
        switch1=findViewById(R.id.switch1);
        speek=findViewById(R.id.button3);
        speek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getspeechinput();
            }
        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Intent intent =new Intent(getApplicationContext(),selectconutry.class);
                startActivity(intent);
                switch1.setChecked(false);
            }
        });
    }
    public  void getspeechinput(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
       intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());//android device language
      //  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);//English language
       // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRANCE);//FRANCE language
        if(intent.resolveActivity(getPackageManager()) != null){
        startActivityForResult(intent,100);}
        else{
            Toast.makeText(getApplicationContext(),"Your Device Not Support Speech Input",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 100:
      if(resultCode==RESULT_OK && data!=null){
  ArrayList <String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
  Intent intent=new Intent(getApplicationContext(),MainActivity3.class);
  intent.putExtra("countryname",result.get(0));
  startActivity(intent);
      }
                break;
        }
    }
}