package com.example.jokingApp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.R;
import com.example.jokingApp.holder.ImageHolder;
import com.example.jokingApp.holder.ViewPagerHoler;
import com.example.jokingApp.utils.UiUtils;

import java.util.List;

/**
 * 面向holder编程
 * Created by idea-pc on 2016/3/22.
 */
public class HomeAdapter extends RecyclerView.Adapter {
    private static final int TYPE_VIEWPAGER = 0;
    private static final int TYPE_NORMAL = 1;
    private List<String> data;

    public HomeAdapter(List<String> mList) {
        this.data = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIEWPAGER) {
            View view = getViewHolder(R.layout.fragment_item_home_viewpager, parent, true);
            return new ViewPagerHoler(view);
        } else {
            View view = getViewHolder(R.layout.item_frgment_home_image, parent, false);
            return new ImageHolder(view, data);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType==TYPE_NORMAL){
            position = position - 1;
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.initData(position);
        }

    }


    private View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view
                .getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_VIEWPAGER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return 1 + data.size();
    }


}
