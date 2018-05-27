package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.WebView;


//Not sure if to include this or something similar, just further resources

public class FitnessBlenderActivity extends AppCompatActivity {

    public WebView wv;

    public String URL = "https://www.fitnessblender.com/videos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_blender);

        Button backBtn = (Button) findViewById(R.id.fitnessBlenderBackBtn);

        wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);

            }
        });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(URL);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
