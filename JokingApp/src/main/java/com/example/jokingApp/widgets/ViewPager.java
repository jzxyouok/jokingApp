package com.example.jokingApp.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 解决iVewPager滑动冲突的类
 * Created by idea-pc on 2016/3/22.
 */
public class ViewPager extends android.support.v4.view.ViewPager {
    public ViewPager(Context context) {
        super(context);
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //父控件 不要拦截我的事件
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
