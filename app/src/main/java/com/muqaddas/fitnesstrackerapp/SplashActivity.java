package com.muqaddas.fitnesstrackerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);  // Link to XML layout

        ImageView logo = findViewById(R.id.logo);  // Linking to ImageView

        // Animation for logo
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);  // Start animation

        // Delay then move to main screen
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);  // Transition to MainActivity
            startActivity(i);
            finish();
        }, 3000); // 3 second delay
    }
}
