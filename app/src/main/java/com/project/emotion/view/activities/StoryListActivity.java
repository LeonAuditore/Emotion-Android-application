package com.project.emotion.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.emotion.R;
import com.project.emotion.adapter.StoryAdapter;
import com.project.emotion.base.BaseActivity;
import com.project.emotion.entity.Story;
import com.project.emotion.inter.OnStoryListener;
import com.project.emotion.utils.Utils;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 袁茏天
 * @description:故事大全
 * @date :2022/3/17 22:29
 */
public class StoryListActivity extends BaseActivity implements OnStoryListener {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_title)
    TextView tvRightTitle;
    @BindView(R.id.tv_right)
    ImageView tvRight;
    @BindView(R.id.rl_story)
    RecyclerView rlStory;

    StoryAdapter storyAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_story_list;
    }

    @Override
    protected void init() {
        tvTitle.setText("故事列表");
        storyAdapter = new StoryAdapter(this);
        storyAdapter.setNewData(Utils.getStoryList());
        rlStory.setLayoutManager(new LinearLayoutManager(StoryListActivity.this));
        rlStory.addItemDecoration(new DividerItemDecoration(StoryListActivity.this, DividerItemDecoration.VERTICAL));
        storyAdapter.bindToRecyclerView(rlStory);
        storyAdapter.disableLoadMoreIfNotFullPage();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onClick(Story story) {
        Intent intent = new Intent(StoryListActivity.this,WebViewActivity.class);
        intent.putExtra("detail",story);
        startActivity(intent);
    }
}
