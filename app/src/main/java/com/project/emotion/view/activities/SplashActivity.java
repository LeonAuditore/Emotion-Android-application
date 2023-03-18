package com.project.emotion.view.activities;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.utils.SPUtil;
import com.project.emotion.widget.CountdownView;


import butterknife.BindView;

/**
 * 文件名：SplashActivity
 * 作  者：
 * 描述：启动页
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_count)
    CountdownView tvCount;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        SPUtil spUtil = new SPUtil(getApplicationContext(),"isLogin");
        boolean isFirstLogin = spUtil.getBoolean("first",false);
        Glide.with(this).load(R.drawable.splash).into(image);

        tvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvCount.timeEnd();//停止计时
                showActivity(SplashActivity.this,LoginActivity.class);
                finish();
            }
        });

        //倒计时按钮
        tvCount.setMaxTime(3)
                .setConcatStr("s秒跳过")
                .setBgStyle(CountdownView.BgStyle.FILL)
                .setBgColor(Color.RED)
                .setBgColor(30)
                .setEndListener(new CountdownView.CountdownEndListener() {
                    @Override
                    public void countdownEnd() {
                        //TODO 倒计时结束监听
                        showActivity(SplashActivity.this,LoginActivity.class);
                        finish();
                    }
                }).timeStart();
    }

}
