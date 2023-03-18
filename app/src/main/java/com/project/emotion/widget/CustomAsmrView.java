package com.project.emotion.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomAsmrView extends VideoView{
    public CustomAsmrView(Context context) {
        super(context);
    }

    public CustomAsmrView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAsmrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }
}
