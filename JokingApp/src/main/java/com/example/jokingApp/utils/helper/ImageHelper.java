package com.example.jokingApp.utils.helper;

import android.media.Image;
import android.widget.ImageView;

import com.example.jokingApp.utils.UiUtils;
import com.squareup.picasso.Picasso;

/**
 * 封装图片加载框架
 * Created by idea-pc on 2016/4/22.
 */
public class ImageHelper {

    private Picasso mPicasso;

    public ImageHelper( ) {
      mPicasso = Picasso.with(UiUtils.getContext());
    }

    public void showImage(String url, ImageView Image) {
        mPicasso.load(url)
                .into(Image);
    }
}
