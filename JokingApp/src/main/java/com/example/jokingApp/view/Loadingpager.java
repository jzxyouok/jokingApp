package com.example.jokingApp.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.ThreadManager;

/**
 * Created by idea-pc on 2016/3/16.
 */
public abstract class Loadingpager extends FrameLayout {

    public View loadingView;
    private View errorView;
    private View successView;
    private View emptyView;
    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public int state = STATE_UNKOWN;

    public Loadingpager(Context context) {
        super(context);
        init();
    }

    public Loadingpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Loadingpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化加载页面
     */
    private void init() {
        loadingView = createLoadingView();
        if (loadingView != null) {
            addView(loadingView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                    .MATCH_PARENT));
        }
        errorView = createErrorView();
        if (errorView != null) {
            addView(errorView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                    .MATCH_PARENT));
        }
        emptyView = creatEmptyView();
        if (emptyView != null) {
            addView(emptyView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                    .MATCH_PARENT));
        }
        showPage();

    }


    /**
     * 根据不同的状态显示 加载页面
     */
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (state == STATE_SUCCESS) {
            if (successView == null) {
                successView = createSuccessView();
                this.addView(successView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams
                        .MATCH_PARENT));
            }
            successView.setVisibility(View.VISIBLE);
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    private View createErrorView() {
        View view = View.inflate(UiUtils.getContext(), R.layout.loadpage_error, null);
        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });
        return view;
    }

        public enum LoadResult {
            error(2), empty(3), success(4);

            int value;

            LoadResult(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }

        }

        /**
         * 根据服务器的数据来 切换 状态
         */

    public void show() {
        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
        }
        //根据服务器的数据 进行判断
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(500);
                final LoadResult result = load();
                UiUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            state = result.getValue();
                            showPage(); // 状态改变了,重新判断当前应该显示哪个界面
                        }
                    }
                });
            }
        });
    }

    private View createLoadingView() {
        View view = View.inflate(getContext(), R.layout.loadpage_loading, null);
        return view;
    }

    private View creatEmptyView() {
        View view = View.inflate(getContext(), R.layout.loadpage_empty, null);
        return view;
    }

    public abstract View createSuccessView();

    public abstract LoadResult load();

}
