package com.example.jokingApp.injector.component;

import com.example.jokingApp.injector.PerActivity;
import com.example.jokingApp.injector.moduel.ActivityModule;
import com.example.jokingApp.ui.BaseActivity;
import com.example.jokingApp.ui.CollectActivity;
import com.example.jokingApp.ui.LoginActivity;

import dagger.Component;

/**
 * Created by idea-pc on 2016/4/11.
 */
@PerActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {


    void inject(BaseActivity baseActivity);

    void inject(LoginActivity loginActivity);

    void inject(CollectActivity collectActivity);

}
