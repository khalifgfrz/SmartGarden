package com.example.smartgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // method ini akan di eksekusi setelah timer SELESAI

                // start Main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);

                startActivity(i);
                // tutup activity ini
                finish();
            }
        }, SPLASH_TIME_OUT);
    }



}
