package com.example.jokingApp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JoyInfo;
import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/5/8.
 */
public class JoyAdapter extends RecyclerView.Adapter {
    private   Activity mActivity;
    private List<JoyInfo.ShowapiResBodyBean.ContentlistBean> data;

    public JoyAdapter(List<JoyInfo.ShowapiResBodyBean.ContentlistBean> data, Activity mActivity) {
        this.data = data;
        this.mActivity=mActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(R.layout.fragment_item_joy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder mHolder = (ViewHolder) holder;
         String url = data.get(position).getImg();
        String title = data.get(position).getTitle();
         Picasso.with(mActivity)
                 .load(url)
                .config(Bitmap.Config.RGB_565)
                .into(mHolder.mImage);
        mHolder.mText.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.size()==0?0: data.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.image)
        ImageView mImage;
        @InjectView(R.id.text)
        TextView mText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
