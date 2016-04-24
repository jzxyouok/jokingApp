package com.example.jokingApp.injector.component;

import com.example.jokingApp.injector.PerActivity;
import com.example.jokingApp.injector.moduel.ActivityModule;
import com.example.jokingApp.ui.activity.BaseActivity;
import com.example.jokingApp.ui.activity.BaseSwipeBackActivity;
import com.example.jokingApp.ui.activity.CollectActivity;
import com.example.jokingApp.ui.activity.DetailActivity;
import com.example.jokingApp.ui.activity.LoginActivity;
import com.example.jokingApp.ui.activity.MainActivity;
import com.example.jokingApp.ui.activity.VideoActivity;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/11.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    void inject(LoginActivity loginActivity);

    void inject(CollectActivity collectActivity);

    void inject(BaseSwipeBackActivity mBaseSwipeBackActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity mDetailActivity);

    void inject(VideoActivity mVideoActivity);

}
