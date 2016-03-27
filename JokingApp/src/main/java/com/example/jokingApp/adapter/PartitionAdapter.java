package com.example.jokingApp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.PartitionInfo;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.holder.PartitionHolder;
import com.example.jokingApp.utils.BitmapHelper;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/24.
 */
public class PartitionAdapter extends RecyclerView.Adapter {
    List<PartitionInfo.PictureBean> mPictureBeen ;
    public PartitionAdapter(List<PartitionInfo.PictureBean> mPictureBeen) {
        this.mPictureBeen=mPictureBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_fragment_partition, null);
        return  new PartitionHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PartitionHolder  mPartition= (PartitionHolder) holder;
        ImageView imageView = mPartition.mImageView;
        TextView textView =mPartition .mTextView;
        String  uri= GlobalConstant.SERVER_URL+mPictureBeen.get(position).getIconUrl();
        BitmapHelper.getBitmapUtils().display(imageView,uri);
        textView.setText(mPictureBeen.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mPictureBeen.size();
    }
}
