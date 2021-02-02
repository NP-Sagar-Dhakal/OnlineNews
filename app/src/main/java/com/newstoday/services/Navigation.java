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
package com.newstoday.services;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.newstoday.R;
import com.newstoday.Social_Media_Fragment;
import com.newstoday.darker.ScreenFilter_Activity;
import com.newstoday.news_package.recent_news.activity.MainHomeActivity;
import com.newstoday.radio.Radio_Activity;
import com.newstoday.rssfeedreader.activity.HomeActivity;
import com.newstoday.screenfilter.Constants;
import com.newstoday.todo_diary.MainActivity;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.darktTheme:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        createNotificationChannel();
//                    }
//                    startActivity(new Intent(Navigation.this, com.newstoday.screenfilter.ui.MainActivity.class));
//                } else {
//                    Intent i = new Intent(Navigation.this, FilterService.class);
//                    if (FilterService.CURRENT_STATE == FilterService.ACTIVE) {
//                        stopService(i);
//                    } else {
//                        startService(i);
//                    }
//                }
//                break;
//            case R.id.refresh:
//                Intent intent1 = new Intent(this, MainActivity.class);
//                intent1.putExtra("realtimeURL", true);
//                startActivity(intent1);
//                break;
//            case R.id.setting: {
//                startActivity(new Intent(this, GeneralPrefsActivity.class));
//                return true;
//            }
//            case R.id.more_rate:
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Navigation.this, R.style.MyAlertDialogStyle);
//                alertDialogBuilder.setMessage("If you are facing any problems, please send us a message else, please give us 5-star ratings. It helps us to do more hard work on this app.");
//
//                alertDialogBuilder.setPositiveButton("Rate us", (dialog, which) -> {
//                    String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//                });
//                alertDialogBuilder.setNegativeButton("Send Problems", (dialog, which) -> {
//                    Intent email = new Intent(Intent.ACTION_SEND);
//                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"cherrydigital.care@gmail.com"});
//                    email.putExtra(Intent.EXTRA_SUBJECT, "Problems & Feedback from-- " + getPackageName());
//                    email.putExtra(Intent.EXTRA_TEXT, "Note : Dont't clear the subject please,\n\n");
//                    email.setType("message/rfc822");
//                    startActivity(Intent.createChooser(email, "Send Mail"));
//                });
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//                break;
//            case R.id.more_invite:
//                Intent txtIntent = new Intent(Intent.ACTION_SEND);
//                txtIntent.setType("text/plain");
//                txtIntent.putExtra(Intent.EXTRA_TEXT, "One of the best News App with online radio, todo &amp; diary, Feed Reader, and many other features in one app. Download now from Google Play Store \n\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
//                startActivity(Intent.createChooser(txtIntent, "Share"));
//                break;
//            case R.id.more_about:
//                startActivity(new Intent(Navigation.this, About_Developer.class));
//                break;
//            case R.id.more_send:
//                Intent email = new Intent(Intent.ACTION_SEND);
//                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"cherrydigital.care@gmail.com"});
//                email.putExtra(Intent.EXTRA_SUBJECT, "Problems & Feedback from" + getPackageName());
//                email.putExtra(Intent.EXTRA_TEXT, "Note : Dont't clear the subject please,\n\n");
//                email.setType("message/rfc822");
//                startActivity(Intent.createChooser(email, "Send Mail"));
//                break;
//            case R.id.more_disclaimer:
//                Disclaimer_Dialog exampleDialog = new Disclaimer_Dialog();
//                exampleDialog.show(getSupportFragmentManager(), "dialog");
//                break;
//            case R.id.more_privacy:
//                ChromeOpener opener = new ChromeOpener();
//                opener.openLink(this, "https://sites.google.com/view/hamronepal-privacy-policy/home");
//                break;
//        }
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent home = new Intent(Navigation.this, com.newstoday.news_package.recent_news.activity.MainHomeActivity.class);
                startActivity(home);
                break;
            case R.id.dark_theme:
                Theme_Service.changeTheme(Navigation.this);
                break;
            case R.id.radio:
                if (MainHomeActivity.radioItems.isEmpty()) {
                    Toast.makeText(this, getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
                } else {
                    Intent radio = new Intent(Navigation.this, Radio_Activity.class);
                    startActivity(radio);
                }
                break;
            case R.id.screen_filter:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotificationChannel();
                    }
                    startActivity(new Intent(Navigation.this, com.newstoday.screenfilter.ui.MainActivity.class));
                } else {
                    startActivity(new Intent(Navigation.this, ScreenFilter_Activity.class));
                }
                break;
            case R.id.socialMedia:
                startActivity(new Intent(this, Social_Media_Fragment.class));
                break;
            case R.id.diary_reminder:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.rss_reader:
                Intent rss_reader = new Intent(Navigation.this, HomeActivity.class);
                startActivity(rss_reader);
                break;
            case R.id.nav_exit:
                Navigation.this.moveTaskToBack(true);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
