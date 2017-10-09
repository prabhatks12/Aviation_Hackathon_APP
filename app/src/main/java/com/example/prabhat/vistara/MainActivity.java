package com.example.prabhat.vistara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.splash);
    }

    public void splashclick(View v)
    {    Intent i=new Intent(MainActivity.this,BarCode.class);
         startActivity(i);

    }
}
