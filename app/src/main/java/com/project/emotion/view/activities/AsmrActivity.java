package com.project.emotion.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.emotion.R;
import com.project.emotion.widget.CustomAsmrView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsmrActivity extends AppCompatActivity{
    @BindView(R.id.asmr)
    CustomAsmrView asmrView;
    private MediaController mediaController;
    String data ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asmr);
        ButterKnife.bind(this);
        mediaController=new MediaController(this);
        asmrView.setMediaController(mediaController);
        data = getIntent().getStringExtra("detail");

        if("asmr01".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr01));
        }
        else if("asmr02".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr02));
        }
        else if("asmr03".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr03));
        }
        else if("asmr04".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr04));
        }
        else if("asmr05".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr05));
        }
        else if("asmr06".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr06));
        }
        else if("asmr07".equals(data)) {
            asmrView.setVideoURI(Uri.parse("android.resource://com.project.emotion/" + R.raw.asmr07));
        }
        asmrView.start();
    }
}
