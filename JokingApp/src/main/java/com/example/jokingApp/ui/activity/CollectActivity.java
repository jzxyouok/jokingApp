package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jokingApp.db.JokeEntity;
import com.example.jokingApp.db.JokeEntityDao;
import com.example.jokingApp.R;
import com.example.jokingApp.adapter.CollectAdapter;
import com.example.jokingApp.utils.helper.DbHelper;
import com.example.jokingApp.widgets.DividerItemDecoration;

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


    @InjectView(R.id.text)
    TextView mText;
    CollectAdapter mJokeAdapter;
    @Inject
    DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_collect);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        initToolBar(mToolbar);
        getSupportActionBar().setTitle("收藏");

        //在这里 存入数据库的类型 和 从服务器拿到的数据类型冲突 好烦....
        JokeEntityDao jokeEntityDao = mDbHelper.getJokeEntityDao();
        List<JokeEntity> list = jokeEntityDao.loadAll();
        mText.setVisibility(list==null
                 ? View.VISIBLE : View.GONE);


        //设置recylerView
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mList.setHasFixedSize(true);
        mJokeAdapter=new CollectAdapter(list ,this);
        //设置adapter
        mList.setAdapter(mJokeAdapter);
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
