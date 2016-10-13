package com.example.usuario.videoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {


    private VideoView video;
    private MediaController meController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        video = (VideoView)findViewById(R.id.video);
        meController = new MediaController(VideoViewActivity.this);
        video.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.video);
        video.setMediaController(meController);
        video.start();




    }
}
