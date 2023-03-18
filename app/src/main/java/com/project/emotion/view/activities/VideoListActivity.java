package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emotion.R;
import com.project.emotion.adapter.LuntanAdapter;
import com.project.emotion.adapter.VideoAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.VideoBean;
import com.project.emotion.inter.OnVideoListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/20 17:50
 */
public class VideoListActivity extends BaseActivity implements OnVideoListener {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_video)
    RecyclerView rlVideo;

    private VideoAdapter videoAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.video_list;
    }

    @Override
    protected void init() {
        List<VideoBean> videoBeanList = new ArrayList<>();
        videoBeanList.add(new VideoBean("【奥数魔刃】拼装高达 快节奏 放松解压 极度舒适 - 1.【奥数魔刃】拼装高达 快节奏 放松解压 极度舒适(Av71654535,P1)","one"));
        videoBeanList.add(new VideoBean("【沉浸式洗车】旧车先不要卖给二手车贩子！先去洗一洗！ - 1.2(Av722066673,P1)","two"));
        videoBeanList.add(new VideoBean("【团子】3分钟100个触发音 - 1.发布版(Av721476594,P1)","three"));
        videoBeanList.add(new VideoBean("【优质助眠】超刺激的玻璃触发音，针对免疫 - 1.【优质助眠】超刺激的玻璃触发音，针对免疫(Av543208140,P1)","four"));
        videoBeanList.add(new VideoBean("巴克球解压玩具创意玩法极度舒适 - 1.巴克球解压玩具创意玩法极度舒适(Av293586563,P1)","five"));
        videoBeanList.add(new VideoBean("解压舒适 让耳朵放松的太空沙 - 1.22(Av679527607,P1)","six"));
        videoBeanList.add(new VideoBean("清洁小哥洗最脏的地毯，干完这单就辞职，这真是太难为人了 - 1.清洁小哥洗最脏的地毯，干完这单就辞职，这真是太难为人了(Av551528671,P1)","seven"));
        videoBeanList.add(new VideoBean("全网最解压视频没有之一 - 1.1644983753284.mp4(Av551571924,P1)","eight"));
        videoBeanList.add(new VideoBean("世界上最令人舒服满意的视频 2021【第2期】 - 1.0305(Av331902894,P1)","nine"));
        videoBeanList.add(new VideoBean("世界上最让强迫症满意的解压视频，入睡用 - 1.序列 01(Av97978611,P1)","ten"));
        videoBeanList.add(new VideoBean("无聊，玩一玩多米若骨牌，强迫症表示很喜欢！ - 1.THE AMAZING TRIPLE SPIRAL (15,000 DOMI(Av27917772,P1)","ele"));

        videoAdapter = new VideoAdapter(this);
        videoAdapter.setNewData(videoBeanList);
        rlVideo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlVideo.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        videoAdapter.bindToRecyclerView(rlVideo);
        videoAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void onClick(VideoBean videoBean) {
        Intent intent=new Intent(VideoListActivity.this,VideoActivity.class);
        intent.putExtra("detail",videoBean.getUrl());
        startActivity(intent);
    }
}
