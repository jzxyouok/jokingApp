package com.example.jokingApp.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jokingApp.R;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.BitmapHelper;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.view.ViewPager;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/23.
 */
public class ViewPagerHoler extends RecyclerView.ViewHolder {
    private LinearLayout mLinearLayout;
    private ViewPagerAdapter mAdapter;
    private final ViewPager mViewPager;
    private List<String> data;

    public ViewPagerHoler(View parent, final List<String> data) {
        super(parent);
        this.data=data;
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter();
        }
        mViewPager = (ViewPager) itemView.findViewById(R.id.viewpager);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.line);
        //给轮播图设置适配器
        mViewPager.setAdapter(mAdapter);
        //添加点
        initPoint();
        //设置开始的位置
        int centerCount = Integer.MAX_VALUE / 2;
        centerCount = centerCount - centerCount % data.size();
        mViewPager.setCurrentItem(centerCount);// 设置起始的位置

        //给viewpager设置触摸监听  触摸的时候不再轮播
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        runTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:  // 事件的取消
                    case MotionEvent.ACTION_UP:
                        runTask.start();
                        break;
                }
                return  false; // viewPager 触摸事件 返回值要是false
            }
        });
        mViewPager.addOnPageChangeListener(new android.support.v4.view.ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                position = position % data.size();
                selectPoint(position);
                super.onPageSelected(position);
            }
        });
        runTask = new AuToRunTask();
        runTask.start();
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

    //添加点
    private void initPoint() {
        for (int i = 0; i < data.size(); i++) {
            ImageView point = new ImageView(UiUtils.getContext());
            if (i == 0) {
                point.setImageResource(R.drawable.shape_indicator_selector);
            } else {
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

    private AuToRunTask runTask;
    boolean flag;

    public class AuToRunTask implements Runnable {

        @Override
        public void run() {
            if (flag) {
                UiUtils.cancel(this);  // 取消之前
                int currentItem = mViewPager.getCurrentItem();
                currentItem++;
                mViewPager.setCurrentItem(currentItem);
                //  延迟执行当前的任务
                UiUtils.postDelay(this, 2000);// 递归调用
            }
        }

        public void start() {
            if (!flag) {
                UiUtils.cancel(this);  // 取消之前
                flag = true;
                UiUtils.postDelay(this, 2000);// 递归调用
            }
        }

        public void stop() {
            if (flag) {
                flag = false;
                UiUtils.cancel(this);
            }
        }

    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position=position%data.size();
            ImageView iv = new ImageView(UiUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            String uri = GlobalConstant.SERVER_URL + data.get(position);
            BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
            bitmapUtils.display(iv, uri);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}



