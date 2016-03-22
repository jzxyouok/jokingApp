package com.example.jokingApp.adapter;

import android.content.SyncStatusObserver;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.holder.AppHolder;
import com.example.jokingApp.holder.MoreHolder;
import com.example.jokingApp.protocol.HomeProtocol;
import com.example.jokingApp.utils.ThreadManager;
import com.example.jokingApp.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class AppAdapter extends RecyclerView.Adapter {
    List<JokeInfo.JokeBean> mJokeBeen;
    private AppHolder mAppHolder;

    public void setPersons(List<JokeInfo.JokeBean> mJokeBeen) {
        this.mJokeBeen = mJokeBeen;
        notifyDataSetChanged();
    }

    private static final int MORE_ITEM = 1;
    private static final int DEFAULT_ITEM = 0;
    private MoreHolder mHolder;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == DEFAULT_ITEM) {
            View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.item_fragment_app, parent, false);
            mAppHolder = new AppHolder(view);
            return mAppHolder;
        } else {
            View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.load_more, parent, false);
            mHolder = new MoreHolder(view, this);
            return mHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == DEFAULT_ITEM) {
            mAppHolder.mTitle.setText(mJokeBeen.get(position).getName());
            mAppHolder.mDesc.setText(mJokeBeen.get(position).getDes());
        }
        if (itemViewType == MORE_ITEM) {
            mHolder.loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return mJokeBeen.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mJokeBeen.size()) { // 当前是最后一个条目
            return MORE_ITEM;
        }
        return DEFAULT_ITEM;
    }

    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
                // 在子线程中加载更多
                final List<JokeInfo.JokeBean> newData = onLoad();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData == null) {
                            mHolder.setData(MoreHolder.LOAD_ERROR);//
                        } else if (newData.size() == 0) {
                            mHolder.setData(MoreHolder.HAS_NO_MORE);
                        } else {
                            // 成功了
                            mHolder.setData(MoreHolder.HAS_MORE);
                            notifyDataSetChanged();// 刷新界面
                        }
                    }
                });
            }
        });
    }

    protected List<JokeInfo.JokeBean> onLoad() {

        HomeProtocol protocol = new HomeProtocol();
        List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) protocol.load(1);
        if (load != null) {
            mJokeBeen.addAll(load);
        }
        return load;
    }
}
