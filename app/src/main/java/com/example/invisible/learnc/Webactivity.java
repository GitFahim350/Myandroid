package com.example.invisible.learnc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webactivity extends AppCompatActivity {

    private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webactivity);
        wb=findViewById(R.id.webviewid);
        WebSettings websettings=wb.getSettings();
        websettings.setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl("https://www.onlinegdb.com/online_c_compiler");
    }
}
