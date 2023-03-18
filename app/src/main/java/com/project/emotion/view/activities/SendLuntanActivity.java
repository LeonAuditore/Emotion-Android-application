package com.project.emotion.view.activities;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;

import com.project.emotion.MyApplication;
import com.project.emotion.R;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.EventMessage;
import com.project.emotion.entity.Luntan;
import com.project.emotion.sqlite.LuntanDBUtils;
import com.project.emotion.utils.Utils;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * @author 袁茏天
 * @description:发布帖子
 * @date :2022/2/23 22:48
 */
public class SendLuntanActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.image_head)
    ImageView imageHead;
    @BindView(R.id.rl_head)
    LinearLayout rlHead;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.btn_send)
    AppCompatTextView btnSend;

    String path= "";
    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_luntan;
    }

    @Override
    protected void init() {
        tvTitle.setText("发布动态");
    }


    @OnClick({R.id.rl_back, R.id.rl_head, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_head:
                PictureSelector
                        .create(SendLuntanActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
            case R.id.btn_send:
                String content = tvName.getText().toString();
                if (TextUtils.isEmpty(content)){
                    showToast("请输入动态...");
                    return;
                }else {
                    Luntan luntan = new Luntan();
                    luntan.setHead_url(MyApplication.getInstance().userBean.getHead_url());
                    luntan.setUsername(MyApplication.getInstance().userBean.getName());
                    luntan.setUser_id(MyApplication.getInstance().userBean.getUser_id());
                    luntan.setPic(path);
                    luntan.setZan("0");
                    luntan.setContent(content);
                    luntan.setTime(Utils.getCurrentTime());
                    LuntanDBUtils.getInstance(getApplicationContext()).insert(luntan);
                    showToast("发布成功");
                    EventBus.getDefault().post(new EventMessage(EventMessage.REFRESH));
                    finish();
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
