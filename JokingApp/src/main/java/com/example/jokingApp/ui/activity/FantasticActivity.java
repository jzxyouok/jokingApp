package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jokingApp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class FantasticActivity extends BaseSwipeBackActivity {

    @InjectView(R.id.wv_web)
    WebView mWvWeb;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_fantastic);
        ButterKnife.inject(this);
        String url = getIntent().getStringExtra("url");
        initToolBar(mToolbar);
        setTitle("返回");

        WebSettings settings = mWvWeb.getSettings();
        settings.setJavaScriptEnabled(true);// 表示支持js
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击缩放
        mWvWeb.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);   //在当前的webview中跳转到新的url
                return true;
            }
        });
        mWvWeb.loadUrl(url);// 加载网页
    }

    @Override
    public void initInjector() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
