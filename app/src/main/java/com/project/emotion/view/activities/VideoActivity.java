package com.project.emotion.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.emotion.R;
import com.project.emotion.widget.CustomVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.video)
    CustomVideoView videoView;
    private MediaController mediaController;
    String data ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        ButterKnife.bind(this);
        mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        data = getIntent().getStringExtra("detail");
        if ("one".equals(data)){
            //下面android.resource://是固定的，com.example.work是包名，R.raw.sw是你raw文件夹下的视频文件
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.one));
        }else if ("two".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.two));
        }else if ("three".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.three));
        }else if ("four".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.four));
        }else if ("five".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.five));
        }else if ("six".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.six));
        }else if ("seven".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.seven));
        }else if ("eight".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.eight));
        }else if ("nine".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.nine));
        }else if ("ten".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.ten));
        }else if ("ele".equals(data)){
            videoView.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.ele));
        }


        videoView.start();

    }
}
