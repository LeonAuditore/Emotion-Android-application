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
import com.project.emotion.adapter.Mp3Adapter;
import com.project.emotion.adapter.VideoAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.Mp3Bean;
import com.project.emotion.entity.VideoBean;
import com.project.emotion.inter.OnMp3Listener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Mp3ListActivity extends BaseActivity implements OnMp3Listener {
    @BindView(R.id.rl_back)
    RelativeLayout rlback;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_mp3)
    RecyclerView rlMp3;

    private Mp3Adapter mp3Adapter;
    @Override
    protected  int getLayoutId(){return R.layout.mp3_list;}

    @Override
    protected void init(){
        List<Mp3Bean>mp3BeanList=new ArrayList<>();
        mp3BeanList.add(new Mp3Bean("Daydream - Little Comfort","music01"));
        mp3BeanList.add(new Mp3Bean("DJ Okawari - Flower Dance","music02"));
        mp3BeanList.add(new Mp3Bean("S.E.N.S. - 沉醉于风中","music03"));
        mp3BeanList.add(new Mp3Bean("郭燕 - 天空之城 (钢琴版)","music04"));
        mp3BeanList.add(new Mp3Bean("几米 - 拥有Masbfca (广告配乐完整版)","music05"));
        mp3BeanList.add(new Mp3Bean("木村弓 - いつも何度でも","music06"));
        mp3BeanList.add(new Mp3Bean("木村弓 奥户巴寿 - Always With Me","music07"));

        mp3Adapter = new Mp3Adapter(this);
        mp3Adapter.setNewData(mp3BeanList);
        rlMp3.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rlMp3.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        mp3Adapter.bindToRecyclerView(rlMp3);
        mp3Adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void onClick(Mp3Bean mp3Bean){
        Intent intent=new Intent(Mp3ListActivity.this,Mp3Activity.class);
        intent.putExtra("detail",mp3Bean.getUrl());
        startActivity(intent);
    }
}
