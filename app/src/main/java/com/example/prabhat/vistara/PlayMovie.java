package com.example.prabhat.vistara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayMovie extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movie);

        webview=(WebView)findViewById(R.id.webview);
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl(getIntent().getExtras().getString("play"));
        webview.setWebViewClient(new WebViewClient());

    }
}
