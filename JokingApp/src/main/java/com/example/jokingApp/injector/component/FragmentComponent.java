package com.example.jokingApp.injector.component;

import com.example.jokingApp.ui.fragment.BaseFragment;
import com.example.jokingApp.ui.fragment.FantasticFragment;
import com.example.jokingApp.ui.fragment.ImageFragment;
import com.example.jokingApp.ui.fragment.JokeFragment;
import com.example.jokingApp.ui.fragment.MySettingFragmetn;
import com.example.jokingApp.ui.fragment.PartitionFragment;
import com.example.jokingApp.injector.PerFragment;
import com.example.jokingApp.injector.moduel.FragmentModule;
import com.example.jokingApp.ui.fragment.VideoFragment;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/10.
 */
@PerFragment
@Component(modules = FragmentModule.class,dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);

    void inject(JokeFragment jokeFragment);


    void inject(PartitionFragment partitionFragment);

    void inject(ImageFragment imageFragment);

    void inject(MySettingFragmetn mySettingFragment);

    void inject(VideoFragment mVideoFragment);

    void inject(FantasticFragment mFantasticFragment);
}

