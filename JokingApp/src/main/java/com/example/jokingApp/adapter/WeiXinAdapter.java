package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.FantasticInfo;
import com.example.jokingApp.bean.WeiXinInfo;
import com.example.jokingApp.ui.activity.FantasticActivity;
import com.example.jokingApp.ui.activity.WeiXinActivity;
import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class WeiXinAdapter extends RecyclerView.Adapter {
    Activity mActivity;
    List<WeiXinInfo.NewslistBean> data;


    public WeiXinAdapter(List<WeiXinInfo.NewslistBean> data, Activity mActivity) {
        this.data = data;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.fragment_item_weixin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        String title = data.get(position).getTitle(); //标题
        String time = data.get(position).getCtime(); //时间
        String des = data.get(position).getDescription();
        //首页图片网址
        String mPicUrl = data.get(position).getPicUrl();
        System.out.println("weixin"+mPicUrl);
        Picasso.with(UiUtils.getContext()).load(mPicUrl).resize(70, 70).centerCrop().config(Bitmap.Config.RGB_565)
                .into(mHolder.mImage);
        mHolder.mTitle.setText(des);
        mHolder.mDesc.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.size() == 0 ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image)
        ImageView mImage;
        @InjectView(R.id.title)
        TextView mTitle;
        @InjectView(R.id.desc)
        TextView mDesc;
        @InjectView(R.id.linear)
        LinearLayout mLinear;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            mLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = ViewHolder.this.getAdapterPosition()-1;
                    String url = data.get(position).getUrl();
                    String picurl = data.get(position).getPicUrl();
                    String title = data.get(position).getTitle();
                    Intent intent = new Intent(mActivity, WeiXinActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("picurl", picurl);
                    intent.putExtra("title", title);
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
