/*
  OnlineNews
  <p/>
  Copyright (c) 2019-2021 Sagar Dhakal
  <p/>
  This program is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  <p/>
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
  <p/>
  You should have received a copy of the GNU General Public License
  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.newstoday.activities.walkthrough;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.newstoday.activities.Splash_Activity;
import com.newstoday.nepali.news.R;
import com.newstoday.services.InternetIsConnected;

import java.util.ArrayList;
import java.util.List;

public class Activity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabIndicator;
    ImageButton btnNext;
    int position = 0;
    Button btnGetStarted;
    private ViewPager screenPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), Splash_Activity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.walk_activity_intro);

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);

        final List<ScreenItems> mList = new ArrayList<>();
        mList.add(new ScreenItems("News by Location", "One of the best News Application with many more features.", R.drawable.app_icon));
        mList.add(new ScreenItems("All Online Radio Stations", "Almost all Online Radio Stations are available to listen online.", R.drawable.ic_radio));
        mList.add(new ScreenItems("App Permissions", "External Storage is used to save app data and read them.<br><br>Call permission is used to stop radio player when you are receiving phone calls.", R.drawable.app_icon));

        screenPager = findViewById(R.id.screen_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(this, mList);
        screenPager.setAdapter(viewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(v -> {
            position = screenPager.getCurrentItem();
            if (position < mList.size()) {
                position++;
                screenPager.setCurrentItem(position);
            }

            if (position == mList.size() - 1) {
                loaddLastScreen();
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loaddLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        btnGetStarted.setOnClickListener(v -> {
            InternetIsConnected isConnected = new InternetIsConnected();
            if (isConnected.internetIsConnected()) {
                Intent mainActivity = new Intent(getApplicationContext(), Splash_Activity.class);
                startActivity(mainActivity);
                finish();
            } else {
                Toast.makeText(this, this.getResources().getString(R.string.connect_internet), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isIntroOpnend", false);
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
    }

}
