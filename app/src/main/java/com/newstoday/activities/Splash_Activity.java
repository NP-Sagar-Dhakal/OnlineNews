package com.newstoday.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.newstoday.R;
import com.newstoday.news_package.recent_news.activity.MainHomeActivity;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(Splash_Activity.this, MainHomeActivity.class);
            intent.putExtra("realtimeURL", true);
            startActivity(intent);
            finish();
        }, 3000);
    }
}