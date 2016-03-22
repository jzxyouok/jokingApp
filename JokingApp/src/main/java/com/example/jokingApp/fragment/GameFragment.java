package com.example.jokingApp.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.DummyContent;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.MyItemRecyclerViewAdapter;
import com.example.jokingApp.view.LoadMoreRecyclerView;
import com.example.jokingApp.view.Loadingpager;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class GameFragment extends BaseFragment {
    private int page = 0;
    private LoadMoreRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyItemRecyclerViewAdapter mMyItemRecyclerViewAdapter;

    @Override
    public View createSuccessView() {
//        TextView textView = new TextView(getContext());
//        textView.setText("GameFragment");
        View view = View.inflate(getContext(), R.layout.fragment_game, null);
        recyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMyItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(DummyContent.generyData(page));
        recyclerView.setAdapter(mMyItemRecyclerViewAdapter);
        recyclerView.setAutoLoadMoreEnable(true);
        recyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        mMyItemRecyclerViewAdapter.addDatas(DummyContent.generyData(++page));
                        recyclerView.notifyMoreFinish(DummyContent.hasMore(page));
                    }
                }, 1000);
            }
        });
        mMyItemRecyclerViewAdapter.notifyDataSetChanged();



        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },6000);
            }
        });
        return view;
    }

    @Override
    public Loadingpager.LoadResult load() {
        return Loadingpager.LoadResult.success;
    }
}
