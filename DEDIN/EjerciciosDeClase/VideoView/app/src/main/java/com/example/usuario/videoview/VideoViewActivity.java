package com.example.usuario.videoview;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {


    private VideoView video;
    private MediaController meController;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        video = (VideoView) findViewById(R.id.video);
        meController = new MediaController(VideoViewActivity.this);
        mPlayer = new MediaPlayer();
        //Select video for play his.
         video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.diva);
        //Add the controller for video
        video.setMediaController(meController);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            //Save current time from video
            int pos = video.getCurrentPosition();
            // Guardamos el tiempo en el que se encuentra el vídeo en el Bundle
            outState.putInt("position", pos);
            //Preguntamos como estaba el vídeo ya que antes paso por onPause y allí lo guardamos en el Intent
            boolean playing = getIntent().getExtras().getBoolean("playing");
            //Actuamos según su estado
            if(!playing){

                video.pause();
            }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Save the time when the video stopped
        video.seekTo(savedInstanceState.getInt("position"));
        //Comprobamos el estado del vídeo y actuamos.
        boolean playing = getIntent().getExtras().getBoolean("playing");
        if(!playing){

            video.pause();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        //Pause the video
        //Guardamos el estado en el que se encuentra el vídeo en el Intent de la Activity
        //ya que es el primer método por el que pasa al giran el móvil.
        boolean playing = video.isPlaying();
        getIntent().putExtra("playing", playing);
        if(!playing){

            video.pause();
        }





    }

    @Override
    protected void onStart() {
        super.onStart();
        //Este solo se ejecuta la primera vez por lo que lanzamos el video
        video.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //Resume the video
        /*
        Si es la primera vez que se ejecuta la activity el Intent lanzará un
        NullPointerException por lo que intentamos provocar la excepción para
        saber si es la primera vez o no.
         */
            try {

                boolean playing = getIntent().getExtras().getBoolean("playing");
                video.resume();


            } catch (Exception e) {

                video.start();
            }

    }

    @Override
    protected void onStop() {
        super.onStop();
       //stops the video when the user clicks back, thus avoiding a error.
        video.stopPlayback();

    }
}