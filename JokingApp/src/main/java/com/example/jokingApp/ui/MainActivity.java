package com.example.jokingApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jokingApp.R;
import com.example.jokingApp.fragment.BaseFragment;
import com.example.jokingApp.fragment.FragmentFactory;
import com.example.jokingApp.utils.UiUtils;

import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager mViewpager;
    private String[] mStringArray;
    TabLayout mTabLayout;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void init() {
        mStringArray = getResources().getStringArray(R.array.tab_names);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        initToobar();
        initNavigationView();
        //初始化Tablayout
        iniTablayout();
        //设置viewpager
        initViewPager();
    }

    private void initViewPager() {
        mViewpager = (ViewPager) findViewById(R.id.vp);
        final MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mainAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BaseFragment createFragment = FragmentFactory.createFragment(position);
                createFragment.show();//  当切换界面的时候 重新请求服务器
                switch (position) {
                    case 1:
                        mToolbar.setTitle("图片");
                        break;
                    case 2:
                        mToolbar.setTitle("分区");
                        break;
                    case 3:
                        mToolbar.setTitle("设置");
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
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[3]));
    }

    class MainAdapter extends FragmentStatePagerAdapter {
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


    private void initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
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
                                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                            }
                        },500);
                        break;
                    case R.id.theme:
                        Toast.makeText(MainActivity.this, "该功能暂未实现", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_collect:
                        startActivity(new Intent(MainActivity.this,CollectActivity.class));
                        break;
                    case R.id.feedback:
                        Toast.makeText(MainActivity.this, "反馈", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "关于", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
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

    @Override
    public void onBackPressed() {
        //如果 navigationView 打开 点击 返回 应该关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出MyAPP", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }

}
