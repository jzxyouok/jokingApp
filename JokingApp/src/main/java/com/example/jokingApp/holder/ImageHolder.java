package com.example.jokingApp.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.BitmapHelper;
import com.example.jokingApp.utils.UiUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class ImageHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.image)
    public ImageView mImage;
    @InjectView(R.id.text)
    public TextView mText;
    private List<String> data;

    public ImageHolder(View itemView, List<String> data) {
        super(itemView);
        this.data = data;
        ButterKnife.inject(this, itemView);
    }

    public void initData(int position) {
        mImage.setScaleType(ImageView.ScaleType.FIT_XY);
        BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
        String uri = GlobalConstant.SERVER_URL + data.get(position);
        bitmapUtils.display(mImage, uri);

    }
}
