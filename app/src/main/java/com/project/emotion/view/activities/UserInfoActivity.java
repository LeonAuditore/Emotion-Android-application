package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.widget.CircleImageView;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * @author 袁茏天
 * @description: 用户详情
 */
public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id_card)
    TextView tvIdCard;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.image_head)
    CircleImageView imageHead;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void init() {
        tvTitle.setText("用户信息");
        if (MyApplication.getInstance().userBean != null) {
            if (TextUtils.isEmpty(MyApplication.getInstance().userBean.head_url)) {
                Glide.with(this).load(R.mipmap.head_default).into(imageHead);
            }else {
                Glide.with(this).load(MyApplication.getInstance().userBean.head_url).into(imageHead);
            }

            tvName.setText(MyApplication.getInstance().userBean.name);
            tvIdCard.setText(MyApplication.getInstance().userBean.student_num);
            tvMobile.setText(MyApplication.getInstance().userBean.mobile);
            tvPassword.setText(MyApplication.getInstance().userBean.password);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_back, R.id.rl_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_head:
                PictureSelector
                        .create(UserInfoActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                Glide.with(this).load(pictureBean.getPath()).into(imageHead);
                MyApplication.getInstance().userBean.setHead_url(pictureBean.getPath());

            }
        }
    }
}
