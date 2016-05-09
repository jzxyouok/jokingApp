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
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.ui.activity.FantasticActivity;
import com.example.jokingApp.ui.activity.MainActivity;
import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class FantasticAdapter extends RecyclerView.Adapter {
    Activity mActivity ;
    private List<FantasticInfo.ShowapiResBodyBean.NewslistBean> data;


    public FantasticAdapter(List<FantasticInfo.ShowapiResBodyBean.NewslistBean> data,Activity mActivity) {
        this.data = data;
        this.mActivity= mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.fragment_item_fantastic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder mHolder = (ViewHolder) holder;
        String title = data.get(position).getTitle();//标题
        String des = data.get(position).getCtime(); //时间
        String picUrl = data.get(position).getPicUrl();//图片网址
        String url =  data.get(position).getUrl();  //奇闻异事的地址

        Picasso.with(UiUtils.getContext()).load(picUrl).resize(70, 70).centerCrop().config(Bitmap.Config.RGB_565)
                .into(mHolder.mImage);
        mHolder.mTitle.setText(title);
        mHolder.mDesc.setText(des);

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
                    int position = ViewHolder.this.getAdapterPosition();
                     String url = data.get(position).getUrl();

                    Intent intent = new Intent(mActivity,FantasticActivity.class);
                    intent.putExtra("url",url);
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
