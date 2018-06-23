package com.example.nelsoft.apinewscustomer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewloader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewloader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle paramsFromNews = this.getIntent().getExtras();

        if(paramsFromNews !=null){
            String urlNews = paramsFromNews.getString("urlNews");

            WebView view = findViewById(R.id.MyWebView);
            view.getSettings().setJavaScriptEnabled(true);
            view.getSettings().setBuiltInZoomControls(true);
            view.setWebViewClient(new WebViewClient());
            view.loadUrl(urlNews);
        }

    }

}
