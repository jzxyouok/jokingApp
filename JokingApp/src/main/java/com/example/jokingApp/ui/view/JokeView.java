package com.example.jokingApp.ui.view;

import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.bean.MeizhiInfo;

import java.util.List;

/**
 * Created by idea-pc on 2016/4/20.
 */
public interface JokeView extends BaseView {


    //下拉刷新
    void  onRefreshing();
    //刷新完成
    void onRefreshCompleted ();

    //加载更多逻辑
     void  onLoadMore();
    //加载更多完成
    void  onLoadCompleted();

}
