package com.example.lenovo.hitrestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.lenovo.hitrestapi.network.VolleyRequest;
import com.example.lenovo.hitrestapi.network.VolleyResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
   String URL="http://www.example.com/api/get/1";
   VolleyRequest volleyRequest;
   TextView txt_response;
    ProgressDialog progress;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volleyRequest=VolleyRequest.getInstance(getApplicationContext());
        txt_response=findViewById(R.id.response);
        webView=findViewById(R.id.webview);
// For Api HIT
        volleyRequest.requestWithJsonObject(URL,response);
      /*  progress = new ProgressDialog(this);
        progress.setMessage("Please wait...");
        progress.show();
        progress.setCancelable(false);*/

// WEB VIEW TO SHOW HTML RESPONSE

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    VolleyResponse response =new VolleyResponse() {
        @Override
        public void onResponse(JSONObject obj) throws Exception {
            progress.dismiss();
            txt_response.setText(obj.toString());


        }


    };
}