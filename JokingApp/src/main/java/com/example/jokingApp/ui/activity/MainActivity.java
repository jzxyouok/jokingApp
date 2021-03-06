package com.example.jokingApp.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.example.jokingApp.utils.UiUtils;
import com.example.jokingApp.utils.event.DayModelEvent;
import com.example.jokingApp.utils.event.FantasticEvent;
import com.example.jokingApp.utils.event.JokeEvent;
import com.example.jokingApp.utils.event.JoyEvent;
import com.example.jokingApp.utils.event.NightModelEvent;
import com.example.jokingApp.utils.event.TerrorEvent;
import com.example.jokingApp.utils.event.VideoEvent;
import com.example.jokingApp.utils.event.WeiXinEvent;
import com.example.jokingApp.utils.helper.NetWorkHelper;
import com.example.jokingApp.utils.helper.ToastHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseSwipeBackActivity implements View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tabs)
    TabLayout mTabLayout;
    @InjectView(R.id.vp)
    ViewPager mViewpager;
    @InjectView(R.id.id_nv_menu)
    NavigationView mNavigationView;
    @InjectView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    @Inject
    NetWorkHelper mNetWorkHelper;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    RxBus mRxBus;

    @InjectView(R.id.appbar)
    AppBarLayout mAppbar;


    @InjectView(R.id.fab)
    FloatingActionButton mFab;

    public AppBarLayout getAppbar() {
        return mAppbar;
    }

    private String[] mStringArray;

    private boolean isNightModel = true;


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
        ButterKnife.inject(this);

        //初始化toobar
        initToobar();
        initNavigationView();
        //初始化Tablayout
        iniTablayout();
        //设置viewpager
        initViewPager();
        //判断当前网络状态 提醒用户
        initNetWork();
        //初始化事件分发  这里主要用来实现模式切换
        initRxBus();

        initAppBar();

    }

    private void initAppBar() {
        mAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset!=0){
                    mFab.hide();
                }else{
                    mFab.show();
                }
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String title = getSupportActionBar().getTitle().toString();
                switch (title) {
                    case "笑话":
                        mRxBus.send(new JokeEvent());
                        break;
                    case "新闻":
                        mRxBus.send(new FantasticEvent());
                        break;
                    case "视频":
                        mRxBus.send(new VideoEvent());
                        break;
                    case "趣图":
                        mRxBus.send(new JoyEvent());
                        break;
                    case "热点":
                        mRxBus.send(new WeiXinEvent());
                        break;
                    case "鬼故事":
                        mRxBus.send(new TerrorEvent());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 切换模式  白天/夜间
     */
    private void initRxBus() {
        final Subscription subscribe = mRxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof NightModelEvent) {
                    mNavigationView.setBackgroundColor(UiUtils.getResource().getColor(R.color.menu_item_color));
                    mToolbar.setBackgroundColor(UiUtils.getResource().getColor(R.color.toobar_back_night));
                    mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
                    mTabLayout.setBackgroundColor(UiUtils.getResource().getColor(R.color.toobar_back_night));
                } else if (o instanceof DayModelEvent) {
                    mNavigationView.setBackgroundColor(Color.WHITE);
                    mToolbar.setBackgroundColor(UiUtils.getResource().getColor(R.color.colorPrimary));
                    mTabLayout.setSelectedTabIndicatorColor(UiUtils.getResource().getColor(R.color.indicatorcolor));
                    mTabLayout.setBackgroundColor(UiUtils.getResource().getColor(R.color.colorPrimary));
                }
            }
        });
        addSubscription(subscribe); //退出的时候销毁
    }


    /**
     * 判断当前的网络状态
     */
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
                        mToolbar.setTitle(mStringArray[0]);
                        break;
                    case 1:
                        mToolbar.setTitle(mStringArray[1]);
                        break;
                    case 2:
                        mToolbar.setTitle(mStringArray[2]);
                        break;
                    case 3:
                        mToolbar.setTitle(mStringArray[3]);
                        break;
                    case 4:
                        mToolbar.setTitle(mStringArray[4]);
                        break;
                    case 5:
                        mToolbar.setTitle(mStringArray[5]);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initToobar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.img_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("笑话");
    }

    private void iniTablayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[4]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[5]));
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
        //头布局
        View headerView = mNavigationView.getHeaderView(0);
        TextView mTextView = (TextView) headerView.findViewById(R.id.login);
        ImageView mImageView = (ImageView) headerView.findViewById(R.id.image);
        mTextView.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        final MenuItem item = mNavigationView.getMenu().getItem(2);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                switch (itemId) {
                    case R.id.nav_home:
                        break;
                    case R.id.theme:
                        changeTheme(item);
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

    private void changeTheme(MenuItem item) {
        if (!isNightModel) {
            item.setTitle("夜间模式");
            mRxBus.send(new DayModelEvent());
        } else {
            item.setTitle("日间模式");
            mRxBus.send(new NightModelEvent());
        }
        isNightModel = !isNightModel;
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
