package com.project.emotion.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.emotion.R;
import com.project.emotion.widget.CustomMp3View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Mp3Activity extends AppCompatActivity{
    @BindView(R.id.mp3)
    CustomMp3View mp3View;
    private MediaController mediaController;
    String data ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp3);
        ButterKnife.bind(this);

        mediaController = new MediaController(this);
        mp3View.setMediaController(mediaController);
        data=getIntent().getStringExtra("detail");

        if ("music01".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music01));
        }
        else if ("music02".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music02));
        }
        else if ("music03".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music03));
        }
        else if ("music04".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music04));
        }
        else if ("music05".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music05));
        }
        else if ("music06".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music06));
        }
        else if ("music07".equals(data)){
            mp3View.setVideoURI(Uri.parse("android.resource://com.project.emotion/"+R.raw.music07));
        }

        mp3View.start();
    }
}
