package com.example.jokingApp.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.HomeAdapter;
import com.example.jokingApp.protocol.HomeProtocol;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

  @InjectView(R.id.list)
    RecyclerView mRecyclerview;
    @InjectView(R.id.refresh_layout)
   public  SwipeRefreshLayout mRefresh;
    private List<String> mList;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
       ButterKnife.inject(this, view);

        mRefresh.setOnRefreshListener(this);
        mRefresh.setColorSchemeResources(R.color.colorAccent);
        HomeAdapter homeAdapter = new HomeAdapter(mList);

        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(homeAdapter);

        return view;
    }

    @Override
    public Loadingpager.LoadResult load() {
        HomeProtocol homeProtocol = new HomeProtocol();
        mList = (List<String>) homeProtocol.load(0);
        return checkData(mList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
            UiUtils.postDelay(new Runnable() {
                @Override
                public void run() {
                    mRefresh.setRefreshing(false);
                    Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_LONG).show();
                }
            },3000);
    }
}
