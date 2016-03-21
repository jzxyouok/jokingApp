package com.example.jokingApp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.R;

/**
 * Created by idea-pc on 2016/3/21.
 */
public class AppHolder extends RecyclerView.ViewHolder {
    public final TextView mTextView;
    public AppHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
    }
}
