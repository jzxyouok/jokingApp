package com.example.jokingApp.ui;

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

import com.example.jokingApp.R;
import com.example.jokingApp.bean.JokeInfo;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.utils.BitmapHelper;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 点击笑话 进入后的详情页
 * Created by idea-pc on 2016/3/28.
 */
public class DetailActivity extends SwipeBackActivity {
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
    private SwipeBackLayout mSwipeBackLayout;
    private JokeInfo.JokeBean mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
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
        BitmapHelper.getBitmapUtils().display(mIvImage, imageurl);

        //设置 swipebacklayout
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        //设置toolbar
        String name = mData.getName();
        mCollapsingToolbar.setTitle(name);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //toobar的 显示布局
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //这段代码的可阅读性是不是太差了
        //其实可以把代码抽取到另一个方法当中  因为这里只有这一段逻辑 暂时不抽取了
        int id = item.getItemId();
        if (id == R.id.action_collect) {
            DbUtils db = DbUtils.create(this);
            //首先判断数据库中是否已经加入  其实 也可以在进入activity中先判断 , 将收藏图标替换
            try {
                JokeInfo.JokeBean bean = db.findFirst(Selector.from(JokeInfo.JokeBean.class).where("name", "=", mData
                        .getName()));
                //如果数据库中没有该数据  将数据放入数据库中
                if (bean == null) {
                    JokeInfo.JokeBean user = new JokeInfo.JokeBean(); //这里需要注意的是User对象必须有id属性，或者有通过@ID注解的属性
                    user.setUrl(mData.getUrl());
                    user.setDes(mData.getDes());
                    user.setImageurl(mData.getImageurl());
                    user.setName(mData.getName());
                    user.setId(mData.getId());
                    try {
                        //将设置的数据 保存到数据库当中
                        db.save(user);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(mCoor, "收藏成功", Snackbar.LENGTH_LONG).show();
                } else {
                    //数据库中没有数据  提示已经添加过了
                    Snackbar.make(mCoor, "已经添加过了", Snackbar.LENGTH_LONG).show();
                }
            } catch (DbException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
