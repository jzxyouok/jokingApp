package com.example.jokingApp.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.TerrorDeatail;
import com.example.jokingApp.bean.WeiXinInfo;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.widgets.LoadingPage;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by idea-pc on 2016/5/10.
 */
public class TerrorActivity extends BaseSwipeBackActivity {


    @InjectView(R.id.ivImage)
    ImageView mIvImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @InjectView(R.id.text)
    TextView mText;
    @InjectView(R.id.refresh)
    SwipeRefreshLayout mRefresh;
    private String mId;
    private String mText1;

    int[] a = new int[]{R.mipmap.ic_1,R.mipmap.ic_2,R.mipmap.ic_3,R.mipmap.ic_4,R.mipmap.ic_5};
    Random random = new Random();

    //http://route.showapi.com/955-2?showapi_appid=18760&showapi_sign=fb9e2d61cd5e44f7966d0c6842d56c61&id=/mj/18319.html
    private ApiService mApiService;
    String URL = "http://route.showapi.com/";
    String SHOWAPI_APPID = "18760";
    String SHOWAPI_SIGN = "fb9e2d61cd5e44f7966d0c6842d56c61";


    @Override
    public void initInjector() {
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_terror);
        ButterKnife.inject(this);
        initToolBar(mToolbar);

        mId = getIntent().getStringExtra("id");
        String picurl = getIntent().getStringExtra("picurl");
        String title = getIntent().getStringExtra("title");

        Picasso.with(UiUtils.getContext()).load(picurl).config(Bitmap.Config.RGB_565).into(mIvImage);
        mCollapsingToolbar.setTitle(title);

        int i = random.nextInt(5);
        Picasso.with(this)
                .load(a[i])
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(mIvImage);
        initData();//想服务器请求数据

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    /**
     * 请求服务器数据
     */
    @Override
    protected void initData() {
        mRefresh.setRefreshing(true);
        mApiService = RetrofitUtils.createApiToGson(URL, ApiService.class);
        Observable<TerrorDeatail> call = mApiService.getTerrorDetail(SHOWAPI_APPID, SHOWAPI_SIGN, mId);
        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<TerrorDeatail>() {
            @Override
            public void call(TerrorDeatail data) {
                mText1 = data.getShowapi_res_body().getText();
                String s="";
                if (!TextUtils.isEmpty(mText1)) {
                  s = mText1.replaceAll("&nbsp;", "  ");
                }
                mText.setText(s);
                mRefresh.setRefreshing(false);
            }
        });
    }
}

