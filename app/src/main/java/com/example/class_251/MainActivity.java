package com.example.class_251;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    AdView mAdview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);

        mAdview=findViewById(R.id.adView);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://icelandic-things.000webhostapp.com/apps/hello.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                                                        new Response.Listener<String>() {
                                                            @Override
                                                            public void onResponse(String response) {
                                                                // Display the first 500 characters of the response string.
                                                                progressBar.setVisibility(View.GONE);
                                                                if (response.contains("ShowAd")){
                                                                    mAdview.setVisibility(View.VISIBLE);
                                                                    AdRequest adRequest=new AdRequest.Builder().build();
                                                                    mAdview.loadAd(adRequest);

                                                                }else{
                                                                    mAdview.setVisibility(View.GONE);

                                                                }

                                                            }
                                                        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}