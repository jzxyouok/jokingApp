package com.example.jokingApp.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.bean.ImageInfo;
import com.example.jokingApp.adapter.holder.ViewPagerHoler;
import com.example.jokingApp.adapter.holder.textHolder;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.helper.ImageHelper;
import com.example.jokingApp.widgets.swipeback.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 *
 * Created by idea-pc on 2016/3/22.
 */
public class ImageAdapter extends RecyclerView.Adapter {
    private static final int TYPE_VIEWPAGER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_TIPS = 2;
    private final List<String> mViewpager;
    private final List<String> mPicture;
    private ImageHolder mImageHolder;



    public ImageAdapter(ImageInfo data) {
        mViewpager = data.getViewpager();
        mPicture = data.getPicture();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIEWPAGER) {
            View view = getViewHolder(R.layout.fragment_item_home_viewpager, parent, true);
            return new ViewPagerHoler(view,mViewpager);
        } else if (viewType==TYPE_NORMAL){
            View view = getViewHolder(R.layout.item_frgment_home_image, parent, false);
            return new ImageHolder(view, mPicture);
        }else {
            View view =getViewHolder(R.layout.item_fragment_home_tips,parent,true);
            return new textHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType==TYPE_NORMAL){
            position = position - 1;
            mImageHolder = (ImageHolder) holder;
            mImageHolder.initData(position);
        }
    }

    private View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(UiUtils.getContext()).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view
                .getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_VIEWPAGER;
        }if (position>0&&position<=mPicture.size()){
            return TYPE_NORMAL;
        }else {
            return   TYPE_TIPS;
        }
    }
    //+1 是viewPager   最后一个+1是 tips
    @Override
    public int getItemCount() {
        return 1 + mPicture.size()+1;
    }


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
            String url = GlobalConstant.SERVER_URL + data.get(position);
              Picasso.with(UiUtils.getContext())
                   .load(url)
                   .into(mImage);

        }
    }
}
