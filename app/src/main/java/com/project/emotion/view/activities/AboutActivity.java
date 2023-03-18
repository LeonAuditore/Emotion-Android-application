package com.project.emotion.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：AboutActivity
 * 作  者： 袁茏天
 * 日  期：1/24/22 10:35 AM
 * 描述：关于APP
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {
        tvTitle.setText("关于我们");
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
