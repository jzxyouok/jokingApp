package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.MeizhiInfo;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.ui.activity.VideoActivity;
import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/4/16.
 */
public class MeiZhiAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder>{

    List<MeizhiInfo.ImageBean> data;
    Activity mActivity;

    public MeiZhiAdapter( List<MeizhiInfo.ImageBean> data, Activity mActivity) {
        this.data = data;
        this.mActivity=mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.item_frgment_home_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder mHolder = (ViewHolder) holder;
        String url = GlobalConstant.SERVER_URL+data.get(position).getImageurl();

        Picasso with = Picasso.with(UiUtils.getContext());
        final Random random = new Random();
        final int i = random.nextInt(4)+1;
        with .load(url)
                .resize(200,400+i*30)
                .centerCrop()
                .tag(GlobalConstant.tag)
                .placeholder(R.mipmap.beauty2)
                .config(Bitmap.Config.RGB_565)
                .into(mHolder.mImage);
        mHolder.mText.setText("视频图片");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.image)
        public ImageView mImage;
        @InjectView(R.id.text)
        public TextView mText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mActivity, VideoActivity.class);
            mActivity.startActivity(intent);
        }
    }

}
