package com.example.jokingApp.injector.moduel;

import android.app.Service;

import com.example.jokingApp.injector.PerService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by idea-pc on 2016/4/13.
 */
@Module
public class ServiceModule {
    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    public Service provideContext() {
        return mService;
    }
}

