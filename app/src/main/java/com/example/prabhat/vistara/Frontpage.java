package com.example.prabhat.vistara;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Frontpage extends AppCompatActivity {

    int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage);
        ImageView img11 = (ImageView)findViewById(R.id.frontenter);
        ImageView img12 = (ImageView)findViewById(R.id.frontgames);
        ImageView img13 = (ImageView)findViewById(R.id.frontorder);
        ImageView img14 = (ImageView)findViewById(R.id.frontrev);
        ImageView img15 = (ImageView)findViewById(R.id.frontbook);

        // img11.animate().alpha(1f).setDuration(3000);

        img11.animate().alpha(1).setDuration(3200);
        img12.animate().alpha(1).setDuration(3200);
        img13.animate().alpha(1).setDuration(3200);
        img14.animate().alpha(1).setDuration(3200);
        img15.animate().alpha(1).setDuration(3200);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
             GetDetails();
            }
        },1000000);


    }

    public void GetDetails() {

        RequestQueue requestQueue = Volley.newRequestQueue(Frontpage.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.38/Vistara/checkstatus.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String str=jsonObject.getString("server");
                    if(str==null)
                    {

                    }
                    else
                    {
                        Toast.makeText(Frontpage.this,str+"",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) ;
        requestQueue.add(stringRequest);

    }


    public void Entertainment(View v){

        Intent i=new Intent(Frontpage.this,Entertainment.class);
        startActivity(i);
    }
    public void Order(View v){
        Intent i=new Intent(Frontpage.this,Orders.class);
        startActivity(i);
    }
    public void Games(View v){
        Intent i=new Intent(Frontpage.this,Games.class);
        startActivity(i);
    }
    public void Review(View v){
        Intent i=new Intent(Frontpage.this,Reviews.class);
        startActivity(i);
    }
    public void Book(View v){
        Intent i=new Intent(Frontpage.this,Book.class);
        startActivity(i);
    }
}
