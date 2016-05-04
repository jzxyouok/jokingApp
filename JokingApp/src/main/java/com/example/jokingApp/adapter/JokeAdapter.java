package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.ui.activity.DetailActivity;
import com.example.jokingApp.ui.activity.MainActivity;
import com.example.jokingApp.utils.UiUtils;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/22.
 */
public class JokeAdapter extends AnimRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<JokeInfo.JokeBean> data;
    private boolean isLight;

    public void setData(List<JokeInfo.JokeBean> data) {
        this.data = data;
    }

    private Activity mActivity;


    //是否有加载更多的功能
    private boolean isLoadingMore = true;


    public JokeAdapter( Activity activity, boolean isLoadingMore) {
      //  this.data = data;
        mActivity = activity;
        this.isLoadingMore = isLoadingMore;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == TYPE_ITEM) {
            ViewHolder mHolder = (ViewHolder) holder;
            mHolder.mIdView.setText(data.get(position).getName());
            mHolder.mContentView.setText(data.get(position).getDes());
            if (!isLight) {
                mHolder.mIdView.setTextColor(UiUtils.getResource().getColor(R.color.toobar_back_night));
                mHolder.mContentView.setTextColor(UiUtils.getResource().getColor(R.color.toobar_back_night));
            } else {
                mHolder.mIdView.setTextColor(UiUtils.getResource().getColor(android.R.color.white));
                mHolder.mContentView.setTextColor(UiUtils.getResource().getColor(android.R.color.white));
            }
            mHolder.mCardView.setBackgroundColor(isLight ? Color.parseColor("#ff303030") : Color.parseColor("#ffffffff"));

            //这个动画效果有bug
            //showItemAnim(((ViewHolder) holder).mCardView, position);
        }

    }


    @Override
    public int getItemViewType(int position) {
        //如果是最后一个位置  返回脚布局
        if (position + 1 == getItemCount() && isLoadingMore) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        if (isLoadingMore) {//+1 是底部加载更多
            return data.size() + 1;
        } else if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mIdView;
        public final TextView mContentView;
        public final LinearLayout mLine;
        public final CardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
           mLine= (LinearLayout) view.findViewById(R.id.root_view);
            mCardView= (CardView) view.findViewById(R.id.cardview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = this.getAdapterPosition();
            JokeInfo.JokeBean jokeBean = data.get(adapterPosition);

            Intent intent = new Intent();
            intent.setClass(mActivity, DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", jokeBean);
            intent.putExtras(bundle);
            // intent.putExtra("url", "https://www.baidu.com");
            mActivity.startActivity(intent);

        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View view) {
            super(view);
        }
    }

    public JokeInfo.JokeBean getItem(int position) {
        return data == null ? null : data.get(position);
    }

    //  设置是否加载更多
    public void setIsLoadingMore(boolean isLoadingMore) {
        this.isLoadingMore = isLoadingMore;
    }

    public void updateTheme() {
        isLight= !isLight;
        notifyDataSetChanged();
    }
}