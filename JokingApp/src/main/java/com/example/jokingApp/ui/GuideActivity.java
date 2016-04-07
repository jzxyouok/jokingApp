package com.example.jokingApp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.PrefUtils;
import com.example.jokingApp.utils.UiUtils;

/**
 * 新手引导页  初次进入的时候 显示
 * Created by idea-pc on 2016/3/30.
 */
public class GuideActivity extends Activity {
    private static final int[] mImageIds = new int[] { R.mipmap.guide_1,
            R.mipmap.guide_2, R.mipmap.guide_3 };

    private ViewPager mViewPager;
    private LinearLayout mLinearLayout;// 引导圆点的父控件
    private Button btnStart;// 开始体验
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_point_group);
        btnStart = (Button) findViewById(R.id.btn_start);
        initPoints();
        initViews();
    }

    private void initViews() {
        ViewPagerAdapter  mAdapter = new ViewPagerAdapter();

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                selectPoint(position);
                if (position == mImageIds.length - 1) {// 最后一个页面
                    btnStart.setVisibility(View.VISIBLE);// 显示开始体验的按钮
                } else {
                    btnStart.setVisibility(View.INVISIBLE);
                }
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更新sp, 表示已经展示了新手引导
                PrefUtils.setBoolean(GuideActivity.this,
                        "is_user_guide_showed", true);
                // 跳转主页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void initPoints() {
        for (int i = 0; i <3; i++) {
            ImageView point = new ImageView(UiUtils.getContext());
            if (i == 0) {
                point.setImageResource(R.drawable.shape_indicator_selector);
            } else {
                point.setImageResource(R.drawable.shape_indicator_normal);
                point.setImageResource(R.drawable.shape_indicator_normal);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                    .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = UiUtils.dip2px(6);
            layoutParams.bottomMargin = UiUtils.dip2px(6);
            point.setLayoutParams(layoutParams);
            mLinearLayout.addView(point);
        }
    }
    /**
     * 根据角标设置选中的点
     * @param index
     */
    private void selectPoint(int index) {
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            ImageView point = (ImageView) mLinearLayout.getChildAt(i);
            if (index == i) {
                point.setImageResource(R.drawable.shape_indicator_selector);
            } else {
                point.setImageResource(R.drawable.shape_indicator_normal);
            }
        }
    }
    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return  mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setImageResource(mImageIds[position]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
