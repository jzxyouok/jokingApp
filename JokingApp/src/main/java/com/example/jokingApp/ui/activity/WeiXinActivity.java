package com.example.jokingApp.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/10.
 */
public class WeiXinActivity extends BaseSwipeBackActivity {


    @InjectView(R.id.ivImage)
    ImageView mIvImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.webview)
    WebView mWebview;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    public void initInjector() {

    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_weixin);
        ButterKnife.inject(this);
        initToolBar(mToolbar);
        String url = getIntent().getStringExtra("url");
        String picurl = getIntent().getStringExtra("picurl");
        String title = getIntent().getStringExtra("title");
        Picasso.with(UiUtils.getContext()).load(picurl).config(Bitmap.Config.RGB_565).into(mIvImage);
        mCollapsingToolbar.setTitle(title);


        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);// 表示支持js
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击缩放
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);   //在当前的webview中跳转到新的url
                return true;
            }
        });
        mWebview.loadUrl(url);// 加载网页
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
