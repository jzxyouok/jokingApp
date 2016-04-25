package com.example.jokingApp.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.example.jokingApp.BaseApplication;
import com.example.jokingApp.R;
import com.example.jokingApp.injector.component.DaggerFragmentComponent;
import com.example.jokingApp.injector.moduel.FragmentModule;
import com.example.jokingApp.ui.activity.SettingActivity;
import com.example.jokingApp.utils.FileUtils;
import com.example.jokingApp.utils.PrefUtils;
import com.example.jokingApp.utils.RxBus;
import com.example.jokingApp.utils.event.DayModelEvent;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.utils.event.NightModelEvent;
import com.example.jokingApp.utils.helper.CacheHelper;
import com.example.jokingApp.utils.helper.DataCleanHelper;
import com.example.jokingApp.utils.helper.SettingPrefHelper;
import com.example.jokingApp.widgets.PreferenceFragment;

import javax.inject.Inject;

/**
 * Created by idea-pc on 2016/4/14.
 */
public class MySettingFragmetn extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener  {
    private ListPreference pTextSize;// 字体大小
    private Preference pPicSavePath;// 图片保存路径
    private Preference pClearCache; //清除缓存
    private ListPreference pThreadSort;
    private Preference pNightMode;

    private ListPreference pSwipeBackEdgeMode;// 手势返回方向

    @Inject
    SettingPrefHelper mSettingPrefHelper;
    @Inject
    CacheHelper mCacheHelper;
    @Inject
    RxBus mBus;
    @Inject
    ToastHelper mToastHelper;
    @Inject
    DataCleanHelper mDataCleanHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(((BaseApplication) getActivity().getApplication()).getApplicationComponent())
                .build()
                .inject(this);
        addPreferencesFromResource(R.xml.setting);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //字体大小
        pTextSize = (ListPreference) findPreference("pTextSize");
        pTextSize.setOnPreferenceChangeListener(this);
        setListSetting(Integer.parseInt(prefs.getString("pTextSize", "4")), R.array.txtSizeNum, pTextSize);

        //图片缓存路径
        pPicSavePath = findPreference("pPicSavePath");
        pPicSavePath.setOnPreferenceClickListener(this);
        pPicSavePath.setSummary( FileUtils.getIconDir().getAbsolutePath());

        //清除缓存
        pClearCache = findPreference("pClearCache");
        pClearCache.setOnPreferenceClickListener(this);
        pClearCache.setSummary(mCacheHelper.getCacheSize());

        //手势返回方向
        pSwipeBackEdgeMode = (ListPreference) findPreference("pSwipeBackEdgeMode");
        pSwipeBackEdgeMode.setOnPreferenceChangeListener(this);
        setListSetting(Integer.parseInt(prefs.getString("pSwipeBackEdgeMode", "0")), R.array.swipeBackEdgeMode, pSwipeBackEdgeMode);

        //
        pNightMode  =  findPreference("pNightMode");
        pNightMode.setOnPreferenceChangeListener(this);

    }

    protected void setListSetting(int value, int hintId, ListPreference listPreference) {
        String[] valueTitleArr = getResources().getStringArray(hintId);
        listPreference.setSummary(valueTitleArr[value]);
    }



    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if ("pTextSize".equals(preference.getKey())) {
            setListSetting(Integer.parseInt(newValue.toString()), R.array.txtSizeNum, pTextSize);
        } else if ("pSwipeBackEdgeMode".equals(preference.getKey())) {
            setListSetting(Integer.parseInt(newValue.toString()), R.array.swipeBackEdgeMode, pSwipeBackEdgeMode);
            ((SettingActivity) getActivity()).reload();
        } else if ("pNightMode".equals(preference.getKey())){
            if ("true".equals(newValue.toString())) {
                mBus.send(new NightModelEvent());
            } else if ("false".equals(newValue.toString())){
                mBus.send(new DayModelEvent());
            }
        }
        return true;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
     if ("pClearCache".equals(preference.getKey())) {
            cleanCache();
        }
        return true;
    }

    private void cleanCache() {
        //此处还是有bug  不能够清理缓存 ,缓存清除后 app启动慢,  并且回滚到原来的数据
       // mDataCleanHelper.cleanApplicationCache();
       mToastHelper.showToast("清理成功");
        pClearCache.setSummary("0KB");
    }
}
