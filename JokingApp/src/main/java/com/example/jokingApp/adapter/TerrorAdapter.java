package com.example.jokingApp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.TerroeInfo;
import com.example.jokingApp.ui.activity.TerrorActivity;
import com.example.jokingApp.utils.UiUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class TerrorAdapter extends RecyclerView.Adapter {
    Activity mActivity;
    List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean>  data;
    int[] a = new int[]{R.mipmap.ic_1,R.mipmap.ic_2,R.mipmap.ic_3,R.mipmap.ic_4,R.mipmap.ic_5};
    Random random = new Random();

    public TerrorAdapter( List<TerroeInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean>  data, Activity mActivity) {
        this.data = data;
        this.mActivity = mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.fragment_item_terror, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        String title = data.get(position).getTitle(); //标题
        String desc = data.get(position).getDesc();
        //首页图片网址

         int i = random.nextInt(5);
        String mPicUrl = data.get(position).getImg();

        mHolder.mTitle.setText(title);
        Picasso.with(UiUtils.getContext())
                .load(a[i])
                .into(mHolder.mImage);

        mHolder.mDesc.setText(desc);

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
                    String id = data.get(position).getId();
                    String picurl = data.get(position).getImg();
                    String title = data.get(position).getTitle();
                    Intent intent = new Intent(mActivity, TerrorActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("picurl", picurl);
                    intent.putExtra("title", title);
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
