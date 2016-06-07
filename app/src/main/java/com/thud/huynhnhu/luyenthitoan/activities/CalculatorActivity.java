package com.thud.huynhnhu.luyenthitoan.activities;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;

public class CalculatorActivity extends BaseActivity
        implements ActivityInterface {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_web_view);

        initFlags();

        initControl();

        setEventForControl();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void initFlags() {
    }

    @Override
    public void initControl() {
        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebViewClient());

//        String url = "http://luyenthitoan.esy.es";
        String url = "file:///android_asset/index.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void setEventForControl() {

    }

    @Override
    public void getData(String... params) {

    }

    @Override
    public void setData() {

    }

    private class MyWebViewClient extends WebViewClient{
        public boolean sholdOverrideLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
