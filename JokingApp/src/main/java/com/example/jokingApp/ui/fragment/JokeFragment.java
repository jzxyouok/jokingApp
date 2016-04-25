package com.example.jokingApp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.api.ApiService;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.RetrofitUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.DayModelEvent;
import com.example.jokingApp.utils.event.NightModelEvent;
import com.example.jokingApp.widgets.DividerItemDecoration;
import com.example.jokingApp.widgets.LoadingPage;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class JokeFragment extends BaseFragment {
    private List<JokeInfo.JokeBean> mJokeBeen;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    boolean isLoading;
    private FloatingActionButton mFloatingActionButton;
    private JokeAdapter mJokeAdapter;

    private boolean isLight;
    @Inject
    RxBus mRxBus;


    /**
     * activityCreated 的时候就请求数据
     * 其他的fragment   ViewPager 设置滑动监听 调用show()方法
     *
     * @param savedInstanceState
     */
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
        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        //初始化 recylerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));
        recyclerView.setHasFixedSize(true);

        mJokeAdapter = new JokeAdapter( mActivity, true);
        mJokeAdapter.setData(mJokeBeen);
        mJokeAdapter.setIsLoadingMore(true);
        recyclerView.setAdapter(mJokeAdapter);

        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshing();
            }
        });
        //加载更多
        initLoadMoreListener(layoutManager);
        //设置folatingaction
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutManager.smoothScrollToPosition(recyclerView, null, 0);
            }
        });
        initRxBus();
        return view;

    }

    private void initRxBus() {
        mRxBus.toObserverable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof NightModelEvent) {
                            mJokeAdapter.updateTheme();
                        } else if (o instanceof DayModelEvent) {
                            mJokeAdapter.updateTheme();
                        }
                        isLight=!isLight;
                    }
                });
    }


    /**
     * recylerview 滚动监听
     *
     * @param layoutManager
     */
    private void initLoadMoreListener(final LinearLayoutManager layoutManager) {
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
                    //加载更多的时候  如果下拉刷新 正在刷新 那么移除加载更多控件 返回
                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                        return;
                    }
                    //是否加载更多  防止重复调用
                    if (!isLoading) {
                        isLoading = true;
                        onLoadMore();
                    }
                }
            }
        });
    }

    /**
     * 加载更多数据
     */
    private void onLoadMore() {
        //这里new Action1  只关注请求成功
        ApiService api = RetrofitUtils.createApiToGson(ApiService.class);
        api.getJoke("joke", new Random().nextInt(2)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Action1<JokeInfo>() {
            @Override
            public void call(JokeInfo jokeInfo) {
                List<JokeInfo.JokeBean> joke = jokeInfo.getJoke();
                mJokeBeen.addAll(joke);
                mJokeAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                isLoading = false;
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void onRefreshing() {
        Observable.create(new Observable.OnSubscribe<List<JokeInfo.JokeBean>>() {
            @Override
            public void call(Subscriber<? super List<JokeInfo.JokeBean>> subscriber) {
                JokeProtocol jokeProtocol = new JokeProtocol();
                final List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
                subscriber.onNext(load);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<JokeInfo
                .JokeBean>>() {
            @Override
            public void call(List<JokeInfo.JokeBean> jokeBeen) {
                mJokeBeen.clear();
                mJokeBeen.addAll(jokeBeen);
                mJokeAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                mJokeAdapter.notifyItemRemoved(mJokeAdapter.getItemCount());
                Toast.makeText(UiUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 请求服务器的数据
     * 之后执行的createSuccessView()
     * @return
     */
    @Override
    public LoadingPage.LoadResult load() {
        JokeProtocol jokeProtocol = new JokeProtocol();
        List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) jokeProtocol.load(0);
        if (mJokeBeen == null) {
            mJokeBeen = load;
        }
        return checkData(load);
    }

    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }


}
