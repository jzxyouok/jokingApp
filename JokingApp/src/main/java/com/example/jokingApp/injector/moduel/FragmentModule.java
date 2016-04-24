package com.example.jokingApp.injector.moduel;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.ListAdapter;

import com.example.jokingApp.adapter.JokeAdapter;
import com.example.jokingApp.injector.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by idea-pc on 2016/4/10.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

     @PerFragment
     @Provides
     Activity provideActivity() {
        return mFragment.getActivity();
    }

}

