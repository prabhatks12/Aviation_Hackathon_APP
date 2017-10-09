package com.example.prabhat.vistara;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Orders extends AppCompatActivity {

    String JsonString;
    OrdersAdapter moviesAdapter;
    ListView listView1,listView2;
    ViewGroup parent;
    ImageButton btn;
    View view;
    String id,qty1,name1;
    List<ClipData.Item> data=new ArrayList<>();
    LinearLayout ln;
    EditText tv;
    View vi;
    int qty=0;
    String pric;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ln= (LinearLayout) findViewById(R.id.linearlay);
         vi = inflater.inflate(R.layout.orderslist, null); //log.xml is your file.
         tv = (EditText) vi.findViewById(R.id.numbers);
        GetDetails();
    }

    public void Savedata(View v){

        Log.e("savehoraha","save");
        RequestQueue requestQueue = Volley.newRequestQueue(Orders.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.38/Vistara/pushorder.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> map = new HashMap<>();
                map.put("id",BarCode.seat+"");
                map.put("itemname",name1);
                map.put("qty",qty1);
                map.put("price",pric);
                return map;
            }

        };
        requestQueue.add(stringRequest);



    }

    public void GetDetails() {

        qty1=tv.getText().toString();
        final ProgressDialog progress;
        progress = new ProgressDialog(Orders.this);
        progress.setMessage("Loading Food.. ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();



        RequestQueue requestQueue = Volley.newRequestQueue(Orders.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.38/Vistara/menu.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                listView1 = (ListView) findViewById(R.id.listorder);
                moviesAdapter = new OrdersAdapter(Orders.this, R.layout.orderslist); // this line should come before the line given below ,otherwise eerror
                listView1.setAdapter(moviesAdapter);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("server");
                    progress.dismiss();
                    int count = 0;
                    String name,lang,type;
                    while (count < jsonArray.length()) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                        name1 = jsonObject1.getString("name");
                        lang = jsonObject1.getString("link");
                        type=jsonObject1.getString("price");
                        pric=type;
                        Orders.Editdetails ed = new Orders.Editdetails(name1,lang,type);
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
                });
        requestQueue.add(stringRequest);

    }


    public int onEdit(){
        View parentRow = (View) view.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        int a=position+1;
        return a;

    }



    public static class Editdetails {

        String name,lang,type;

        public Editdetails(String name, String lang,String type) {
            this.name = name;
            this.lang=lang;
            this.type=type;

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
