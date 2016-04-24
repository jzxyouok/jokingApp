package com.example.jokingApp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.adapter.holder.AppHolder;
import com.example.jokingApp.protocol.JokeProtocol;
import com.example.jokingApp.utils.UiUtils;

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


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.item_fragment_app, parent, false);
            mAppHolder = new AppHolder(view);
            return mAppHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == DEFAULT_ITEM) {
            mAppHolder.mTitle.setText(mJokeBeen.get(position).getName());
            mAppHolder.mDesc.setText(mJokeBeen.get(position).getDes());
        }
        if (itemViewType == MORE_ITEM) {
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

    protected List<JokeInfo.JokeBean> onLoad() {

        JokeProtocol protocol = new JokeProtocol();
        List<JokeInfo.JokeBean> load = (List<JokeInfo.JokeBean>) protocol.load(1);
        if (load != null) {
            mJokeBeen.addAll(load);
        }
        return load;
    }
}
