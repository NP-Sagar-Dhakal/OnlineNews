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

package com.newstoday.radio;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.newstoday.R;
import com.newstoday.items.NewsItem;
import com.newstoday.radio.radioplayer_service.RadioService;
import com.newstoday.screenfilter.Constants;
import com.newstoday.services.CacheCleaner;
import com.newstoday.services.FilterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Radio_Activity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {

    private RadioService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        CacheCleaner cacheCleaner = new CacheCleaner();
        cacheCleaner.clearCacheFolder(this.getCacheDir(), 5);

        service = new RadioService();
        Toolbar radio_Toolbar = findViewById(R.id.radio_Toolbar);
        TabLayout radio_TabLayout = findViewById(R.id.radio_Tab);
        ViewPager radio_Main_ViewPager = findViewById(R.id.radio_ViewPager);
        setSupportActionBar(radio_Toolbar);
        setupViewPgar(radio_Main_ViewPager);
        radio_TabLayout.setupWithViewPager(radio_Main_ViewPager);

        try {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setupViewPgar(ViewPager pager) {
        Main_Radio_ViewPager_Adapter pager_adapter = new Main_Radio_ViewPager_Adapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        pager_adapter.addFragment(new All_Radio_Fragment(), "All");
        pager_adapter.addFragment(new Favourite_Radio_Fragment(), "Favourites");
        pager_adapter.addFragment(new Recent_Radio_Fragment(), "Recent");
        pager.setAdapter(pager_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radio_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.radio_Search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        List<NewsItem.News.OnlineRadios> stringList = new ArrayList<>();

        for (NewsItem.News.OnlineRadios string : All_Radio_Fragment.radioItems) {
            if (string.stationName.toLowerCase().contains(userInput)) {
                stringList.add(string);
            }
        }
        try {
            All_Radio_Fragment.adapter.updateList(stringList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            NotificationChannel channel = new NotificationChannel(
                    Constants.NOTIFICATION_CHANNEL_ID_RS,
                    getString(R.string.notification_channel_running_status),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setShowBadge(false);
            channel.enableLights(false);
            channel.enableVibration(false);
            channel.setSound(null, null);

            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.radio_darktheme) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel();
                }
                startActivity(new Intent(Radio_Activity.this, com.newstoday.screenfilter.ui.MainActivity.class));
            } else {
                Intent i = new Intent(Radio_Activity.this, FilterService.class);
                if (FilterService.CURRENT_STATE == FilterService.ACTIVE) {
                    stopService(i);
                } else {
                    startService(i);
                }
            }
        } else if (id == R.id.radio_timer) {
            PopupMenu popup = new PopupMenu(Radio_Activity.this, findViewById(R.id.radio_timer));
            popup.setOnMenuItemClickListener(item1 -> {
                switch (item1.getItemId()) {
                    case R.id.ten_minutes:
                        service.sleepTimer(this, 10);
                        return true;
                    case R.id.twentyfive_minutes:
                        service.sleepTimer(this, 25);
                        return true;

                    case R.id.fourty_minutes:
                        service.sleepTimer(this, 40);
                        return true;

                    case R.id.sixty_minutes:
                        service.sleepTimer(this, 60);
                        return true;

                }
                return true;
            });

            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.radio_timer_menu, popup.getMenu());
            popup.show();
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, com.newstoday.news_package.recent_news.activity.MainHomeActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
