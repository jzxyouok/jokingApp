package com.example.jokingApp.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.jokingApp.R;
import com.example.jokingApp.presenter.LoginPresenter;
import com.example.jokingApp.ui.view.LoginView;
import com.example.jokingApp.utils.helper.ToastHelper;
import com.example.jokingApp.utils.UiUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by idea-pc on 2016/4/11.
 */
public class LoginActivity extends BaseSwipeBackActivity implements LoginView {
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.userIcon)
    ImageView mUserIcon;
    @InjectView(R.id.login_username_edit)
    EditText mUser;
    @InjectView(R.id.login_password_edit)
    EditText mPass;
    @InjectView(R.id.login_login_btn)
    Button mLogin;
    @InjectView(R.id.login_sign_up_txt)
    TextView mRegister;


    @Inject
    LoginPresenter mLoginPresenter;
    @Inject
    ToastHelper mToastHelper;
    @InjectView(R.id.root)
    LinearLayout mRoot;

    private MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        mLoginPresenter.attachView(this);

        initToolBar(mToolbar);
        setTitle("登录");
        dialog = new MaterialDialog.Builder(this).title("提示").content("登录中").progress(true, 0).build();

        //键盘弹出会挡住登录按钮  因此应该让按钮向上滚动
        controlKeyboardLayout(mRoot, mRegister);

    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }


    /**
     * 登录 注册按钮点击事件
     *
     * @param view
     */
    @OnClick({R.id.login_login_btn, R.id.login_sign_up_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login_btn:
                if (mUser.getText().length() == 0) {
                    mUser.setError(getString(R.string.login_error_empty_user));
                    mUser.requestFocus();
                } else if (mPass.getText().length() == 0) {
                    mPass.setError(getString(R.string.login_error_empty_pass));
                    mPass.requestFocus();
                } else {
                    doLogin();
                }
                break;
            case R.id.login_sign_up_txt:
                mToastHelper.showToast("该功能暂时没有实现");
                break;
        }
    }

    /**
     * 登录界面的操作
     */
    private void doLogin() {
        //这里直接finish掉
        String user = mUser.getText().toString().trim();
        String password = mPass.getText().toString().trim();
        //  mLoginPresenter.login(user, password);
        showLoading();
        mToastHelper.showToast("登录成功,快体验功能吧");
    }

    /**
     * 应该交给presenter调用
     * 正在加载
     */
    @Override
    public void showLoading() {
        if (!isFinishing() && !dialog.isShowing()) {
            dialog.show();
            UiUtils.postDelay(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
    }

    /**
     * 加载完成
     */
    @Override
    public void hideLoading() {
        if (!isFinishing() && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 登录成功
     * 这里我直接finish掉
     */
    @Override
    public void loginSuccess() {
        UiUtils.postDelay(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
        mToastHelper.showToast("登录成功");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    //点击空白处 隐藏输入法
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 键盘弹出挡住了 view
     * 因此view应该向上滚动
     * @param root  根布局
     * @param scrollToView   需要向上滚动的view
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }

}



