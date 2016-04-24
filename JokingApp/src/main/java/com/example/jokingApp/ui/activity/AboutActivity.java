package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.jokingApp.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/4/14.
 */
public class AboutActivity extends BaseSwipeBackActivity {


    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_about);
        ButterKnife.inject(this);
        initToolBar(mToolbar);

        mToolbar.setTitle("关于");
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
