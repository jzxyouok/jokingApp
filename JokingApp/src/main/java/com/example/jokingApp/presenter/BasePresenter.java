package com.example.jokingApp.presenter;

import android.support.annotation.NonNull;

import com.example.jokingApp.ui.view.BaseView;
import com.example.jokingApp.widgets.LoadingPage;

import java.util.List;

/**
 * Created by idea-pc on 2016/4/11.
 */
public abstract class BasePresenter<T extends BaseView>{

    protected T view;

    public  void attachView(@NonNull T view) {
        this.view = view;
    }


    public abstract void detachView();



    /**校验数据 */
    public LoadingPage.LoadResult checkData(List datas) {
        if(datas==null){
            return LoadingPage.LoadResult.error;//  请求服务器失败
        }else{
            if(datas.size()==0){
                return LoadingPage.LoadResult.empty;
            }else{
                return LoadingPage.LoadResult.success;
            }
        }

    }


}
