package com.example.jokingApp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.AppAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class AppFragment extends BaseFragment {
    private List<JokeInfo.JokeBean> mJokeBeen;
    private AppAdapter mAppAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.fragment_app, null);
        RecyclerView mRecycleView = (RecyclerView) view.findViewById(R.id.recylerview);
        SwipeRefreshLayout mRresh= (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

        mRecycleView.setAdapter(new AppAdapter());

        return view;
    }

    @Override
    public Loadingpager.LoadResult load() {
        JokeProtocol homeProtocol = new JokeProtocol();
        mJokeBeen = (List<JokeInfo.JokeBean>)homeProtocol.load(0);
        System.out.println(mJokeBeen.size());
        return Loadingpager.LoadResult.empty;
    }
}

