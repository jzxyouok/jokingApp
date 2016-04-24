package com.example.jokingApp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jokingApp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/4/16.
 */
public class MusicAdapter extends RecyclerView.Adapter {
    private Activity mActivity;

    public MusicAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ViewGroup.inflate(mActivity, R.layout.fragment_music_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ViewHolder  extends RecyclerView.ViewHolder{
        @InjectView(R.id.fragment_main_item_img)
        ImageView mFragmentMainItemImg;
        @InjectView(R.id.text_up)
        TextView mTextUp;
        @InjectView(R.id.text_down)
        TextView mTextDown;
        @InjectView(R.id.time)
        TextView mTime;
        @InjectView(R.id.root)
        LinearLayout mRoot;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

        }
    }
}


