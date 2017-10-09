package com.example.prabhat.vistara;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

public class Reviews extends AppCompatActivity {

    EditText ed,ed1;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        ed=(EditText)findViewById(R.id.review);
        ed1=(EditText)findViewById(R.id.mail);
            }

    public void savedetails(View view) {

        str = ed.getText().toString();
        final String mail=ed1.getText().toString();
        if (str.equals("")||mail.equals("")) {

            Toast.makeText(Reviews.this,"Details can't be left blank ",Toast.LENGTH_LONG).show();
        } else {

            RequestQueue requestQueue = Volley.newRequestQueue(Reviews.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.38/Vistara/review.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        String s=jsonObject.getString("server");
                        Toast.makeText(Reviews.this,s,Toast.LENGTH_LONG).show();

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
                map.put("review",str);
                map.put("email",mail);
                return map;
            }

        };
            requestQueue.add(stringRequest);

        }
    }

}
