package com.example.usuario.videoview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {


    private VideoView video;
    private MediaController meController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        video = (VideoView) findViewById(R.id.video);
        meController = new MediaController(VideoViewActivity.this);
        //Select video for play his.
         video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.diva);
        //Add the controller for video
        video.setMediaController(meController);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {


            //Save current time from video
            int pos = video.getCurrentPosition();
            // Save the param
            outState.putInt("position", pos);
            outState.getBoolean("playing", video.isPlaying());



    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        //Save the time when the video stopped
        video.seekTo(savedInstanceState.getInt("position"));

        boolean a = savedInstanceState.getBoolean("playing");

        if(!savedInstanceState.getBoolean("playing")){

            video.pause();

        }else{

            video.start();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Pause the video
            video.pause();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Play the video
        video.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Resume the video
        video.resume();
    }
}