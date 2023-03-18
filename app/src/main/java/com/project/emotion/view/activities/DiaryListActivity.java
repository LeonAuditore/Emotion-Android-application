package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.adapter.DiaryAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.Diary;
import com.project.emotion.sqlite.DiaryDbUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 袁茏天
 * @description:日记
 * @date :2022/3/17 22:52
 */
public class DiaryListActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.lv_data)
    ListView lvData;

    DiaryAdapter diaryAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_diary_list;
    }

    @Override
    protected void init() {
        tvTitle.setText("日记列表");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setBackgroundResource(R.mipmap.add);
        diaryAdapter = new DiaryAdapter(DiaryListActivity.this, DiaryDbUtils.getInstance(getApplicationContext()).findAllByUserId(MyApplication.getInstance().userBean.getUser_id()));
        lvData.setAdapter(diaryAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (lvData != null){
            diaryAdapter = new DiaryAdapter(DiaryListActivity.this, DiaryDbUtils.getInstance(getApplicationContext()).findAllByUserId(MyApplication.getInstance().userBean.getUser_id()));
            lvData.setAdapter(diaryAdapter);
        }
    }

    @OnClick({R.id.rl_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(new Intent(DiaryListActivity.this,AddRijiActivity.class));
                break;
        }
    }
}
