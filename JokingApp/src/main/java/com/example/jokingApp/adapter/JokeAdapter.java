package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jokingApp.ui.DetailActivity;
import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/22.
 */
public class JokeAdapter extends RecyclerView.Adapter {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<JokeInfo.JokeBean> data;
    private Activity mActivity;

    public JokeAdapter(List<JokeInfo.JokeBean> data, Activity activity) {
        this.data = data;
        mActivity = activity;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent,
                    false);
            return new FootViewHolder(view);
        }
        return null ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType==TYPE_ITEM){
            ViewHolder mHolder = (ViewHolder) holder;
            mHolder.mIdView.setText(data.get(position).getName());
            mHolder.mContentView.setText(data.get(position).getDes());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return data.size()+1 ;
    }//+1 是底部加载更多

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = this.getAdapterPosition();
            JokeInfo.JokeBean jokeBean = data.get(adapterPosition);

            Intent intent = new Intent();
            intent.setClass(mActivity, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data",  jokeBean);
            intent.putExtras(bundle);
           // intent.putExtra("url", "https://www.baidu.com");
            mActivity.startActivity(intent);
        }
    }
    static class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }
    public JokeInfo.JokeBean getItem(int position) {
        return data == null ? null : data.get(position);
    }
}