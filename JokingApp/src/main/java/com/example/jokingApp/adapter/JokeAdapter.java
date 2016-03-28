package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jokingApp.DetailActivity;
import com.example.jokingApp.MainActivity;
import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/22.
 */
public class JokeAdapter extends RecyclerView.Adapter {
    private List<JokeInfo.JokeBean> data;
    private  Activity mActivity;
    public JokeAdapter(List<JokeInfo.JokeBean> data, Activity activity) {
        this.data = data;
        mActivity= activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        mHolder.mIdView.setText(data.get(position).getName());
        mHolder.mContentView.setText(data.get(position).getDes());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

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
            Intent intent = new Intent();
            intent.setClass(mActivity, DetailActivity.class);
            intent.putExtra("url", "https://www.baidu.com");
            mActivity.startActivity(intent);
        }
    }
}