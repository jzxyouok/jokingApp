package com.example.jokingApp.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 这个类处理swipebacklayout
 * 设置只有当 在屏幕左边的时候  事件交给swipebacklayout出来
 * Created by idea-pc on 2016/4/7.
 */
public class ScrollView extends NestedScrollView {
    int startX;
    int startY;

    public ScrollView(Context context) {
        super(context);
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            startX = (int) ev.getRawX();
            if (startX < 35) {
                //在屏幕左边的时候  交给父控件出来
                getParent().requestDisallowInterceptTouchEvent(false);
                return super.dispatchTouchEvent(ev);
            } else {
                //没有在屏幕左边的时候   不要移动
                getParent().requestDisallowInterceptTouchEvent(true);// 不要拦截, 自己出来
                return super.dispatchTouchEvent(ev);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
