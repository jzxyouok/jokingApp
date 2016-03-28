package com.example.jokingApp.fragment;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alafighting.loadmore.OnLoadmoreListener;
import com.alafighting.loadmore.RecyclerSwipeHelper;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.ThreadManager;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.DividerItemDecoration;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class JokeFragment extends BaseFragment {
    private List<JokeInfo.JokeBean> mJokeBeen;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  RecyclerView recyclerView;
    private JokeAdapter mJokeAdapter;
    private RecyclerSwipeHelper helper;
    private  static  int i = 0; //加载更多的次数
    private  Activity mActivity;

    public JokeFragment(Activity activity) {
        mActivity=  activity;
    }

    @Override
    public View createSuccessView() {
        View view = View.inflate(getContext(), R.layout.fragment_game, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        recyclerView= (RecyclerView) view.findViewById(R.id.list);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) ;
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        recyclerView.setHasFixedSize(true);
        mJokeAdapter = new JokeAdapter(mJokeBeen,mActivity);
        recyclerView.setAdapter(mJokeAdapter);

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
                onLoadMore();
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
                            mJokeAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getContext(),"没有更多数据",Toast.LENGTH_LONG).show();
                            //没有更多数据的时候  让该控件不再响应
                            helper.setEnabledLoadmore(false);
                        }
                    }
                });
            }
        });
    }

    /**
     * 下拉刷新
     */
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
                        mJokeAdapter.notifyDataSetChanged();
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
