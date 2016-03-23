package com.example.jokingApp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alafighting.loadmore.OnLoadmoreListener;
import com.alafighting.loadmore.RecyclerSwipeHelper;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.GameAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.ThreadManager;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class JokeFragment extends BaseFragment {
    private List<JokeInfo.JokeBean> mJokeBeen;
    private List<JokeInfo.JokeBean> data; // 下拉刷新后 获得的数据
    private SwipeRefreshLayout swipeRefreshLayout;
    private  RecyclerView recyclerView;
    private GameAdapter mGameAdapter;
    private RecyclerSwipeHelper helper;
    private  static  int i = 0; //加载更多的次数

    @Override
    public View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.fragment_game, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        recyclerView= (RecyclerView) view.findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        mGameAdapter = new GameAdapter(mJokeBeen);
        recyclerView.setAdapter(mGameAdapter);

        // 初始化辅助类
        helper = new RecyclerSwipeHelper(swipeRefreshLayout, recyclerView);
        // 监听下拉刷新
        helper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        helper.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore() {
                    ThreadManager.getInstance().createLongPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            if (++i < 3) {
                                JokeProtocol jokeProtocol = new JokeProtocol();
                                List<JokeInfo.JokeBean> data = (List<JokeInfo.JokeBean>) jokeProtocol.load(i);
                                if (data != null) {//这里加个判断语句 为了保证  如果服务器返回为null的话 程序不会崩溃
                                    mJokeBeen.addAll(data);
                                }
                            }
                            UiUtils.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (i < 3) {
                                        helper.setLoadmoreing(false);
                                        mGameAdapter.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                                        helper.setEnabledLoadmore(false);
                                    }
                                }
                            });
                        }
                    });
            }
        });
        return view;
    }

    private void refresh() {
        // 重新启用加载更多
        helper.setEnabledLoadmore(true);
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                JokeProtocol jokeProtocol = new JokeProtocol();
                List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
                i=0;
                mJokeBeen.clear();
                mJokeBeen.addAll(load);
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        helper.setRefreshing(false);
                        mGameAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    /**
     * 请求服务器的数据
     * 之后执行的createSuccessView()
     * @return
     */
    @Override
    public Loadingpager.LoadResult load() {
        JokeProtocol jokeProtocol = new JokeProtocol();
        mJokeBeen = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
        return checkData(mJokeBeen);
    }
}
