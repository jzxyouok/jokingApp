package com.example.jokingApp.presenter;

import com.example.jokingApp.ui.view.BaseView;

/**
 * Created by idea-pc on 2016/4/11.
 */
public abstract class BasePresenter<T extends BaseView>{
    protected T view;

    public  void attachView(T view) {
        this.view = view;

    }
    public abstract void detachView();
}
