package com.example.jokingApp.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.MeiZhiAdapter;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.presenter.VideoPresenter;
import com.example.jokingApp.ui.view.VideoView;
import com.example.jokingApp.widgets.LoadingPage;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/4/16.
 */
public class VideoFragment extends BaseFragment implements VideoView {
    @InjectView(R.id.RecyclerView)
    RecyclerView mRecyclerView;


    @Inject
    VideoPresenter mVideoPresenter;
    @Inject
    MeiZhiAdapter mMeiZhiAdapter;


    private StaggeredGridLayoutManager mManager;

    @Override
    protected View createSuccessView() {

        View view = View.inflate(mActivity, R.layout.fragment_video, null);
        ButterKnife.inject(this, view);
        mVideoPresenter.attachView(this);


        mManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mManager);

        mRecyclerView.setAdapter(mMeiZhiAdapter);

        return view;

    }

    /**
     * 刚进入页面的时候请求服务器
     *
     * @return 请求成功||请求失败
     */
    @Override
    protected LoadingPage.LoadResult load() {
        return mVideoPresenter.loadServerData();
    }


    @Override
    public void setAdapterData(List<MeizhiInfo.ResultsBean> mResults) {
        mMeiZhiAdapter.bind(mResults);
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }



}
