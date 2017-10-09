package com.example.prabhat.vistara;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

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


public class Entertainment extends AppCompatActivity {

    String JsonString;
    MoviesAdapter moviesAdapter;
    ListView listView1,listView2;
    ViewGroup parent;
    ImageButton btn;
    View view;
    String id,link;
    List<ClipData.Item> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entertainment);
        GetDetails();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menumovie:
                return true;

            case R.id.menusongs:
                Intent k=new Intent(Entertainment.this,Songs.class);
                startActivity(k);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void GetDetails() {
        final ProgressDialog progress;
        progress = new ProgressDialog(Entertainment.this);
        progress.setMessage("Loading Movies.. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();



        RequestQueue requestQueue = Volley.newRequestQueue(Entertainment.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://webtechie.000webhostapp.com/Vistara/connect.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                listView1 = (ListView) findViewById(R.id.listmovies);
                moviesAdapter = new MoviesAdapter(Entertainment.this, R.layout.movieslist); // this line should come before the line given below ,otherwise eerror
                listView1.setAdapter(moviesAdapter);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("server");
                    progress.dismiss();
                    int count = 0;
                    String name,lang,type,thumbnail;
                    while (count < jsonArray.length()) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                        thumbnail=jsonObject1.getString("thumbnail");
                        name = jsonObject1.getString("name");
                        lang = jsonObject1.getString("lang");
                        type=jsonObject1.getString("type");
                        link=jsonObject1.getString("link");
                        Log.v("Vista",link);
                        Editdetails ed = new Editdetails(name,lang,type,thumbnail);
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
                }) /*{
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id",id);
                map.put("shift","Lunch");
                return map;
            }

        }*/;
        requestQueue.add(stringRequest);

    }

 public void playmovie(View v){

     Intent i= new Intent(Entertainment.this,PlayMovie.class);
     i.putExtra("play",link);
     startActivity(i);

 }



    public static class Editdetails {
        public Editdetails(String name, String lang, String type, String thumb) {
            this.name = name;
            this.lang = lang;
            this.type = type;
            this.thumb = thumb;
        }

        String name,lang,type,thumb;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getName() {
            return name;
        }

        public void setName(String time1) {
            this.name = name;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String time2) {
            this.lang = lang;
        }

        public String getType() {
            return type;
        }

        public void setType(String time2) {
            this.type = type;
        }

    }



}
