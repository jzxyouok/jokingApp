package com.example.jokingApp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.ThreadManager;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.DividerItemDecoration;
import com.example.jokingApp.view.LoadingPage;

import java.util.List;
import java.util.Random;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class JokeFragment extends BaseFragment {
    private List<JokeInfo.JokeBean> mJokeBeen;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private JokeAdapter mJokeAdapter;
    boolean isLoading;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    public View createSuccessView() {
        View view = View.inflate(mActivity, R.layout.fragment_game, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        recyclerView.setHasFixedSize(true);
        mJokeAdapter = new JokeAdapter(mJokeBeen, mActivity);
        recyclerView.setAdapter(mJokeAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshing();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mJokeAdapter.getItemCount()) {
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        onLoadMore();
                    }
                }
            }
        });
        return view;
    }

    /**
     * 加载更多数据
     */
    private void onLoadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                JokeProtocol jokeProtocol = new JokeProtocol();
                Random random = new Random();
                List<JokeInfo.JokeBean> data = (List<JokeInfo.JokeBean>) jokeProtocol.load(random.nextInt(2));
                mJokeBeen.addAll(data);
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mJokeAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                        isLoading = false;
                    }
                });
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void onRefreshing() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                JokeProtocol jokeProtocol = new JokeProtocol();
                final List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mJokeBeen.clear();
                        mJokeBeen.addAll(load);
                        mJokeAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                        Toast.makeText(UiUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    /**
     * 请求服务器的数据
     * 之后执行的createSuccessView()
     *
     * @return
     */
    @Override
    public LoadingPage.LoadResult load() {
        JokeProtocol jokeProtocol = new JokeProtocol();
        List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
        if(mJokeBeen==null){
            mJokeBeen=load;
        }
        return checkData(load);
    }

}
