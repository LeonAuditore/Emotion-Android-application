package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.sqlite.SqliteDBUtils;
import com.project.emotion.widget.CircleImageView;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 修改用户信息
 */
public class ChangeUserInfoActivity extends AppCompatActivity {


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.image_head)
    CircleImageView imageHead;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_id_card)
    EditText tvIdCard;
    @BindView(R.id.tv_mobile)
    EditText tvMobile;
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.tv_change)
    TextView tvChange;

    String path= "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_userinfo);

        ButterKnife.bind(this);
        tvTitle.setText("修改用户信息");
        if (MyApplication.getInstance().userBean != null){
            tvName.setText(MyApplication.getInstance().userBean.getName());
            tvIdCard.setText(MyApplication.getInstance().userBean.getStudent_num());
            tvMobile.setText(MyApplication.getInstance().userBean.getMobile());
            tvPassword.setText(MyApplication.getInstance().userBean.getPassword());
            if (!TextUtils.isEmpty(MyApplication.getInstance().userBean.getHead_url())){
                path = MyApplication.getInstance().userBean.getHead_url();
                Glide.with(this).load(MyApplication.getInstance().userBean.getHead_url()).into(imageHead);
            }
        }

    }



    @OnClick({R.id.rl_back, R.id.rl_head, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_head:
                PictureSelector
                        .create(ChangeUserInfoActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
            case R.id.tv_change:
                String name = tvName.getText().toString();
                String idcard = tvIdCard.getText().toString();
                String mobile = tvMobile.getText().toString();
                String password = tvPassword.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(idcard) || TextUtils.isEmpty(mobile) ||TextUtils.isEmpty(password) ){
                    Toast.makeText(getApplicationContext(),"请检查输入信息是否完整",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    MyApplication.getInstance().userBean.setHead_url(path);
                    MyApplication.getInstance().userBean.setMobile(mobile);
                    MyApplication.getInstance().userBean.setName(name);
                    MyApplication.getInstance().userBean.setPassword(password);
                    MyApplication.getInstance().userBean.setStudent_num(idcard);
                    SqliteDBUtils.getInstance(getApplicationContext()).change(getApplicationContext(),MyApplication.getInstance().userBean);
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                }
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
                path = pictureBean.getPath();
            }
        }
    }
}
