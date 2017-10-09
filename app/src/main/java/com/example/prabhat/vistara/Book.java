package com.example.prabhat.vistara;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

import java.util.ArrayList;
import java.util.List;

public class Book extends AppCompatActivity {

    String JsonString;
    BookAdapter moviesAdapter;
    ListView listView1,listView2;
    ViewGroup parent;
    ImageButton btn;
    View view;
    String id,link;
    List<ClipData.Item> data=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        GetDetails();

    }

    public void DownloadBook(View v){

        Toast.makeText(this,"Book will be downloaded ",Toast.LENGTH_LONG).show();
    }


    public void GetDetails() {
        final ProgressDialog progress;
        progress = new ProgressDialog(Book.this);
        progress.setMessage("Loading Books.. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        RequestQueue requestQueue = Volley.newRequestQueue(Book.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.38/Vistara/conbook.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                listView1 = (ListView) findViewById(R.id.listbook);
                moviesAdapter = new BookAdapter(Book.this, R.layout.booklist); // this line should come before the line given below ,otherwise eerror
                listView1.setAdapter(moviesAdapter);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("server");
                    progress.dismiss();
                    int count = 0;
                    String name,lang,type,img;
                    while (count < jsonArray.length()) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                        name = jsonObject1.getString("name");
                        lang = jsonObject1.getString("authorname");
                        img=jsonObject1.getString("thumbnail");
                        link=jsonObject1.getString("link");
                        Book.Editdetails ed = new Book.Editdetails(name,lang,img);
                        moviesAdapter.add(ed);
                        count++;
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




    public static class Editdetails {

        public Editdetails(String name, String author, String img) {
            this.name = name;
            this.author = author;
            this.img=img;

        }
        String name, author,img;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }


    }
    }
