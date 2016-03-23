package com.example.jokingApp.fragment;

import android.view.View;

import com.example.jokingApp.view.Loadingpager;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class TopFragment extends BaseFragment {
    @Override
    public View createSuccessView() {


        return null;
    }

    @Override
    public Loadingpager.LoadResult load() {
        return Loadingpager.LoadResult.empty;
    }
}
