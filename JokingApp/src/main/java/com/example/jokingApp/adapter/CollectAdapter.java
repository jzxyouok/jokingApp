package com.example.jokingApp.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jokingApp.db.JokeEntity;
import com.example.jokingApp.R;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/22.
 */
public class CollectAdapter extends AnimRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;

    private List<JokeEntity>  data;
    private Activity mActivity;

    public CollectAdapter(List<JokeEntity>  data, Activity activity) {
        this.data = data;
        mActivity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
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

            //这个动画效果有bug
            //showItemAnim(((ViewHolder) holder).mCardView, position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
            return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }

    public JokeEntity getItem(int position) {
        return data == null ? null : data.get(position);
    }
}