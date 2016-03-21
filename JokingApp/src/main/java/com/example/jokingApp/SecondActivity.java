package com.example.jokingApp;

import android.widget.TextView;

import com.example.jokingApp.BaseActivity;

/**
 * Created by idea-pc on 2016/3/20.
 */
public class SecondActivity extends BaseActivity {
    @Override
    protected void init() {
        super.init();
        TextView textView = new TextView(this);
        textView.setText("SecondActivity");
    }
}
