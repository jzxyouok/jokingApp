package com.example.jokingApp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.MeiZhiAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.VideoEvent;
import com.example.jokingApp.widgets.LoadingPage;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by idea-pc on 2016/4/16.
 */
public class VideoFragment extends BaseFragment {
    @InjectView(R.id.RecyclerView)
    RecyclerView mRecyclerView;
    @Inject
    RxBus mRxBus;

    MeiZhiAdapter mMeiZhiAdapter;

    private String URL = "http://gank.io/api/data/";
    private ApiService mApiService;
    private  List<MeizhiInfo.ImageBean> mResults;
    private LinearLayoutManager mLinearLayoutManager;
    private Subscription mSubscribe;

    @Override
    protected View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_video, null);
        ButterKnife.inject(this, view);
        mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMeiZhiAdapter = new MeiZhiAdapter(mResults,mActivity);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    Picasso.with(UiUtils.getContext()).resumeTag(GlobalConstant.tag);
                }
                else
                {
                    Picasso.with(UiUtils.getContext()).pauseTag(GlobalConstant.tag);
                }
            }
        });
        mRecyclerView.setAdapter(mMeiZhiAdapter);
        initRxBus();
        return view;

    }

    private void initRxBus() {
        mSubscribe = mRxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof VideoEvent) {
                    mLinearLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
                }
            }
        });
    }



    /**
     * 刚进入页面的时候请求服务器
     *
     * @return 请求成功||请求失败
     */
    @Override
    protected LoadingPage.LoadResult load() {
        mApiService = RetrofitUtils.createApiToGson( ApiService.class);
         Call<MeizhiInfo> call = mApiService.getMeiZhi();
        MeizhiInfo mMeizhiInfo=null;
        try {
            mMeizhiInfo = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mMeizhiInfo == null) {
            return LoadingPage.LoadResult.error;
        }
         List<MeizhiInfo.ImageBean> image = mMeizhiInfo.getImage();
        System.out.println(image.size());
        if (mResults == null) {
            mResults = image;
        }
        return LoadingPage.LoadResult.success;

    }


    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

}
