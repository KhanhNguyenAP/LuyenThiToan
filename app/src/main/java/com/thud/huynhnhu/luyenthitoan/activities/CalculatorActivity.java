package com.thud.huynhnhu.luyenthitoan.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentShowDeTailExample;
import com.thud.huynhnhu.luyenthitoan.fragment.FragmentShowDeTailInfo;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.ActivityInterface;

import java.util.ArrayList;
import java.util.List;

public class CalculatorActivity extends Activity
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

        String url = "http://luyenthitoan.esy.es";
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
