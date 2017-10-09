package com.example.prabhat.vistara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Games extends AppCompatActivity {

    ImageView img;
    int count=0;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        img=(ImageView)findViewById(R.id.ball);
        tv=(TextView)findViewById(R.id.score);
    }

    public void Right(View v){
        img.animate().translationX(80f).setDuration(1000);
        count++;
        tv.setText(count+"");
    }
    public void Left(View v){
        img.animate().translationX(-80f).setDuration(1000);
        count++;
        tv.setText(count+"");
    }
}
