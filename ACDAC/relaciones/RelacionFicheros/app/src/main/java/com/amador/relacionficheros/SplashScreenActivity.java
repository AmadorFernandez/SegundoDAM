package com.amador.relacionficheros;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    private Animation anim;
    private ImageView imvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();

    }

    private void init() {

        anim = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.descendan_move);
        imvLogo = (ImageView)findViewById(R.id.imvLogoSplash);
        imvLogo.setAnimation(anim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this, SelectionActivity.class));
                finish();

            }
        }, 2000);
    }
}
