package com.project.emotion.view.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.hb.dialog.myDialog.MyAlertDialog;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.Diary;
import com.project.emotion.sqlite.DiaryDbUtils;
import com.project.emotion.utils.Utils;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/2/15 19:59
 */
public class AddRijiActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.btn_camera)
    ImageView btnCamera;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.rl_change_pwd)
    LinearLayout rlChangePwd;

    String path = "";
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;

    String xinqing = "开心";
    @Override
    protected int getLayoutId() {
        return R.layout.actiovity_add_riji;
    }

    @Override
    protected void init() {
        tvTitle.setText("添加日记");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setBackgroundResource(R.mipmap.save);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] xinqin = getResources().getStringArray(R.array.xinqing);
                xinqing = xinqin[pos];

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }



    @OnClick({R.id.rl_back, R.id.tv_right, R.id.btn_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_right:
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)){
                    showToast("输入信息不完整");
                }else {
                    isPublicSend(title,content);
                }
                break;
            case R.id.btn_camera:
                PictureSelector
                        .create(AddRijiActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
        }
    }

    private void isPublicSend(String title,String content){
        MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
                .setTitle("提示")
                .setMsg("日记内容是否发布？")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Diary diary = new Diary();
                        diary.setContent(content);
                        diary.setTitle(title);
                        diary.setXinqing(xinqing);
                        diary.setWeather("");
                        diary.setUser_id(MyApplication.getInstance().userBean.getUser_id());
                        diary.setImg(path);
                        diary.setIsPublic("true");
                        diary.setTime(Utils.getCurrentTime());
                        DiaryDbUtils.getInstance(getApplicationContext()).saveUser(diary);
                        showToast("保存成功");
                        finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        myAlertDialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                Glide.with(this).load(pictureBean.getPath()).into(btnCamera);
                path = pictureBean.getPath();
            }
        }
    }
}
