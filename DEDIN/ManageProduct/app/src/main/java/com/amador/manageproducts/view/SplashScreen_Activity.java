package com.amador.manageproducts.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.amador.manageproducts.R;

public class SplashScreen_Activity extends AppCompatActivity {

    private static final int SPLASH_DURATION_MS = 1400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen_Activity.this, R.anim.splash_logo);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);
        if (imgLogo != null) {
            imgLogo.setAnimation(animation);
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen_Activity.this, Login_Activity.class));
                finish();
            }
        }, SPLASH_DURATION_MS);
    }
}
