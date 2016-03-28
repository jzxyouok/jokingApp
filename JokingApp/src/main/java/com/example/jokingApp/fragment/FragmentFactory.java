package com.example.jokingApp.fragment;

import android.app.Activity;

import java.util.HashMap;

/**
 * Created by idea-pc on 2016/3/17.
 */
public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position , Activity activity) {
        BaseFragment fragment = null;
        fragment = mFragments.get(position);
        if (fragment == null) {
            if (position == 0) {
                fragment = new HomeFragment();
            } else if (position == 1) {
                fragment = new JokeFragment(activity);
            } else if (position == 2) {
                fragment = new PartitionFragment();
            } else if (position == 3) {
                fragment = new SubjectFragment();
            } else if (position == 4) {
                fragment = new CategoryFragment();
            } else if (position == 5) {
                fragment = new SettingFragment();
            }
            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }
        return fragment;

    }
}
