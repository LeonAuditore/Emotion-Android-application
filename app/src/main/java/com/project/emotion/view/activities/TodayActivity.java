package com.project.emotion.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 袁茏天
 * @description:每日一句
 * @date :2022/3/17 22:12
 */
public class TodayActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_today;
    }

    @Override
    protected void init() {
        tvTitle.setText("每日一句");
        int random=(int)(Math.random()*10);
        tvContent.setText(Utils.getStrList().get(random));
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
