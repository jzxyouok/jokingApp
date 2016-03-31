package com.example.jokingApp.fragment;

import android.view.View;

import com.example.jokingApp.view.LoadingPage;

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
}
