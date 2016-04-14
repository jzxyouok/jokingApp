package com.example.jokingApp.injector.moduel;

import android.content.Context;

import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.utils.helper.UserStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by idea-pc on 2016/4/11.
 */
@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context.getApplicationContext();
    }

    @Provides
    @Singleton
    UserStorage provideUserStorage(SettingPrefHelper mSettingPrefHelper, Context mContext) {
        return new UserStorage(mSettingPrefHelper, mContext);
    }

}
