package com.example.ign_app.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ign_app.R

class WebViewActivity : AppCompatActivity() {
    lateinit var webView: WebView
    var urlString: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_web_view)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        webView = findViewById(R.id.webView)
        webView.setWebViewClient(WebViewClient())
        val intent = intent
        urlString = intent.getStringExtra("URL")
        webView.loadUrl(urlString)
    }
}