package com.example.jokingApp.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.R;
import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.widgets.DividerItemDecoration;
import com.example.jokingApp.widgets.swipeback.SwipeBackLayout;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 收藏界面
 * Created by idea-pc on 2016/4/6.
 */
public class CollectActivity extends BaseSwipeBackActivity {
    @InjectView(R.id.list)
    RecyclerView mList;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

      private   JokeAdapter mJokeAdapter;
    @InjectView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        initToolBar(mToolbar);
        getSupportActionBar().setTitle("收藏");


        //从服务器中拿到数据
        DbUtils db = DbUtils.create(this);
        List<JokeInfo.JokeBean> list = null;
        try {
            list = db.findAll(JokeInfo.JokeBean.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        mText.setVisibility(list==null
                 ? View.VISIBLE : View.GONE);
        //设置recylerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mList.setHasFixedSize(true);

        //设置adapter
        mJokeAdapter=new JokeAdapter(list,this ,false);
        mJokeAdapter.setIsLoadingMore(false);
        mList.setAdapter(mJokeAdapter);
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }
}
