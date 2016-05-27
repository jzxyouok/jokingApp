package com.example.jokingApp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokingApp.BaseApplication;
import com.example.jokingApp.injector.component.DaggerFragmentComponent;
import com.example.jokingApp.injector.component.FragmentComponent;
import com.example.jokingApp.injector.moduel.FragmentModule;
import com.example.jokingApp.ui.activity.MainActivity;
import com.example.jokingApp.utils.ViewUtils;
import com.example.jokingApp.widgets.LoadingPage;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {
    protected FragmentComponent mFragmentComponent;

    public MainActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       mActivity=  (MainActivity)context;
    }

    public LoadingPage mLoadingPage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mLoadingPage == null) {  // 之前的frameLayout 已经记录了一个爹了  爹是之前的ViewPager
            mLoadingPage = new LoadingPage(mActivity){
                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                public LoadingPage.LoadResult load() {
                    return BaseFragment.this.load();
                }
            };
        }else{
            ViewUtils.removeParent(mLoadingPage);// 移除frameLayout之前的爹
        }
        return mLoadingPage;  //  拿到当前viewPager 添加这个framelayout
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .applicationComponent(((BaseApplication) getActivity().getApplication()).getApplicationComponent())
                .build();
        initInjector();
        getBundle(getArguments());
    }

    /***
     *  创建成功的界面
     * @return
     */
    protected abstract View createSuccessView();
    /**
     * 请求服务器
     * @return
     */
    protected abstract LoadingPage.LoadResult load();


    public void show(){
        if(mLoadingPage !=null){
            mLoadingPage.show();
        }
    }


    /**校验数据 */
    public LoadingPage.LoadResult checkData(List datas) {
        if(datas==null){
            return LoadingPage.LoadResult.error;//  请求服务器失败
        }else{
            if(datas.size()==0){
                return LoadingPage.LoadResult.empty;
            }else{
                return LoadingPage.LoadResult.success;
            }
        }

    }
    //初始化  注入器
    public abstract void initInjector();

    public void getBundle(Bundle bundle){}

}
