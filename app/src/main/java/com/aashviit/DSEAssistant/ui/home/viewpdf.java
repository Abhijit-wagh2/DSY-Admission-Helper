package com.aashviit.DSEAssistant.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aashviit.DSEAssistant.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class viewpdf extends AppCompatActivity {
    PDFView pdfView;
    ProgressBar bar;
    TextView tv;
    //private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpdf);
        bar=findViewById(R.id.progress_circular);
        tv=findViewById(R.id.textBar);


        String FilUrl=getIntent().getStringExtra("FileUrl");
         pdfView=findViewById(R.id.pdfView);
         new pdfdownload().execute(FilUrl);
         bar.setVisibility(View.INVISIBLE);
         tv.setVisibility(View.INVISIBLE);




         /*
        AdView adView = new AdView(this);

adView.setAdSize(AdSize.BANNER);

adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
// TODO: Add adView to your view hierarchy.

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });
        */

    }


    private  class  pdfdownload extends AsyncTask<String, Void, InputStream>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
        }

        @Override
        protected InputStream doInBackground(String... strings) {
            bar.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            InputStream inputStream=null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();

                if(urlConnection.getResponseCode()==200){
                    inputStream =new BufferedInputStream(urlConnection.getInputStream());
                }

            }catch (Exception e){
            }

            return  inputStream;
        }


        @Override
        protected void onPostExecute(InputStream inputStream) {
            tv.setVisibility(View.INVISIBLE);
            pdfView.fromStream(inputStream)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                            .scrollHandle(new DefaultScrollHandle(viewpdf.this))
                                    .spacing(2)
                                            .load();
            bar.setVisibility(View.INVISIBLE);
        }



    }
}