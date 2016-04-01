package com.example.jokingApp.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.BitmapHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/3/28.
 */
public class DetailActivity extends BaseActivity {
    @InjectView(R.id.ivImage)
    ImageView mIvImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @InjectView(R.id.text)
    TextView mText;

    private ProgressBar pbProgress;
    private WebView mWebView;
    private ImageButton btnBack;
    private ImageButton btnSize;
    private ImageButton btnShare;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//
//        mWebView = (WebView) findViewById(R.id.wv_web);
//        btnBack = (ImageButton) findViewById(R.id.btn_back);
//        btnSize = (ImageButton) findViewById(R.id.btn_size);
//        btnShare = (ImageButton) findViewById(R.id.btn_share);
//        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
//
//        btnBack.setOnClickListener(this);
//        btnSize.setOnClickListener(this);
//        btnShare.setOnClickListener(this);
//
//
//        JokeInfo.JokeBean data = (JokeInfo.JokeBean) getIntent().getParcelableExtra("data");
//        String url = data.getUrl();
//        url= GlobalConstant.SERVER_URL+url;
//        //String url = getIntent().getStringExtra("url");
//        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);// 表示支持js
//        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
//        settings.setUseWideViewPort(true);// 支持双击缩放
//
//        mWebView.setWebViewClient(new WebViewClient() {
//            /**
//             * 网页开始加载
//             */
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                pbProgress.setVisibility(View.VISIBLE);
//            }
//
//            /**
//             * 网页加载结束
//             */
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                pbProgress.setVisibility(View.GONE);
//            }
//
//            /**
//             * 所有跳转的链接都会在此方法中回调
//             */
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // tel:110
//                System.out.println("跳转url:" + url);
//                view.loadUrl(url);
//
//                return true;
//                // return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
//        mWebView.loadUrl(url);// 加载网页

        JokeInfo.JokeBean data = (JokeInfo.JokeBean) getIntent().getParcelableExtra("data");
        String des = data.getDes();
        mText.setText(des);

        String imageurl = GlobalConstant.SERVER_URL+data.getImageurl();
        BitmapHelper.getBitmapUtils().display(mIvImage,imageurl);

        String name = data.getName();
        mCollapsingToolbar.setTitle(name);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_back:
//                finish();
//                break;
//            case R.id.btn_size:
//                break;
//            case R.id.btn_share:
//                break;
//            default:
//                break;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
