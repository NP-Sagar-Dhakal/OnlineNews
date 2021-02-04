package com.newstoday.radio.radio_recent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.newstoday.R;
import com.newstoday.radio.Next_Prev_Callback;
import com.newstoday.radio.Radio_Activity;
import com.newstoday.services.SlideAd_Service;

public class Recent_Detail_Activity extends AppCompatActivity implements Next_Prev_Callback {
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radio_detail);
        int position = getIntent().getIntExtra("position", 0);
        pager = findViewById(R.id.radioViewPager);
        Recent_Pager_Adapter pagerAdapter = new Recent_Pager_Adapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(position);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Recent_Detail_Activity.this);
                SlideAd_Service.putSLIDE_AD(Recent_Detail_Activity.this, sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0) + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void nextcallback(int i) {
        pager.setCurrentItem(i);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Recent_Detail_Activity.this, Radio_Activity.class);
        startActivity(intent);
    }
}
