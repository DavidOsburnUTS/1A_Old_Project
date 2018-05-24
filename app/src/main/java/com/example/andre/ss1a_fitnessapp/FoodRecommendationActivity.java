package com.example.andre.ss1a_fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.webkit.WebView;

public class FoodRecommendationActivity extends AppCompatActivity {

    //Nutritionix
    public WebView wv;
    public String URL = "https://www.nutritionix.com/food/cookie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recommendation);


        //Webview for Nutritionix
        wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                findViewById(R.id.loadingPanel).setVisibility(View.GONE);

            }
        });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(URL);


    }
}
