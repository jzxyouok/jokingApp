package com.example.jokingApp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.TerrorAdapter;
import com.example.jokingApp.adapter.WeiXinAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.TerroeInfo;
import com.example.jokingApp.bean.WeiXinInfo;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.TerrorEvent;
import com.example.jokingApp.utils.event.WeiXinEvent;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.widgets.LoadingPage;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;

/**
 * Created by idea-pc on 2016/5/10.
 */
public class TerrorFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @InjectView(R.id.recyclerView)
    XRecyclerView mRecyclerView;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    RxBus mRxBus;

    //http://route.showapi.com/955-1?showapi_appid=18760&type=mj&page=1&showapi_sign=fb9e2d61cd5e44f7966d0c6842d56c61
    String URL = "http://route.showapi.com/"; //基础网址
    String SHOWAPI_APPID = "18760";  //key值
    String SHOWAPI_SIGN = "fb9e2d61cd5e44f7966d0c6842d56c61";  //key值
    String PAGE = "1"; //返回文章数量

    private TerrorAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ApiService mApiService;
     List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean>  data;
    int i=1;

    @Override
    protected View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_terror, null);
        ButterKnife.inject(this, view);
        Fresco.initialize(mActivity);
        mRecyclerView.setLoadingListener(this);
        mAdapter = new TerrorAdapter(data, mActivity);
        mLinearLayoutManager = new LinearLayoutManager(mActivity);

        initRxBus();
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private void initRxBus() {
        mRxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof TerrorEvent) {
                    mLinearLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
                }
            }
        });
    }

    @Override
    protected LoadingPage.LoadResult load() {
        mApiService = RetrofitUtils.createApiToGson(URL, ApiService.class);
        //mj为民间故事
        Call<TerroeInfo> call = mApiService.getTerror(SHOWAPI_APPID,SHOWAPI_SIGN,"1","mj");
        TerroeInfo mTerroeInfo = null;
        try {
            mTerroeInfo = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mTerroeInfo == null) {
            return LoadingPage.LoadResult.error;
        }
         List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> newslist = mTerroeInfo
                .getShowapi_res_body().getPagebean().getContentlist();

        System.out.println(newslist.size()+"鬼故事");
        
        if (data == null) {
            data = newslist;
        }
        return LoadingPage.LoadResult.success;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }


    @Override
    public void onRefresh() {
        Call<TerroeInfo> call = mApiService.getTerror(SHOWAPI_APPID,SHOWAPI_SIGN,"1","mj");
        call.enqueue(new Callback<TerroeInfo>() {
            @Override
            public void onResponse(Call<TerroeInfo> call, Response<TerroeInfo> response) {
                 List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> newslist = response.body()
                        .getShowapi_res_body().getPagebean().getContentlist();
                data.clear();
                data.addAll(newslist);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();
                Toast.makeText(UiUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TerroeInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        i++;
        Call<TerroeInfo> call = mApiService.getTerror(SHOWAPI_APPID,SHOWAPI_SIGN,i+"","mj");
        call.enqueue(new Callback<TerroeInfo>() {
            @Override
            public void onResponse(Call<TerroeInfo> call, Response<TerroeInfo> response) {
                 List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> newslist = response.body()
                        .getShowapi_res_body().getPagebean().getContentlist();
                if (newslist != null) {
                    System.out.println(data.size() + "服务器数据不为null");
                    data.addAll(newslist);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                } else {
                    mRecyclerView.loadMoreComplete();
                    mToastHelper.showToast("没有数据了");
                    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
                }
            }

            @Override
            public void onFailure(Call<TerroeInfo> call, Throwable t) {
                mToastHelper.showToast("加载失败");
            }
        });
    }
}
