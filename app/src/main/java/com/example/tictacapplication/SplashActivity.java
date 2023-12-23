package com.example.tictacapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent is used to switch from one activity to another.
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i); // invoke the SecondActivity.
                finish(); // the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}