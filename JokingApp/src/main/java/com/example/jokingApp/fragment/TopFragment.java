package com.example.jokingApp.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alafighting.loadmore.RecyclerSwipeHelper;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.GameAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.protocol.HomeProtocol;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

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
