package com.example.jokingApp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jokingApp.db.JokeEntity;
import com.example.jokingApp.db.JokeEntityDao;
import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.utils.helper.DbHelper;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.helper.ImageHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.dao.query.Query;

/**
 * 点击笑话 进入后的详情页
 * Created by idea-pc on 2016/3/28.
 */
public class DetailActivity extends BaseSwipeBackActivity {
    @InjectView(R.id.ivImage)
    ImageView mIvImage;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;
    @InjectView(R.id.text)
    TextView mText;
    @InjectView(R.id.coor)
    CoordinatorLayout mCoor;

    @Inject
    DbHelper mDbHelper;
    @Inject
    ImageHelper mImageHelper;
    private JokeInfo.JokeBean mData;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

     protected void initView() {
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //拿到传入的数据
        mData = (JokeInfo.JokeBean) getIntent().getParcelableExtra("data");
        String des = mData.getDes();
        //设置text
        mText.setText(des);
        //设置图片
        String imageurl = GlobalConstant.SERVER_URL + mData.getImageurl();

         mImageHelper.showImage(imageurl,mIvImage);

        //设置toolbar
        String name = mData.getName();
        mCollapsingToolbar.setTitle(name);



     }

    @Override
    public void initInjector() {
            mActivityComponent.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toobar的 显示布局
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //拿到表
        JokeEntityDao jokeEntityDao = mDbHelper.getJokeEntityDao();
        Query<JokeEntity> build = jokeEntityDao.queryBuilder().where(JokeEntityDao.Properties.Id.eq(mData.getId()))
                .build();
        // Entity or null if no matching entity was found 查询结果可以为null
        // DaoException if the result is not unique
        if(build!=null){
            JokeEntity unique = build.unique();
            if (unique!=null){
                mId = unique.getId();
            }
        }

        int id = item.getItemId();
        if (id == R.id.action_collect) {
                //如果数据库中没有该数据  将数据放入数据库中
                if (mId== null) {
                    JokeEntity jokeEntity = new JokeEntity();
                    jokeEntity.setUrl(mData.getUrl());
                    jokeEntity.setDes(mData.getDes());
                    jokeEntity.setImageurl(mData.getImageurl());
                    jokeEntity.setName(mData.getName());
                    jokeEntity.setId(mData.getId());
                    jokeEntityDao.insert(jokeEntity);
                    Snackbar.make(mCoor, "收藏成功", Snackbar.LENGTH_LONG).show();
                } else {
                    //数据库中没有数据  提示已经添加过了
                    Snackbar.make(mCoor, "已经添加过了", Snackbar.LENGTH_LONG).show();
                }
            }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
