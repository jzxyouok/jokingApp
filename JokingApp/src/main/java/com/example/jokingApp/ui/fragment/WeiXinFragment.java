package com.example.jokingApp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.FantasticAdapter;
import com.example.jokingApp.adapter.WeiXinAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.WeiXinInfo;
import com.example.jokingApp.bean.WeiXinInfo;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.FantasticEvent;
import com.example.jokingApp.utils.event.WeiXinEvent;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.widgets.LoadingPage;
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
public class WeiXinFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @InjectView(R.id.recyclerView)
    XRecyclerView mRecyclerView;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    RxBus mRxBus;


    // http://api.huceo.com/wxnew/?key=14e59ebf7e28a04aac0d1a1a885bacce&num=20&page=1
    String URL = "http://api.huceo.com/"; //基础网址
    String KEY = "14e59ebf7e28a04aac0d1a1a885bacce";  //key值
    String NUM = "20"; //返回文章数量

    private WeiXinAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ApiService mApiService;
    List<WeiXinInfo.NewslistBean> data;
    int i=1;

    @Override
    protected View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_weixin, null);
        ButterKnife.inject(this, view);
        mRecyclerView.setLoadingListener(this);
        mAdapter = new WeiXinAdapter(data, mActivity);
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
                if (o instanceof WeiXinEvent) {
                    mLinearLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
                }
            }
        });
    }

    @Override
    protected LoadingPage.LoadResult load() {
        mApiService = RetrofitUtils.createApiToGson(URL, ApiService.class);
        Call<WeiXinInfo> call = mApiService.getWeiXin(KEY, NUM, "1");
        WeiXinInfo mWeiXinInfo = null;
        try {
            mWeiXinInfo = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mWeiXinInfo == null) {
            return LoadingPage.LoadResult.error;
        }
        List<WeiXinInfo.NewslistBean> newslist = mWeiXinInfo.getNewslist();
        System.out.println(newslist.size()+"微信");
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
        Call<WeiXinInfo> infoCall = mApiService.getWeiXin(KEY, NUM, "1");
        infoCall.enqueue(new Callback<WeiXinInfo>() {
            @Override
            public void onResponse(Call<WeiXinInfo> call, Response<WeiXinInfo> response) {
                List<WeiXinInfo.NewslistBean> newslist = response.body().getNewslist();
                data.clear();
                data.addAll(newslist);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();
                Toast.makeText(UiUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<WeiXinInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        i++;
        Call<WeiXinInfo> infoCall = mApiService.getWeiXin(KEY, NUM, i + "");
        infoCall.enqueue(new Callback<WeiXinInfo>() {
            @Override
            public void onResponse(Call<WeiXinInfo> call, Response<WeiXinInfo> response) {
                List<WeiXinInfo.NewslistBean> newslist = response.body().getNewslist();
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
            public void onFailure(Call<WeiXinInfo> call, Throwable t) {
                mToastHelper.showToast("加载失败");
            }
        });
    }
}
