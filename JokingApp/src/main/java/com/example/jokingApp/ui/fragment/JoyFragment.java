package com.example.jokingApp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JoyAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.JoyInfo;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.widgets.LoadingPage;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 笑话大全
 * Created by idea-pc on 2016/5/8.
 */
public class JoyFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    String SHOWAPI_APPID = "18760";
    String SHOWAPI_SIGN = "fb9e2d61cd5e44f7966d0c6842d56c61";
    String MAXRESULT = "20";

    @InjectView(R.id.recyclerView)
    XRecyclerView mRecyclerView;
    @Inject
    ToastHelper mToastHelper;

    private List<JoyInfo.ShowapiResBodyBean.ContentlistBean> data;
    private Call<JoyInfo> mCall;
    private JoyAdapter mJoyAdapter;
    private ApiService mApiService;
    int i;

    @Override
    protected View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_joy, null);
        ButterKnife.inject(this, view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mJoyAdapter = new JoyAdapter(data, mActivity);
        mRecyclerView.setAdapter(mJoyAdapter);
        mRecyclerView.setLoadingListener(this) ;
        mRecyclerView.setPullRefreshEnabled(false);
        return view;
    }

    @Override
    protected LoadingPage.LoadResult load() {
        mApiService = RetrofitUtils.createApiToGson("http://route.showapi.com/", ApiService.class);
        mCall = mApiService.getJoyInfo(SHOWAPI_APPID, SHOWAPI_SIGN, "1", MAXRESULT);
        JoyInfo mJoyInfo = null;
        try {
            mJoyInfo = mCall.execute().body();
            System.out.println(mJoyInfo.getShowapi_res_body().getContentlist().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mJoyInfo == null) {
            System.out.println("结果为null");
            return LoadingPage.LoadResult.error;
        }
        List<JoyInfo.ShowapiResBodyBean.ContentlistBean> contentlist = mJoyInfo.getShowapi_res_body().getContentlist();

        if (data == null) {
            data = contentlist;
        }
        return LoadingPage.LoadResult.success;
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        System.out.println(data.size());
        i++;
        Call<JoyInfo> infoCall = mApiService.getJoyInfo(SHOWAPI_APPID, SHOWAPI_SIGN, i+"", MAXRESULT);
        infoCall.enqueue(new Callback<JoyInfo>() {
            @Override
            public void onResponse(Call<JoyInfo> call, Response<JoyInfo> response) {
                 List<JoyInfo.ShowapiResBodyBean.ContentlistBean> loadMoreData = response.body()
                        .getShowapi_res_body().getContentlist();
                if (loadMoreData!=null){
                    data.addAll(loadMoreData);
                    mJoyAdapter.notifyDataSetChanged();
                    mRecyclerView.loadMoreComplete();
                }else {
                    mToastHelper.showToast("没有数据了");
                    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
                }
            }
            @Override
            public void onFailure(Call<JoyInfo> call, Throwable t) {
                mToastHelper.showToast("加载失败");
            }
        });
    }
}

