package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jokingApp.BaseApplication;
import com.example.jokingApp.injector.component.ActivityComponent;
import com.example.jokingApp.injector.component.DaggerActivityComponent;
import com.example.jokingApp.injector.moduel.ActivityModule;

import java.util.LinkedList;
import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 抽取BaseActivity   管理所有activity 方便退出
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 管理运行的所有的activity
    public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    public static BaseActivity activity;
    protected ActivityComponent mActivityComponent;

    private CompositeSubscription mCompositeSubscription;


    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
                .build();
        init();
        initInjector(); //初始化注入
        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

    public void killAll() {
        // 复制了一份mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void init() {
    }

    /**
     * 注入Injector
     */
    public abstract void initInjector();

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

}
