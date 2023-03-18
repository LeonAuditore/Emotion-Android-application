package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/2/15 21:05
 */
public class DiaryDetailActivit extends BaseActivity {


    Diary diary = null;
    String xinqing = "开心";
    String path = "";
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_save)
    ImageView tvSave;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.btn_camera)
    ImageView btnCamera;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.rl_change_pwd)
    LinearLayout rlChangePwd;
    @BindView(R.id.et_content)
    EditText etContent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_diary_detail;
    }

    @Override
    protected void init() {
        tvTitle.setText("日记详情");
        tvRight.setVisibility(View.VISIBLE);
        tvSave.setVisibility(View.VISIBLE);
        diary = (Diary) getIntent().getSerializableExtra("detail");
        etContent.setText(diary.getContent());
        etTitle.setText(diary.getTitle());
        if (!TextUtils.isEmpty(diary.getImg())) {
            Glide.with(this).load(diary.getImg()).into(btnCamera);
        }

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


    @OnClick({R.id.rl_back, R.id.tv_save, R.id.tv_right, R.id.btn_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_save:
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    showToast("输入信息不完整");
                } else {
                    isPublicSend(title, content);
                }
                break;
            case R.id.tv_right:
                DiaryDbUtils.getInstance(getApplicationContext()).delete(getApplicationContext(), diary.getId() + "");
                showToast("删除成功");
                finish();
                break;
            case R.id.btn_camera:
                PictureSelector
                        .create(DiaryDetailActivit.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
        }
    }

    private void isPublicSend(String title, String content) {
        MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
                .setTitle("提示")
                .setMsg("是否日记内容？")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        diary.setContent(content);
                        diary.setTitle(title);
                        diary.setXinqing(xinqing);
                        diary.setWeather("");
                        diary.setUser_id(MyApplication.getInstance().userBean.getUser_id());
                        diary.setImg(path);
                        diary.setIsPublic("true");
                        diary.setTime(Utils.getCurrentTime());
                        DiaryDbUtils.getInstance(getApplicationContext()).change(getApplicationContext(), diary);
                        showToast("修改成功");
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
