package com.example.usuario.relacionficheros;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ImageView imvAnimation;
    Animation anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {

        imvAnimation = (ImageView)findViewById(R.id.imvSplash);
        anim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.vertical_move);
        imvAnimation.setAnimation(anim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this, SelectionActivity.class));
                finish();

            }
        }, 1200);

    }
}
