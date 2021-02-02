/*
  OnlineNews
  <p/>
  Copyright (c) 2019-2021 Sagar Dhakal
  Copyright (c) 2015-2016 Arnaud Renaud-Goud
  Copyright (c) 2012-2015 Frederic Julian
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

package com.newstoday.news_package.recent_news.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobScheduler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newstoday.Constants;
import com.newstoday.R;
import com.newstoday.items.NewsItem;
import com.newstoday.news_package.recent_news.fragment.EntriesListFragment;
import com.newstoday.news_package.recent_news.provider.FeedData;
import com.newstoday.news_package.recent_news.provider.FeedData.EntryColumns;
import com.newstoday.news_package.recent_news.provider.FeedData.FeedColumns;
import com.newstoday.news_package.recent_news.provider.FeedDataContentProvider;
import com.newstoday.news_package.recent_news.service.FetcherService;
import com.newstoday.news_package.recent_news.service.RefreshService;
import com.newstoday.news_package.recent_news.utils.PrefUtils;
import com.newstoday.services.Custom_JobSheduler;
import com.newstoday.services.InternetIsConnected;
import com.newstoday.services.Navigation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainHomeActivity extends Navigation implements LoaderManager.LoaderCallbacks<Cursor>, NavigationView.OnNavigationItemSelectedListener {
    public static final int MULTIPLE_PERMISSIONS = 10; // cod
    private static final String STATE_CURRENT_DRAWER_POS = "STATE_CURRENT_DRAWER_POS";
    private static final String FEED_UNREAD_NUMBER = "(SELECT " + Constants.DB_COUNT + " FROM " + EntryColumns.TABLE_NAME + " WHERE " +
            EntryColumns.IS_READ + " IS NULL AND " + EntryColumns.FEED_ID + '=' + FeedColumns.TABLE_NAME + '.' + FeedColumns._ID + ')';
    private static final int LOADER_ID = 0;
    public static List<NewsItem.News.RecentRssLinks> recentRssLinks;
    public static List<NewsItem.News.TopNewsSites> topNewsSites;
    public static List<NewsItem.News.OnlineRadios> radioItems;
    public static String category1Name, category2Name, category3Name, category4Name, category5Name, category6Name, category7Name, category8Name, category9Name, category10Name, category11Name, category12Name, category13Name, category14Name, category15Name;
    public static String location1Name, location2Name, location3Name, location4Name, location5Name, location6Name, location7Name, location8Name, location9Name, location10Name, location11Name, location12Name, location13Name, location14Name, location15Name, location16Name, location17Name, location18Name, location19Name, location20Name, location21Name, location22Name, location23Name, location24Name, location25Name, location26Name, location27Name, location28Name, location29Name, location30Name;
    public static List<NewsItem.News.Categories.Category1.Category1NewsSites> category1NewsSites;
    public static List<NewsItem.News.Categories.Category1.Category1RssLinks> category1RssLinks;
    public static List<NewsItem.News.Categories.Category2.Category2NewsSites> category2NewsSites;
    public static List<NewsItem.News.Categories.Category2.Category2RssLinks> category2RssLinks;
    public static List<NewsItem.News.Categories.Category3.Category3NewsSites> category3NewsSites;
    public static List<NewsItem.News.Categories.Category3.Category3RssLinks> category3RssLinks;
    public static List<NewsItem.News.Categories.Category4.Category4NewsSites> category4NewsSites;
    public static List<NewsItem.News.Categories.Category4.Category4RssLinks> category4RssLinks;
    public static List<NewsItem.News.Categories.Category5.Category5NewsSites> category5NewsSites;
    public static List<NewsItem.News.Categories.Category5.Category5RssLinks> category5RssLinks;
    public static List<NewsItem.News.Categories.Category6.Category6NewsSites> category6NewsSites;
    public static List<NewsItem.News.Categories.Category6.Category6RssLinks> category6RssLinks;
    public static List<NewsItem.News.Categories.Category7.Category7NewsSites> category7NewsSites;
    public static List<NewsItem.News.Categories.Category7.Category7RssLinks> category7RssLinks;
    public static List<NewsItem.News.Categories.Category8.Category8NewsSites> category8NewsSites;
    public static List<NewsItem.News.Categories.Category8.Category8RssLinks> category8RssLinks;
    public static List<NewsItem.News.Categories.Category9.Category9NewsSites> category9NewsSites;
    public static List<NewsItem.News.Categories.Category9.Category9RssLinks> category9RssLinks;
    public static List<NewsItem.News.Categories.Category10.Category10NewsSites> category10NewsSites;
    public static List<NewsItem.News.Categories.Category10.Category10RssLinks> category10RssLinks;
    public static List<NewsItem.News.Categories.Category11.Category11NewsSites> category11NewsSites;
    public static List<NewsItem.News.Categories.Category11.Category11RssLinks> category11RssLinks;
    public static List<NewsItem.News.Categories.Category12.Category12NewsSites> category12NewsSites;
    public static List<NewsItem.News.Categories.Category12.Category12RssLinks> category12RssLinks;
    public static List<NewsItem.News.Categories.Category13.Category13NewsSites> category13NewsSites;
    public static List<NewsItem.News.Categories.Category13.Category13RssLinks> category13RssLinks;
    public static List<NewsItem.News.Categories.Category14.Category14NewsSites> category14NewsSites;
    public static List<NewsItem.News.Categories.Category14.Category14RssLinks> category14RssLinks;
    public static List<NewsItem.News.Categories.Category15.Category15NewsSites> category15NewsSites;
    public static List<NewsItem.News.Categories.Category15.Category15RssLinks> category15RssLinks;
    public static List<NewsItem.News.Location.Location1.Location1NewsSites> location1NewsSites;
    public static List<NewsItem.News.Location.Location1.Location1RssLinks> Location1RssLinks;
    public static List<NewsItem.News.Location.Location2.Location2NewsSites> location2NewsSites;
    public static List<NewsItem.News.Location.Location2.Location2RssLinks> Location2RssLinks;
    public static List<NewsItem.News.Location.Location3.Location3NewsSites> location3NewsSites;
    public static List<NewsItem.News.Location.Location3.Location3RssLinks> Location3RssLinks;
    public static List<NewsItem.News.Location.Location4.Location4NewsSites> location4NewsSites;
    public static List<NewsItem.News.Location.Location4.Location4RssLinks> Location4RssLinks;
    public static List<NewsItem.News.Location.Location5.Location5NewsSites> location5NewsSites;
    public static List<NewsItem.News.Location.Location5.Location5RssLinks> Location5RssLinks;
    public static List<NewsItem.News.Location.Location6.Location6NewsSites> location6NewsSites;
    public static List<NewsItem.News.Location.Location6.Location6RssLinks> Location6RssLinks;
    public static List<NewsItem.News.Location.Location7.Location7NewsSites> location7NewsSites;
    public static List<NewsItem.News.Location.Location7.Location7RssLinks> Location7RssLinks;
    public static List<NewsItem.News.Location.Location8.Location8NewsSites> location8NewsSites;
    public static List<NewsItem.News.Location.Location8.Location8RssLinks> Location8RssLinks;
    public static List<NewsItem.News.Location.Location9.Location9NewsSites> location9NewsSites;
    public static List<NewsItem.News.Location.Location9.Location9RssLinks> Location9RssLinks;
    public static List<NewsItem.News.Location.Location10.Location10NewsSites> location10NewsSites;
    public static List<NewsItem.News.Location.Location10.Location10RssLinks> Location10RssLinks;
    public static List<NewsItem.News.Location.Location11.Location11NewsSites> location11NewsSites;
    public static List<NewsItem.News.Location.Location11.Location11RssLinks> Location11RssLinks;
    public static List<NewsItem.News.Location.Location12.Location12NewsSites> location12NewsSites;
    public static List<NewsItem.News.Location.Location12.Location12RssLinks> Location12RssLinks;
    public static List<NewsItem.News.Location.Location13.Location13NewsSites> location13NewsSites;
    public static List<NewsItem.News.Location.Location13.Location13RssLinks> Location13RssLinks;
    public static List<NewsItem.News.Location.Location14.Location14NewsSites> location14NewsSites;
    public static List<NewsItem.News.Location.Location14.Location14RssLinks> Location14RssLinks;
    public static List<NewsItem.News.Location.Location15.Location15NewsSites> location15NewsSites;
    public static List<NewsItem.News.Location.Location15.Location15RssLinks> Location15RssLinks;
    public static List<NewsItem.News.Location.Location16.Location16NewsSites> location16NewsSites;
    public static List<NewsItem.News.Location.Location16.Location16RssLinks> Location16RssLinks;
    public static List<NewsItem.News.Location.Location17.Location17NewsSites> location17NewsSites;
    public static List<NewsItem.News.Location.Location17.Location17RssLinks> Location17RssLinks;
    public static List<NewsItem.News.Location.Location18.Location18NewsSites> location18NewsSites;
    public static List<NewsItem.News.Location.Location18.Location18RssLinks> Location18RssLinks;
    public static List<NewsItem.News.Location.Location19.Location19NewsSites> location19NewsSites;
    public static List<NewsItem.News.Location.Location19.Location19RssLinks> Location19RssLinks;
    public static List<NewsItem.News.Location.Location20.Location20NewsSites> location20NewsSites;
    public static List<NewsItem.News.Location.Location20.Location20RssLinks> Location20RssLinks;
    public static List<NewsItem.News.Location.Location21.Location21NewsSites> location21NewsSites;
    public static List<NewsItem.News.Location.Location21.Location21RssLinks> Location21RssLinks;
    public static List<NewsItem.News.Location.Location22.Location22NewsSites> location22NewsSites;
    public static List<NewsItem.News.Location.Location22.Location22RssLinks> Location22RssLinks;
    public static List<NewsItem.News.Location.Location23.Location23NewsSites> location23NewsSites;
    public static List<NewsItem.News.Location.Location23.Location23RssLinks> Location23RssLinks;
    public static List<NewsItem.News.Location.Location24.Location24NewsSites> location24NewsSites;
    public static List<NewsItem.News.Location.Location24.Location24RssLinks> Location24RssLinks;
    public static List<NewsItem.News.Location.Location25.Location25NewsSites> location25NewsSites;
    public static List<NewsItem.News.Location.Location25.Location25RssLinks> Location25RssLinks;
    public static List<NewsItem.News.Location.Location26.Location26NewsSites> location26NewsSites;
    public static List<NewsItem.News.Location.Location26.Location26RssLinks> Location26RssLinks;
    public static List<NewsItem.News.Location.Location27.Location27NewsSites> location27NewsSites;
    public static List<NewsItem.News.Location.Location27.Location27RssLinks> Location27RssLinks;
    public static List<NewsItem.News.Location.Location28.Location28NewsSites> location28NewsSites;
    public static List<NewsItem.News.Location.Location28.Location28RssLinks> Location28RssLinks;
    public static List<NewsItem.News.Location.Location29.Location29NewsSites> location29NewsSites;
    public static List<NewsItem.News.Location.Location29.Location29RssLinks> Location29RssLinks;
    public static List<NewsItem.News.Location.Location30.Location30NewsSites> location30NewsSites;
    public static List<NewsItem.News.Location.Location30.Location30RssLinks> Location30RssLinks;
    public static List<NewsItem.News.SocialMedia> socialMedia;
    private final SharedPreferences.OnSharedPreferenceChangeListener mShowReadListener = (sharedPreferences, key) -> {
        if (PrefUtils.SHOW_READ.equals(key)) {
            getLoaderManager().restartLoader(LOADER_ID, null, MainHomeActivity.this);

        }
    };
    private final String demo = "News Today";
    InternetIsConnected isConnected;
    SharedPreferences sharedPreferences;
    @SuppressLint("InlinedApi")
    String[] permissions = {
            Manifest.permission.FOREGROUND_SERVICE,
            Manifest.permission.VIBRATE,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CLEAR_APP_CACHE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED
    };
    private EntriesListFragment mEntriesFragment;
    private int mCurrentDrawerPos;
    private int i;

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAnalytics mFirebaseAnalytics;
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();

        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Main Activity Started");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findViewById(R.id.drawer_layout).setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainHomeActivity.this);
        isConnected = new InternetIsConnected();

        String responses = sharedPreferences.getString("NewsSites", "");
        String databaseLink = sharedPreferences.getString("DatabaseLink", "");

        if (Objects.requireNonNull(responses).equals("")) {
            Toast.makeText(this, this.getResources().getString(R.string.please_wait), Toast.LENGTH_LONG).show();
            try {
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference().child("News Today").child("Project News");
            scoresRef.keepSynced(false);
            DatabaseReference jokeslinks = FirebaseDatabase.getInstance().getReference().child("News Today").child("Project News");
            jokeslinks.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        String databaseUrl = Objects.requireNonNull(dataSnapshot.child("database").getValue()).toString();
                        getRealtimeUrl(savedInstanceState, databaseUrl, !Objects.requireNonNull(databaseLink).equals(databaseUrl));
                        FirebaseDatabase.getInstance().goOffline();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            if (getIntent().getBooleanExtra("realtimeURL", false)) {
                try {
                    FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference().child("News Today").child("Project News");
                scoresRef.keepSynced(false);
                DatabaseReference jokeslinks = FirebaseDatabase.getInstance().getReference().child("News Today").child("Project News");
                jokeslinks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try {
                            String databaseUrl = Objects.requireNonNull(dataSnapshot.child("database").getValue()).toString();
                            getRealtimeUrl(savedInstanceState, databaseUrl, !Objects.requireNonNull(databaseLink).equals(databaseUrl));
                            FirebaseDatabase.getInstance().goOffline();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } else {
                getNewsItem(savedInstanceState, responses);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.home_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    private void getRealtimeUrl(Bundle savedInstanceState, String databaseUrl, boolean fetchdata) {
        String responses = sharedPreferences.getString("NewsSites", "");
        if (Objects.requireNonNull(responses).equals("")) {
            if (isConnected.internetIsConnected()) {
                AsyncTask.execute(() -> {
                    StringRequest stringRequest = new StringRequest(databaseUrl, response -> {
                        sharedResponse("NewsSites", response);
                        getNewsItem(savedInstanceState, response);
                    }, error -> {
                        //Todo
                        Toast.makeText(MainHomeActivity.this, this.getResources().getString(R.string.server_busy), Toast.LENGTH_LONG).show();
                    });
                    RequestQueue queue = Volley.newRequestQueue(MainHomeActivity.this);
                    queue.add(stringRequest);
                });

            } else {
                Toast.makeText(MainHomeActivity.this, this.getResources().getString(R.string.turn_on_internet), Toast.LENGTH_LONG).show();
            }
        } else {
            getNewsItem(savedInstanceState, responses);
            if (fetchdata) {
                AsyncTask.execute(() -> {
                    StringRequest stringRequest = new StringRequest(databaseUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            sharedResponse("NewsSites", response);
                            sharedResponse("DatabaseLink", databaseUrl);
                        }
                    }, error -> {
                    });
                    RequestQueue queue = Volley.newRequestQueue(MainHomeActivity.this);
                    queue.add(stringRequest);
                });
            }
        }
    }

    private void getNewsItem(Bundle savedInstanceState, String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        NewsItem newsItems = gson.fromJson(response, NewsItem.class);
        try {
            topNewsSites = newsItems.News.get(3).TopNewsSites;
            recentRssLinks = newsItems.News.get(4).recentRssLinks;
            radioItems = newsItems.News.get(5).OnlineRadios;

            category1Name = newsItems.News.get(0).Categories.get(0).category1.category1Name;
            category2Name = newsItems.News.get(0).Categories.get(1).category2.category2Name;
            category3Name = newsItems.News.get(0).Categories.get(2).category3.category3Name;
            category4Name = newsItems.News.get(0).Categories.get(3).category4.category4Name;
            category5Name = newsItems.News.get(0).Categories.get(4).category5.category5Name;
            category6Name = newsItems.News.get(0).Categories.get(5).category6.category6Name;
            category7Name = newsItems.News.get(0).Categories.get(6).category7.category7Name;
            category8Name = newsItems.News.get(0).Categories.get(7).category8.category8Name;
            category9Name = newsItems.News.get(0).Categories.get(8).category9.category9Name;
            category10Name = newsItems.News.get(0).Categories.get(9).category10.category10Name;
            category11Name = newsItems.News.get(0).Categories.get(10).category11.category11Name;
            category12Name = newsItems.News.get(0).Categories.get(11).category12.category12Name;
            category13Name = newsItems.News.get(0).Categories.get(12).category13.category13Name;
            category14Name = newsItems.News.get(0).Categories.get(13).category14.category14Name;
            category15Name = newsItems.News.get(0).Categories.get(14).category15.category15Name;

            location1Name = newsItems.News.get(1).Location.get(0).location1.location1Name;
            location2Name = newsItems.News.get(1).Location.get(1).location2.location2Name;
            location3Name = newsItems.News.get(1).Location.get(2).location3.location3Name;
            location4Name = newsItems.News.get(1).Location.get(3).location4.location4Name;
            location5Name = newsItems.News.get(1).Location.get(4).location5.location5Name;
            location6Name = newsItems.News.get(1).Location.get(5).location6.location6Name;
            location7Name = newsItems.News.get(1).Location.get(6).location7.location7Name;
            location8Name = newsItems.News.get(1).Location.get(7).location8.location8Name;
            location9Name = newsItems.News.get(1).Location.get(8).location9.location9Name;
            location10Name = newsItems.News.get(1).Location.get(9).location10.location10Name;
            location11Name = newsItems.News.get(1).Location.get(10).location11.location11Name;
            location12Name = newsItems.News.get(1).Location.get(11).location12.location12Name;
            location13Name = newsItems.News.get(1).Location.get(12).location13.location13Name;
            location14Name = newsItems.News.get(1).Location.get(13).location14.location14Name;
            location15Name = newsItems.News.get(1).Location.get(14).location15.location15Name;
            location16Name = newsItems.News.get(1).Location.get(15).location16.location16Name;
            location17Name = newsItems.News.get(1).Location.get(16).location17.location17Name;
            location18Name = newsItems.News.get(1).Location.get(17).location18.location18Name;
            location19Name = newsItems.News.get(1).Location.get(18).location19.location19Name;
            location20Name = newsItems.News.get(1).Location.get(19).location20.location20Name;
            location21Name = newsItems.News.get(1).Location.get(20).location21.location21Name;
            location22Name = newsItems.News.get(1).Location.get(21).location22.location22Name;
            location23Name = newsItems.News.get(1).Location.get(22).location23.location23Name;
            location24Name = newsItems.News.get(1).Location.get(23).location24.location24Name;
            location25Name = newsItems.News.get(1).Location.get(24).location25.location25Name;
            location26Name = newsItems.News.get(1).Location.get(25).location26.locatio26nName;
            location27Name = newsItems.News.get(1).Location.get(26).location27.location27Name;
            location28Name = newsItems.News.get(1).Location.get(27).location28.location28Name;
            location29Name = newsItems.News.get(1).Location.get(28).location29.location29Name;
            location30Name = newsItems.News.get(1).Location.get(29).location30.location30Name;

            category1NewsSites = newsItems.News.get(0).Categories.get(0).category1.category1NewsSites;
            category1RssLinks = newsItems.News.get(0).Categories.get(0).category1.category1RssLinks;
            category2NewsSites = newsItems.News.get(0).Categories.get(1).category2.category2NewsSites;
            category2RssLinks = newsItems.News.get(0).Categories.get(1).category2.category2RssLinks;
            category3NewsSites = newsItems.News.get(0).Categories.get(2).category3.category3NewsSites;
            category3RssLinks = newsItems.News.get(0).Categories.get(2).category3.category3RssLinks;
            category4NewsSites = newsItems.News.get(0).Categories.get(3).category4.category4NewsSites;
            category4RssLinks = newsItems.News.get(0).Categories.get(3).category4.category4RssLinks;
            category5NewsSites = newsItems.News.get(0).Categories.get(4).category5.category5NewsSites;
            category5RssLinks = newsItems.News.get(0).Categories.get(4).category5.category5RssLinks;
            category6NewsSites = newsItems.News.get(0).Categories.get(5).category6.category6NewsSites;
            category6RssLinks = newsItems.News.get(0).Categories.get(5).category6.category6RssLinks;
            category7NewsSites = newsItems.News.get(0).Categories.get(6).category7.category7NewsSites;
            category7RssLinks = newsItems.News.get(0).Categories.get(6).category7.category7RssLinks;
            category8NewsSites = newsItems.News.get(0).Categories.get(7).category8.category8NewsSites;
            category8RssLinks = newsItems.News.get(0).Categories.get(7).category8.category8RssLinks;
            category9NewsSites = newsItems.News.get(0).Categories.get(8).category9.category9NewsSites;
            category9RssLinks = newsItems.News.get(0).Categories.get(8).category9.category9RssLinks;
            category10NewsSites = newsItems.News.get(0).Categories.get(9).category10.category10NewsSites;
            category10RssLinks = newsItems.News.get(0).Categories.get(9).category10.category10RssLinks;
            category11NewsSites = newsItems.News.get(0).Categories.get(10).category11.category11NewsSites;
            category11RssLinks = newsItems.News.get(0).Categories.get(10).category11.category11RssLinks;
            category12NewsSites = newsItems.News.get(0).Categories.get(11).category12.category12NewsSites;
            category12RssLinks = newsItems.News.get(0).Categories.get(11).category12.category12RssLinks;
            category13NewsSites = newsItems.News.get(0).Categories.get(12).category13.category13NewsSites;
            category13RssLinks = newsItems.News.get(0).Categories.get(12).category13.category13RssLinks;
            category14NewsSites = newsItems.News.get(0).Categories.get(13).category14.category14NewsSites;
            category14RssLinks = newsItems.News.get(0).Categories.get(13).category14.category14RssLinks;
            category15NewsSites = newsItems.News.get(0).Categories.get(14).category15.category15NewsSites;
            category15RssLinks = newsItems.News.get(0).Categories.get(14).category15.category15RssLinks;

            location1NewsSites = newsItems.News.get(1).Location.get(0).location1.location1NewsSites;
            Location1RssLinks = newsItems.News.get(1).Location.get(0).location1.location1RssLinks;
            location2NewsSites = newsItems.News.get(1).Location.get(1).location2.location2NewsSites;
            Location2RssLinks = newsItems.News.get(1).Location.get(1).location2.location2RssLinks;
            location3NewsSites = newsItems.News.get(1).Location.get(2).location3.location3NewsSites;
            Location3RssLinks = newsItems.News.get(1).Location.get(2).location3.location3RssLinks;
            location4NewsSites = newsItems.News.get(1).Location.get(3).location4.location4NewsSites;
            Location4RssLinks = newsItems.News.get(1).Location.get(3).location4.location4RssLinks;
            location5NewsSites = newsItems.News.get(1).Location.get(4).location5.location5NewsSites;
            Location5RssLinks = newsItems.News.get(1).Location.get(4).location5.location5RssLinks;
            location6NewsSites = newsItems.News.get(1).Location.get(5).location6.location6NewsSites;
            Location6RssLinks = newsItems.News.get(1).Location.get(5).location6.location6RssLinks;
            location7NewsSites = newsItems.News.get(1).Location.get(6).location7.location7NewsSites;
            Location7RssLinks = newsItems.News.get(1).Location.get(6).location7.location7RssLinks;
            location8NewsSites = newsItems.News.get(1).Location.get(7).location8.location8NewsSites;
            Location8RssLinks = newsItems.News.get(1).Location.get(7).location8.location8RssLinks;
            location9NewsSites = newsItems.News.get(1).Location.get(8).location9.location9NewsSites;
            Location9RssLinks = newsItems.News.get(1).Location.get(8).location9.location9RssLinks;
            location10NewsSites = newsItems.News.get(1).Location.get(9).location10.location10NewsSites;
            Location10RssLinks = newsItems.News.get(1).Location.get(9).location10.location10RssLinks;
            location11NewsSites = newsItems.News.get(1).Location.get(10).location11.location11NewsSites;
            Location11RssLinks = newsItems.News.get(1).Location.get(10).location11.location11RssLinks;
            location12NewsSites = newsItems.News.get(1).Location.get(11).location12.location12NewsSites;
            Location12RssLinks = newsItems.News.get(1).Location.get(11).location12.location12RssLinks;
            location13NewsSites = newsItems.News.get(1).Location.get(12).location13.location13NewsSites;
            Location13RssLinks = newsItems.News.get(1).Location.get(12).location13.location13RssLinks;
            location14NewsSites = newsItems.News.get(1).Location.get(13).location14.location14NewsSites;
            Location14RssLinks = newsItems.News.get(1).Location.get(13).location14.location14RssLinks;
            location15NewsSites = newsItems.News.get(1).Location.get(14).location15.location15NewsSites;
            Location15RssLinks = newsItems.News.get(1).Location.get(14).location15.location15RssLinks;
            location16NewsSites = newsItems.News.get(1).Location.get(15).location16.location16NewsSites;
            Location16RssLinks = newsItems.News.get(1).Location.get(15).location16.location16RssLinks;
            location17NewsSites = newsItems.News.get(1).Location.get(16).location17.location17NewsSites;
            Location17RssLinks = newsItems.News.get(1).Location.get(16).location17.location17RssLinks;
            location18NewsSites = newsItems.News.get(1).Location.get(17).location18.location18NewsSites;
            Location18RssLinks = newsItems.News.get(1).Location.get(17).location18.location18RssLinks;
            location19NewsSites = newsItems.News.get(1).Location.get(18).location19.location19NewsSites;
            Location19RssLinks = newsItems.News.get(1).Location.get(18).location19.location19RssLinks;
            location20NewsSites = newsItems.News.get(1).Location.get(19).location20.location20NewsSites;
            Location20RssLinks = newsItems.News.get(1).Location.get(19).location20.location20RssLinks;
            location21NewsSites = newsItems.News.get(1).Location.get(20).location21.location21NewsSites;
            Location21RssLinks = newsItems.News.get(1).Location.get(20).location21.location21RssLinks;
            location22NewsSites = newsItems.News.get(1).Location.get(21).location22.location22NewsSites;
            Location22RssLinks = newsItems.News.get(1).Location.get(21).location22.location22RssLinks;
            location23NewsSites = newsItems.News.get(1).Location.get(22).location23.location23NewsSites;
            Location23RssLinks = newsItems.News.get(1).Location.get(22).location23.location23RssLinks;
            location24NewsSites = newsItems.News.get(1).Location.get(23).location24.location24NewsSites;
            Location24RssLinks = newsItems.News.get(1).Location.get(23).location24.location24RssLinks;
            location25NewsSites = newsItems.News.get(1).Location.get(24).location25.location25NewsSites;
            Location25RssLinks = newsItems.News.get(1).Location.get(24).location25.location25RssLinks;
            location26NewsSites = newsItems.News.get(1).Location.get(25).location26.location26NewsSites;
            Location26RssLinks = newsItems.News.get(1).Location.get(25).location26.location26RssLinks;
            location27NewsSites = newsItems.News.get(1).Location.get(26).location27.location27NewsSites;
            Location27RssLinks = newsItems.News.get(1).Location.get(26).location27.location27RssLinks;
            location28NewsSites = newsItems.News.get(1).Location.get(27).location28.location28NewsSites;
            Location28RssLinks = newsItems.News.get(1).Location.get(27).location28.location28RssLinks;
            location29NewsSites = newsItems.News.get(1).Location.get(28).location29.location29NewsSites;
            Location29RssLinks = newsItems.News.get(1).Location.get(28).location29.location29RssLinks;
            location30NewsSites = newsItems.News.get(1).Location.get(29).location30.location30NewsSites;
            Location30RssLinks = newsItems.News.get(1).Location.get(29).location30.location30RssLinks;


            socialMedia = newsItems.News.get(2).SocialMedia;
            savePrefsData();
            afterDataFetched(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sharedResponse(String key, String response) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = m.edit();
        editor.putString(key, response);
        editor.apply();
    }

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(MainHomeActivity.this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), MULTIPLE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permissions granted.
            } else {
                // no permissions granted.
            }
        }
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.apply();
    }

    private void afterDataFetched(Bundle savedInstanceState) {
        findViewById(R.id.drawer_layout).setVisibility(View.VISIBLE);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View ContentView = inflater.inflate(R.layout.news_activity_home, null, false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.addView(ContentView, 0);

//        Todo

        mEntriesFragment = (EntriesListFragment) getFragmentManager().findFragmentById(R.id.entries_list_fragment);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        FloatingActionMenu menu = findViewById(R.id.fab);
        menu.getMenuIconView().setImageResource(R.drawable.ic_add);
        FloatingActionButton fab = findViewById(R.id.fav_news);
        FloatingActionButton read_unread = findViewById(R.id.read_unread);

        String url1 = MainHomeActivity.recentRssLinks.get(i).rssLink1;
        String url2 = MainHomeActivity.recentRssLinks.get(i).rssLink2;
        String url3 = MainHomeActivity.recentRssLinks.get(i).rssLink3;
        String url4 = MainHomeActivity.recentRssLinks.get(i).rssLink4;
        String url5 = MainHomeActivity.recentRssLinks.get(i).rssLink5;
        String url6 = MainHomeActivity.recentRssLinks.get(i).rssLink6;
        String url7 = MainHomeActivity.recentRssLinks.get(i).rssLink7;
        String url8 = MainHomeActivity.recentRssLinks.get(i).rssLink8;
        String url9 = MainHomeActivity.recentRssLinks.get(i).rssLink9;
        String url10 = MainHomeActivity.recentRssLinks.get(i).rssLink10;
        String url11 = MainHomeActivity.recentRssLinks.get(i).rssLink11;
        String url12 = MainHomeActivity.recentRssLinks.get(i).rssLink12;
        String url13 = MainHomeActivity.recentRssLinks.get(i).rssLink13;
        String url14 = MainHomeActivity.recentRssLinks.get(i).rssLink14;
        String url15 = MainHomeActivity.recentRssLinks.get(i).rssLink15;
        String url16 = MainHomeActivity.recentRssLinks.get(i).rssLink16;
        String url17 = MainHomeActivity.recentRssLinks.get(i).rssLink17;
        String url18 = MainHomeActivity.recentRssLinks.get(i).rssLink18;
        String url19 = MainHomeActivity.recentRssLinks.get(i).rssLink19;
        String url20 = MainHomeActivity.recentRssLinks.get(i).rssLink20;

        if (PrefUtils.getBoolean(PrefUtils.FIRST_OPEN + "a", true)) {
            PrefUtils.putBoolean(PrefUtils.FIRST_OPEN + "a", false);
            add(url1);
            add(url2);
            add(url3);
            add(url4);
            add(url5);
            add(url6);
            add(url7);
            add(url8);
            add(url9);
            add(url10);
            add(url11);
            add(url12);
            add(url13);
            add(url14);
            add(url15);
            add(url16);
            add(url17);
            add(url18);
            add(url19);
            add(url20);
        } else {
            update(url1, "1");
            update(url2, "2");
            update(url3, "3");
            update(url4, "4");
            update(url5, "5");
            update(url6, "6");
            update(url7, "7");
            update(url8, "8");
            update(url9, "9");
            update(url10, "10");
            update(url11, "11");
            update(url12, "12");
            update(url13, "13");
            update(url14, "14");
            update(url15, "15");
            update(url16, "16");
            update(url17, "17");
            update(url18, "18");
            update(url19, "19");
            update(url20, "20");
        }

        fab.setColorNormalResId(R.color.colorPrimary);
        fab.setColorPressedResId(R.color.h_dark_red);

        read_unread.setColorNormalResId(R.color.colorPrimary);
        read_unread.setColorPressedResId(R.color.h_dark_red);

        fab.setImageResource(R.drawable.ic_heart_empty);

        if (!PrefUtils.getBoolean(PrefUtils.SHOW_READ, true)) {
            read_unread.setImageResource(R.drawable.nav_read);
        } else {
            read_unread.setImageResource(R.drawable.nav_unread);
        }

        read_unread.setOnClickListener(v -> {
            if (!PrefUtils.getBoolean(PrefUtils.SHOW_READ, true)) {
                PrefUtils.putBoolean(PrefUtils.SHOW_READ, true);
                read_unread.setImageResource(R.drawable.nav_unread);
                Toast.makeText(this, this.getResources().getString(R.string.all_news), Toast.LENGTH_LONG).show();
            } else {
                PrefUtils.putBoolean(PrefUtils.SHOW_READ, false);
                read_unread.setImageResource(R.drawable.nav_read);
                Toast.makeText(this, this.getResources().getString(R.string.unread_only), Toast.LENGTH_LONG).show();
            }
        });

        fab.setOnClickListener(v -> {
            if (mCurrentDrawerPos == 1) {
                fab.setImageResource(R.drawable.ic_heart_empty);
                Toast.makeText(this, this.getResources().getString(R.string.all_news), Toast.LENGTH_LONG).show();
                selectDrawerItem(0);
            } else {
                fab.setImageResource(R.drawable.ic_heart_filled);
                Toast.makeText(this, this.getResources().getString(R.string.fav_news), Toast.LENGTH_LONG).show();
                selectDrawerItem(1);
            }
        });

        if (savedInstanceState != null) {
            mCurrentDrawerPos = savedInstanceState.getInt(STATE_CURRENT_DRAWER_POS);
        }
        getLoaderManager().initLoader(LOADER_ID, null, this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean isNewsfirst = sharedPreferences.getBoolean("isNewsfirst", true);
            if (isNewsfirst) {
                startService(new Intent(MainHomeActivity.this, FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isNewsfirst", false);
                editor.apply();
            }
            if (PrefUtils.getBoolean(PrefUtils.REFRESH_ENABLED, true)) {
                JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.cancel(1001);
                Custom_JobSheduler.scheduleNewsJob(this);
            } else {
                JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                jobScheduler.cancel(1001);
            }
        } else {
            if (PrefUtils.getBoolean(PrefUtils.REFRESH_ENABLED, true)) {
                // starts the service independent to this activity
                startService(new Intent(this, RefreshService.class));
            } else {
                stopService(new Intent(this, RefreshService.class));
            }
        }


        if (PrefUtils.getBoolean(PrefUtils.REFRESH_ON_OPEN_ENABLED, false)) {
            if (!PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
                startService(new Intent(MainHomeActivity.this, FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS));
            }
        }

    }

    private void add(String url) {
        FeedDataContentProvider.addFeed(this, url, demo, false);
    }

    private void update(String url, String position) {
        ContentResolver cr = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FeedData.FeedColumns.URL, url);
        values.put(FeedData.FeedColumns.NAME, demo);
        values.put(FeedData.FeedColumns.RETRIEVE_FULLTEXT, false);
        values.put(FeedData.FeedColumns.PRIORITY, position);
        cr.update(FeedData.FeedColumns.CONTENT_URI(position), values, null, null);
    }

    private void selectDrawerItem(int position) {
        mCurrentDrawerPos = position;
        Uri newUri;
        switch (position) {
            case 1:
                newUri = EntryColumns.FAVORITES_CONTENT_URI;
                break;
            case 0:
            default:
                newUri = EntryColumns.ALL_ENTRIES_CONTENT_URI;
                break;
        }

        if (!newUri.equals(mEntriesFragment.getUri())) {
            boolean showFeedInfo = true;
            mEntriesFragment.setData(newUri, true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_CURRENT_DRAWER_POS, mCurrentDrawerPos);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PrefUtils.registerOnPrefChangeListener(mShowReadListener);
    }

    @Override
    protected void onPause() {
        PrefUtils.unregisterOnPrefChangeListener(mShowReadListener);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // We reset the current drawer position
        selectDrawerItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(this,
                FeedColumns.GROUPED_FEEDS_CONTENT_URI,
                new String[]{FeedColumns._ID, FeedColumns.URL, FeedColumns.NAME,
                        FeedColumns.IS_GROUP, FeedColumns.ICON, FeedColumns.LAST_UPDATE,
                        FeedColumns.ERROR, FEED_UNREAD_NUMBER,
                        FeedColumns.IS_GROUP_EXPANDED},
                FeedColumns.IS_GROUP + Constants.DB_IS_TRUE + Constants.DB_OR +
                        FeedColumns.GROUP_ID + Constants.DB_IS_NULL + Constants.DB_OR +
                        FeedColumns.GROUP_ID + " IN (SELECT " + FeedColumns._ID +
                        " FROM " + FeedColumns.TABLE_NAME +
                        " WHERE " + FeedColumns.IS_GROUP_EXPANDED + Constants.DB_IS_TRUE + ")",
                null,
                null
        );
        cursorLoader.setUpdateThrottle(Constants.UPDATE_THROTTLE_DELAY);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        selectDrawerItem(mCurrentDrawerPos);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            PrefUtils.putBoolean(PrefUtils.IS_REFRESHING, false);
            moveTaskToBack(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            NotificationChannel channel = new NotificationChannel(
                    com.newstoday.screenfilter.Constants.NOTIFICATION_CHANNEL_ID_RS,
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
}
