package com.example.jokingApp.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.ImageAdapter;
import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.protocol.ImagerProtocol;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.widgets.LoadingPage;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class ImageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.list)
    RecyclerView mRecyclerview;
    @InjectView(R.id.refresh_layout)
    public SwipeRefreshLayout mRefresh;
    private List<String> data;
    private ImageInfo mImageInfo;

    @Override
    public View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);

        //设置下拉刷新
        mRefresh.setOnRefreshListener(this);
        mRefresh.setColorSchemeResources(R.color.colorAccent);

        //设置数据的显示
        ImageAdapter imageAdapter = new ImageAdapter(mImageInfo);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(imageAdapter);

        return view;
    }

    /**
     * 其请求服务器
     * @return
     */
    @Override
    public LoadingPage.LoadResult load() {
        ImagerProtocol imagerProtocol = new ImagerProtocol();
        mImageInfo = (ImageInfo) imagerProtocol.load(0);
        if (mImageInfo!=null){
            data = mImageInfo.getPicture();
        }
        return checkData(data);
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        UiUtils.postDelay(new Runnable() {
            @Override
            public void run() {
                mRefresh.setRefreshing(false);
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }
        }, 3000);
    }
}
