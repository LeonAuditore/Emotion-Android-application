package com.project.emotion.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.project.emotion.R;
import com.project.emotion.entity.Mp3Bean;
import com.project.emotion.entity.VideoBean;
import com.project.emotion.inter.OnMp3Listener;

public class Mp3Adapter extends BaseQuickAdapter<Mp3Bean,BaseViewHolder>{
    private OnMp3Listener listener;
    public Mp3Adapter(OnMp3Listener listener){
        super(R.layout.item_story);
        this.listener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, Mp3Bean item){
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