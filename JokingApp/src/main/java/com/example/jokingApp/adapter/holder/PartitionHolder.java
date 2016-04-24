package com.example.jokingApp.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.UiUtils;

/**
 * Created by idea-pc on 2016/3/24.
 */
public class PartitionHolder  extends RecyclerView.ViewHolder  implements View.OnClickListener {

    public final ImageView mImageView;
    public final TextView mTextView;

    public PartitionHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.image);
        mTextView = (TextView) itemView.findViewById(R.id.text);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(UiUtils.getContext(),"没有实现功能",Toast.LENGTH_LONG).show();
    }
}
