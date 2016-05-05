package com.example.jokingApp.presenter;

import com.example.jokingApp.api.LoginApi;
import com.example.jokingApp.bean.LoginData;
import com.example.jokingApp.bean.LoginResult;
import com.example.jokingApp.bean.UserData;
import com.example.jokingApp.bean.UserResult;
import com.example.jokingApp.db.User;
import com.example.jokingApp.global.GlobalConstant;
import com.example.jokingApp.ui.view.LoginView;
import com.example.jokingApp.utils.helper.SecurityHelper;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.utils.helper.UserStorage;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by idea-pc on 2016/4/11.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    ToastHelper mToastHelper;
    @Inject
    LoginApi mLoginApi;
    @Inject
    SecurityHelper mSecurityHelper;
    @Inject
    UserStorage mUserStorage;
    private Subscription mSubscription;
    private User user = new User();

    @Inject
    public LoginPresenter() {
    }

    //登录操作
    ///请求服务器
    public void login(final String userName, final String passWord) {

        mSubscription = mLoginApi.login(userName, mSecurityHelper.getMD5(passWord)).flatMap(new Func1<LoginData,
                Observable<UserData>>() {
            @Override
            public Observable<UserData> call(LoginData loginData) {
                if (loginData != null && loginData.is_login == 1) {
                    LoginResult data = loginData.result;
                    //   这里为什么要加URLDecoder.decode(Constants.Cookie, "UTF-8") 没看懂
                    String cookie = "";
                    try {
                        cookie = URLDecoder.decode(GlobalConstant.Cookie, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String uid = cookie.split("\\|")[0];
                    user.setUid(uid);
                    user.setToken(data.token);
                    user.setCookie(cookie);
                    user.setUserName(data.username);
                    return mLoginApi.getUserInfo(uid);
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<UserData>() {
            @Override
            public void call(UserData userData) {
                if (userData != null && userData.result != null) {
                    UserResult data = userData.result;
                    user.setIcon(data.header);
                    user.setThreadUrl(data.bbs_msg_url);
                    user.setPostUrl(data.bbs_post_url);
                    user.setRegisterTime(data.reg_time_str);
                    user.setSchool(data.school);
                    user.setSex(data.gender);
                    user.setLocation(data.location_str);
                    mUserStorage.login(user);
                    insertOrUpdateUser(user);
                    mToastHelper.showToast("登录成功");
//                    mBus.post(new LoginSuccessEvent());
                    view.loginSuccess();
                } else {
                    view.hideLoading();
                    mToastHelper.showToast("登录失败，请检查您的网络");
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                view.hideLoading();
                mToastHelper.showToast("登录失败，请检查您的网络");
            }
        });

        mToastHelper.showToast("登录成功");
    }

    //更新或者插入用户
    private void insertOrUpdateUser(User user) {

    }

    @Override
    public void detachView() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
