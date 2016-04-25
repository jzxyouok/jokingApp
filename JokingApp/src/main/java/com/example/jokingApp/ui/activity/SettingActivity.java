package com.example.jokingApp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.example.jokingApp.R;
import com.example.jokingApp.ui.fragment.MySettingFragmetn;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by idea-pc on 2016/4/14.
 */
public class SettingActivity extends BaseSwipeBackActivity {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void initInjector() {

    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.base_content_toolbar_layout);
        ButterKnife.inject(this);
        initToolBar(mToolbar);
        setTitle("设置");
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new MySettingFragmetn()).commit();

    }

    /**
     * 重启一个activity
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
