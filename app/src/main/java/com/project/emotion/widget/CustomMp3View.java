package com.project.emotion.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomMp3View extends VideoView{
    public CustomMp3View(Context context){super(context);}

    public CustomMp3View(Context context,AttributeSet attars){super(context,attars);}

    public CustomMp3View(Context context,AttributeSet attars,int defstyleAttr){
        super(context,attars,defstyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }
}
