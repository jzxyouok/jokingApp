package com.example.jokingApp.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.jokingApp.R;
import com.example.jokingApp.view.Loadingpager;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class HomeFragment extends BaseFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }
    @Override
    public View createSuccessView() {
        View view = View.inflate(getContext(),R.layout.recyle,null);
        return view;
    }

    @Override
    public Loadingpager.LoadResult load() {
        return Loadingpager.LoadResult.success;
    }
}
