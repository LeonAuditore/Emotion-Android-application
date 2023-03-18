package com.project.emotion.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.emotion.R;
import com.project.emotion.entity.VideoBean;
import com.project.emotion.inter.OnVideoListener;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/20 17:59
 */
public class VideoAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    private OnVideoListener listener;
    public VideoAdapter( OnVideoListener listener) {
        super(R.layout.item_story);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
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
