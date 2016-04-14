package com.example.jokingApp.injector.component;

import com.example.jokingApp.fragment.BaseFragment;
import com.example.jokingApp.fragment.ImageFragment;
import com.example.jokingApp.fragment.JokeFragment;
import com.example.jokingApp.fragment.PartitionFragment;
import com.example.jokingApp.fragment.SettingFragment;
import com.example.jokingApp.injector.PerFragment;
import com.example.jokingApp.injector.moduel.FragmentModule;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/10.
 */
@PerFragment
@Component(modules = FragmentModule.class,dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    void inject(JokeFragment jokeFragment);
    void inject(SettingFragment settingFragment);
    void inject(PartitionFragment partitionFragment);
    void inject(ImageFragment imageFragment);
    void inject(BaseFragment baseFragment);
}

