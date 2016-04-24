package com.example.jokingApp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.ui.fragment.BaseFragment;
import com.example.jokingApp.ui.fragment.FragmentFactory;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.helper.NetWorkHelper;

import javax.inject.Inject;

public class MainActivity extends BaseSwipeBackActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager mViewpager;
    private String[] mStringArray;
    TabLayout mTabLayout;
    private Toolbar mToolbar;

    @Inject
    NetWorkHelper mNetWorkHelper;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    RxBus mRxBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void init() {
        mStringArray = getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        //初始化toobar
        initToobar();
        initNavigationView();
        //初始化Tablayout
        iniTablayout();
        //设置viewpager
        initViewPager();
        //判断当前网络状态 提醒用户
        initNetWork();
    }



    private void initNetWork() {
        boolean wiFi = mNetWorkHelper.isWiFi();
        boolean isAvailableNetwork = mNetWorkHelper.isAvailableNetwork();
        if (!isAvailableNetwork) {
            mToastHelper.showToast("当前网络不可用,请稍后再试");
        }
        if (!wiFi) {
            mToastHelper.showToast("没有连接wifi,注意流量哦");
        }
    }

    private void initViewPager() {
        mViewpager = (ViewPager) findViewById(R.id.vp);
        final MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mainAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment createFragment = FragmentFactory.createFragment(position);
                createFragment.show();//  当切换界面的时候 重新请求服务器
                switch (position) {
                    case 0:
                        mToolbar.setTitle("笑话");
                        break;
                    case 1:
                        mToolbar.setTitle("图片");
                        break;
                    case 2:
                        mToolbar.setTitle("视频");
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initToobar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.img_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("笑话");
    }

    private void iniTablayout() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[2]));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
            case R.id.image:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }
    }

    //viewpager的 adapter
    class MainAdapter extends FragmentPagerAdapter {
        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return mStringArray.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStringArray[position];
        }
    }

    /**
     * 初始化 navigationView
     */
    private void initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        //头布局
        View headerView = mNavigationView.getHeaderView(0);
        TextView mTextView = (TextView) headerView.findViewById(R.id.login);
        ImageView mImageView = (ImageView) headerView.findViewById(R.id.image);
        mTextView.setOnClickListener(this);
        mImageView.setOnClickListener(this);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (itemId) {
                    case R.id.nav_home:
                        //这里之所以延迟执行,为了先将navigation隐藏,否则的话 会出现不太明显的卡顿现象,受百度云音乐的启发
                        UiUtils.postDelay(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                            }
                        }, 200);
                        break;
                    case R.id.theme:
                        Toast.makeText(MainActivity.this, "该功能暂未实现", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_collect:
                        startActivity(new Intent(MainActivity.this, CollectActivity.class));
                        break;
                    case R.id.setting:
                        UiUtils.postDelay(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                            }
                        }, 100);
                        break;
                    case R.id.about:
                        UiUtils.postDelay(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                            }
                        }, 100);
                        break;
                    case R.id.exist:
                        killAll();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSwipeBackEnable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


    private long exitTime = 0;


    //返回键处理
    @Override
    public void onBackPressed() {
        //如果 navigationView 打开 点击 返回 应该关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }
}
