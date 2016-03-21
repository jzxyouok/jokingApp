package com.example.jokingApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.jokingApp.fragment.FragmentFactory;

public class MainActivity extends BaseActivity {


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ViewPager mViewpager;
    private PagerTabStrip mPagerTabStrip;
    private String[] mStringArray;
    TabLayout  mTabLayout;
    private  CollapsingToolbarLayout mCollapsingToolbarLayout;

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
        iniTablayout();
        //设置viewpager
        mViewpager = (ViewPager) findViewById(R.id.vp);
        mViewpager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void initToobar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.img_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void iniTablayout() {
        mTabLayout = (TabLayout)findViewById(R.id.tabs);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("hah"));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[3]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[4]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mStringArray[5]));
    }

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
                        Toast.makeText(MainActivity.this, "我是主界面", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(MainActivity.this,SecondActivity.class));
                        break;
                    case R.id.nav_friends:
                        Toast.makeText(MainActivity.this, "nav_friends", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_messages:
                        Toast.makeText(MainActivity.this, "nav_messages", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.subitem1:
                        Toast.makeText(MainActivity.this, "subitem1", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.nav_discussion:
                        Toast.makeText(MainActivity.this, "nav_discussion", Toast.LENGTH_LONG).show();
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


}
