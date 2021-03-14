package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.util.ArrayList;

public class selectconutry extends AppCompatActivity {
 RecyclerView v1;
 TextView search ;
    Countrylistadapter adapter;
  RecyclerView.LayoutManager layoutManager;
    ArrayList <country>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectconutry);
        v1=findViewById(R.id.recyclerView);
        search=findViewById(R.id.search);
        v1.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
     //  layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false); horzintal item behind item
     // layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
       v1.setLayoutManager(layoutManager);
        search.setOnKeyListener(new View.OnKeyListener() {
                                    @Override
                                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                                            // this is for backspace
                                            adapter = new Countrylistadapter(list);
                                            v1.setAdapter(adapter);
                                            filter(search.getText().toString());
                                        }
                                        return false;
                                    }
                                });
                search.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }


                    @Override
                    public void afterTextChanged(Editable s) {
                        filter(s.toString());
                    }
                });
        fetchdata();


    }
    public  void  filter(String text){
        ArrayList<country> filteredlist =new ArrayList<>();
        for(country country:adapter.country){
            if(country.Name.toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(country);
            }
        }
        adapter.filteredlist(filteredlist);
    }

    private void fetchdata()
    {
        list= new ArrayList<country>();




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
                        //    another way to get more than array or to get all object
//                            JSONObject jsonObject =new JSONObject(response);
//                            JSONArray arra=jsonObject.getJSONArray("array name");
//                         for(int i=0;i<arra.length();i+++){
//                             JSONObject jsonObject =arra.getJSONObject(i);
//                             String name = jsonObject.getString("name");
//                         }
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject row = array.getJSONObject(i);

                                list.add(new country( row.getString("country"),row.getJSONObject("countryInfo").getString("flag")));




                            }
                             adapter = new Countrylistadapter(list);
                            v1.setAdapter(adapter);




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

        //adapter for recycle view
    class Countrylistadapter extends RecyclerView.Adapter<Countrylistadapter.CountryViewHolder> {
       private ArrayList<country> country = new <country>ArrayList();

        public Countrylistadapter(ArrayList <country>countrylist) {
            this.country=countrylist;
        }


 public void filteredlist(ArrayList<country> filteredlist){
     country=filteredlist;
     notifyDataSetChanged();
 }
        @NonNull
        @Override
        public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                    View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,parent,false);
                    return new CountryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
            holder.itemView.setTag(country.get(position));
           holder.name.setText(country.get(position).getName());
            Picasso.get().load(country.get(position).getImage()).into(holder.flag);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                String countryname = (country.get(position).getName());
                intent.putExtra("countryname",countryname);
                startActivity(intent);
                }
            });
        }





        @Override
        public int getItemCount() {
            return country.size();
        }

        public class CountryViewHolder extends RecyclerView.ViewHolder {
            ImageView flag ;
            TextView name;
            public CountryViewHolder(@NonNull View itemView) {
                super(itemView);
                flag=itemView.findViewById(R.id.flag);
                name=itemView.findViewById(R.id.countryname);
            }
        }







        }


    }
