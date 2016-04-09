package com.example.jokingApp.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.customView.DividerItemDecoration;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 收藏界面
 * Created by idea-pc on 2016/4/6.
 */
public class CollectActivity extends BaseActivity {
    @InjectView(R.id.list)
    RecyclerView mList;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    private JokeAdapter mJokeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("收藏");


        //从服务器中拿到数据
        DbUtils db = DbUtils.create(this);
        List<JokeInfo.JokeBean> list = null;
        try {
            list = db.findAll(JokeInfo.JokeBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        mJokeAdapter = new JokeAdapter(list, this,false);

        //设置recylerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mList.setHasFixedSize(true);
        mList.setAdapter(mJokeAdapter);
    }
}
