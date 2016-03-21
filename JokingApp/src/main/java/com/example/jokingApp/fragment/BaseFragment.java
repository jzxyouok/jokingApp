package com.example.jokingApp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.utils.ViewUtils;
import com.example.jokingApp.view.Loadingpager;

import java.util.List;

/**
 * Created by idea-pc on 2016/3/17.
 */
public abstract class BaseFragment extends Fragment {
    private Loadingpager mLoadingpager;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        if (mLoadingpager == null) {
            mLoadingpager = new Loadingpager(getContext()) {
                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                public LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        }else {
            ViewUtils.removeParent(mLoadingpager);
        }

        return mLoadingpager;
    }
    public void show(){
        if(mLoadingpager!=null){
            mLoadingpager.show();
        }
    }

    public abstract View createSuccessView();

    public abstract Loadingpager.LoadResult load();

    /**校验数据 */
    public Loadingpager.LoadResult checkData(List datas) {
        if(datas==null){
            return Loadingpager.LoadResult.error;//  请求服务器失败
        }else{
            if(datas.size()==0){
                return Loadingpager.LoadResult.empty;
            }else{
                return Loadingpager.LoadResult.success;
            }
        }

    }
}
