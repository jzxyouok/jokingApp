package com.example.jokingApp.ui.fragment;

import android.view.View;

import com.example.jokingApp.widgets.LoadingPage;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class SettingFragment extends BaseFragment {
    @Override
    public View createSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public void initInjector() {

    }
}