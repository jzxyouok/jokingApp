package com.example.jokingApp.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.view.Loadingpager;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class SubjectFragment extends BaseFragment {
    @Override
    public View createSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText("SubjectFragment");
        return textView;
    }

    @Override
    public Loadingpager.LoadResult load() {
        return null;
    }
}
