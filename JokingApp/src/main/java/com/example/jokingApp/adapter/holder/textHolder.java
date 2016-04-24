package com.example.jokingApp.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.R;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class textHolder extends RecyclerView.ViewHolder {

    public  TextView mTextView;

    public textHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
    }
}
