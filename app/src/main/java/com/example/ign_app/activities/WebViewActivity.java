package com.example.ign_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ign_app.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String urlString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        Intent intent = getIntent();
        urlString = intent.getStringExtra("URL");
        webView.loadUrl(urlString);


    }

}
