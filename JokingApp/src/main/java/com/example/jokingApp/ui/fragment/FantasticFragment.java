package com.example.jokingApp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.internal.MDAdapter;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.FantasticAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.FantasticEvent;
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
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 *新闻
 * 数据来源 天行
 * Created by idea-pc on 2016/5/8.
 */
public class FantasticFragment extends BaseFragment implements XRecyclerView.LoadingListener {
;
    @InjectView(R.id.recyclerView)
    XRecyclerView mRecyclerView;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    RxBus mRxBus;
    //http://api.huceo.com/social/?key=14e59ebf7e28a04aac0d1a1a885bacce&page=1&num=20
    String NUM = "20";
    String KEY="14e59ebf7e28a04aac0d1a1a885bacce";
    String URL="http://api.huceo.com/";
    private FantasticAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ApiService mApiService;
    List<FantasticInfo.NewslistBean> data ;
    int i=1 ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_fantastic, null);
        ButterKnife.inject(this, view);
        mRecyclerView.setLoadingListener(this);
        mAdapter = new FantasticAdapter(data,mActivity);
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
                if (o instanceof FantasticEvent) {
                    mLinearLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
                }
            }
        });
    }


    @Override
    protected LoadingPage.LoadResult load() {
        mApiService = RetrofitUtils.createApiToGson(URL, ApiService.class);
         Call<FantasticInfo> call = mApiService.getFantastic(KEY, "1", NUM);
        FantasticInfo mFantasticInfo=null;
        try {
            mFantasticInfo = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mFantasticInfo == null) {
            return LoadingPage.LoadResult.error;
        }
         List<FantasticInfo.NewslistBean> newslist = mFantasticInfo.getNewslist();

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
        Call<FantasticInfo> infoCall = mApiService.getFantastic(KEY, "1", NUM);
        infoCall.enqueue(new Callback<FantasticInfo>() {
            @Override
            public void onResponse(Call<FantasticInfo> call, Response<FantasticInfo> response) {
                List<FantasticInfo.NewslistBean> newslist = response.body().getNewslist();
                data.clear();
                data.addAll(newslist);
                mAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();
                Toast.makeText(UiUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<FantasticInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void onLoadMore() {
        i++;
        Call<FantasticInfo> infoCall = mApiService.getFantastic(KEY,i+"", NUM);
        infoCall.enqueue(new Callback<FantasticInfo>() {
            @Override
            public void onResponse(Call<FantasticInfo> call, Response<FantasticInfo> response) {
                List<FantasticInfo.NewslistBean> newslist = response.body().getNewslist();
                if (newslist!=null){
                    System.out.println(data.size()+"服务器数据不为null");
                    data.addAll(newslist);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }else {
                    mRecyclerView.loadMoreComplete();
                    mToastHelper.showToast("没有数据了");
                    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
                }
            }
            @Override
            public void onFailure(Call<FantasticInfo> call, Throwable t) {
                mToastHelper.showToast("加载失败");
            }
        });
    }

}
