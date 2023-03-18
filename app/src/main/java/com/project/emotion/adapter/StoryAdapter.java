package com.project.emotion.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.emotion.R;
import com.project.emotion.entity.Story;
import com.project.emotion.inter.OnStoryListener;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/17 22:32
 */
public class StoryAdapter extends BaseQuickAdapter<Story, BaseViewHolder> {
    private OnStoryListener listener;
    public StoryAdapter(OnStoryListener listener) {
        super(R.layout.item_story);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Story item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onClick(item);
                }
            }
        });
    }
}
