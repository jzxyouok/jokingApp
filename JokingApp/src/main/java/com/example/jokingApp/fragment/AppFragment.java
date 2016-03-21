package com.example.jokingApp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.AppAdapter;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.Loadingpager;

import java.util.ArrayList;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class AppFragment extends BaseFragment {
    private ArrayList<String> mStrings = new ArrayList<String>();
    @Override
    public View createSuccessView() {
//        TextView textView = new TextView(getContext());
//        textView.setText("AppFragment");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        mStrings.add("123");
        View view = View.inflate(getContext(), R.layout.fragment_app, null);
        RecyclerView mRecycleView = (RecyclerView) view.findViewById(R.id.recylerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(layoutManager);

        mRecycleView.setAdapter(new AppAdapter(mStrings));

        return view;
    }

    @Override
    public Loadingpager.LoadResult load() {
        return Loadingpager.LoadResult.success;
    }
}

