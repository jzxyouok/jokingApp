package com.example.jokingApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.jokingApp.R;
import com.example.jokingApp.utils.PrefUtils;

/**
 * Created by idea-pc on 2016/3/30.
 */
public class SplashActivity extends BaseActivity {
    RelativeLayout rlRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        startAnim();
    }

    /**
     * 动画效果
     */
    private void startAnim() {

        // 动画集合
        AnimationSet set = new AnimationSet(false);

        // 旋转动画
//        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation
//                .RELATIVE_TO_SELF, 0.5f);
//        rotate.setDuration(1000);// 动画时间
//        rotate.setFillAfter(true);// 保持动画状态

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(1, 1.35f, 0, 1.35f, Animation.RELATIVE_TO_SELF, 0.5f, Animation
                .RELATIVE_TO_SELF, 0.5f);
      scale.setDuration(1000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0.5f, 1.2f);
        alpha.setDuration(2000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态

///       set.addAnimation(rotate);
        set.addAnimation(scale);
       // set.addAnimation(alpha);

        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }
        });

        rlRoot.startAnimation(set);
    }
    /**
     * 跳转下一个页面
     */
    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",
                false);

        if (!userGuide) {
            // 跳转到新手引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }

        finish();
    }
}