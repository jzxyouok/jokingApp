package com.example.jokingApp.presenter;


import android.support.annotation.NonNull;

import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.ui.view.VideoView;
import com.example.jokingApp.widgets.LoadingPage;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by idea-pc on 2016/4/20.
 */
public class VideoPresenter extends BasePresenter<com.example.jokingApp.ui.view.VideoView> {

    private ApiService mService;
    private List<MeizhiInfo.ResultsBean> mResults;
    private List<MeizhiInfo.ResultsBean> mResultsBeen;

    @Inject
    @Singleton
    public VideoPresenter() {
    }

    @Override
    public void attachView(@NonNull VideoView view) {
        super.attachView(view);
        view.setAdapterData(mResults);
    }

    /**
     * 请求服务器数据
     * @return  数据请求结果为成功 或者 失败
     */
    public LoadingPage.LoadResult loadServerData() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit build = builder.baseUrl("http://gank.io/api/data/").addConverterFactory(GsonConverterFactory.create
                ()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        mService = build.create(ApiService.class);
        Call<MeizhiInfo> call = mService.getMeiZhi(1);
        MeizhiInfo meizhiInfo = null;
        try {
            meizhiInfo = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (meizhiInfo == null) {
            return LoadingPage.LoadResult.error;
        }
        mResultsBeen = meizhiInfo.getResults();
        if (mResults == null) {
            mResults = mResultsBeen;
        }
        return LoadingPage.LoadResult.success;
    }

    @Override
    public void detachView() {

    }

}
