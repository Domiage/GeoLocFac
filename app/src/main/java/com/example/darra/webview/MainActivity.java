package com.example.darra.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
   WebView browser;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.prgBar);
        browser = (WebView) findViewById(R.id.webView);
        browser.setWebViewClient(new Mybrowser());
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl("http://www.gogle.com");
    }

    public void bunext(View view) {
      browser.goForward();
        EditText url = (EditText) findViewById(R.id.editText4);
        url.setText(browser.getUrl().toString());
    }

    public void bunback(View view) {
      browser.goBack();
        EditText url = (EditText) findViewById(R.id.editText4);
        url.setText(browser.getUrl().toString());
    }

    public void bungo(View view) {
        EditText url = (EditText) findViewById(R.id.editText4);
        if(!(url.getText().subSequence(0,4).toString().contains("http"))||!(url.getText().subSequence(0,5).toString().contains("https")))
        {
            url.setText("http://"+url.getText());
        }
        browser.loadUrl(url.getText().toString());
    }

    public class Mybrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            EditText editText = (EditText) findViewById(R.id.editText4);
            editText.setText(url.toString());
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

    }
}
