package com.project.emotion.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author 袁茏天
 * @description:
 * @date :2022/3/20 17:47
 */
public class CustomVideoView extends VideoView {

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }
}