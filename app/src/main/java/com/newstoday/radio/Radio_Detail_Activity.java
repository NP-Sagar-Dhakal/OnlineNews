package com.newstoday.radio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.newstoday.R;
import com.newstoday.services.SlideAd_Service;

public class Radio_Detail_Activity extends AppCompatActivity implements Next_Prev_Callback {
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
        Radio_Pager_Adapter pagerAdapter = new Radio_Pager_Adapter(getSupportFragmentManager());
        pagerAdapter.notifyDataSetChanged();
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Radio_Detail_Activity.this);
                SlideAd_Service.putSLIDE_AD(Radio_Detail_Activity.this, sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0) + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        pager.setCurrentItem(position);
    }

    @Override
    public void nextcallback(int i) {
        pager.setCurrentItem(i);
    }
}
