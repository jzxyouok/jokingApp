package com.example.jokingApp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.AppAdapter;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class MoreHolder extends RecyclerView.ViewHolder {
    public static final int HAS_NO_MORE=0;  // 没有额外数据了
    public static final int LOAD_ERROR=1;// 加载失败
    public static final int HAS_MORE=2;//  有额外数据
    private int data;

    private RelativeLayout rl_more_loading, rl_more_error;
    private AppAdapter adapter;


    public MoreHolder(View itemView) {
        super(itemView);
    }
    public MoreHolder(View itemView,AppAdapter adapter) {
        super(itemView);
        this.adapter=adapter;
        rl_more_loading = (RelativeLayout) itemView.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) itemView.findViewById(R.id.rl_more_error);


    }
    public void loadMore() {
        // 请求服务器   加载下一批数据
        //  交给Adapter  让Adapter  加载更多数据
        adapter.loadMore();
    }
    public void setData(int data){
        this.data=data;
        refreshView(data);
    }
    public void refreshView(int data) {
        rl_more_error.setVisibility(data==LOAD_ERROR?View.VISIBLE:View.GONE);
        rl_more_loading.setVisibility(data==HAS_MORE?View.VISIBLE:View.GONE);
    }
}

