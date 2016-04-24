package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jokingApp.R;
import com.example.jokingApp.widgets.swipeback.SwipeBackLayout;
import com.example.jokingApp.utils.UiUtils;



/**
 * Created by idea-pc on 2016/3/20.
 */
public class SecondActivity extends BaseSwipeBackActivity {
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mSwipeBackLayout = getSwipeBackLayout();
        int widthInPx = UiUtils.getWidthInPx();
        mSwipeBackLayout.setEdgeSize(0);
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    public void initInjector() {

    }
}
