package com.example.mother_portal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        TextView welcomeText =findViewById(R.id.welcomeText);

        // Start animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        logo.startAnimation(animation);
        // WELCOME ANIMATION
        Animation tectAnim =AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        welcomeText.startAnimation(tectAnim);
        // Go to MainActivity after animation
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1600);
    }
}
