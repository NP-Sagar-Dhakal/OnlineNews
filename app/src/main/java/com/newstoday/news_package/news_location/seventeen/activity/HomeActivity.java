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

package com.newstoday.news_package.news_location.seventeen.activity;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.newstoday.Constants;
import com.newstoday.nepali.news.R;
import com.newstoday.news_package.news_location.seventeen.fragment.EntriesListFragment;
import com.newstoday.news_package.news_location.seventeen.provider.FeedData;
import com.newstoday.news_package.news_location.seventeen.provider.FeedData.EntryColumns;
import com.newstoday.news_package.news_location.seventeen.provider.FeedData.FeedColumns;
import com.newstoday.news_package.news_location.seventeen.provider.FeedDataContentProvider;
import com.newstoday.news_package.news_location.seventeen.utils.PrefUtils;
import com.newstoday.news_package.recent_news.activity.MainHomeActivity;


public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String STATE_CURRENT_DRAWER_POS = "STATE_CURRENT_DRAWER_POS";
    private static final String FEED_UNREAD_NUMBER = "(SELECT " + Constants.DB_COUNT + " FROM " + EntryColumns.TABLE_NAME + " WHERE " +
            EntryColumns.IS_READ + " IS NULL AND " + EntryColumns.FEED_ID + '=' + FeedColumns.TABLE_NAME + '.' + FeedData.FeedColumns._ID + ')';

    private static final int LOADER_ID = 0;
    private final SharedPreferences.OnSharedPreferenceChangeListener mShowReadListener = (sharedPreferences, key) -> {
        if (PrefUtils.SHOW_READ.equals(key)) {
            getLoaderManager().restartLoader(LOADER_ID, null, HomeActivity.this);

        }
    };
    private final String demo = "News Today";
    private EntriesListFragment mEntriesFragment;
    private int mCurrentDrawerPos;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_seventeen_view_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mEntriesFragment = (EntriesListFragment) getFragmentManager().findFragmentById(R.id.entries_list_fragment);
        Toolbar toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);
        String name = getIntent().getStringExtra("name");
        TextView navigationTitle = findViewById(R.id.navigationTitle);
        navigationTitle.setText(name);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionMenu menu = findViewById(R.id.fab);
        menu.getMenuIconView().setImageResource(R.drawable.ic_add);
        FloatingActionButton fab = findViewById(R.id.fav_news);

        String url1 = MainHomeActivity.Location17RssLinks.get(i).rssLink1;
        String url2 = MainHomeActivity.Location17RssLinks.get(i).rssLink2;
        String url3 = MainHomeActivity.Location17RssLinks.get(i).rssLink3;
        String url4 = MainHomeActivity.Location17RssLinks.get(i).rssLink4;
        String url5 = MainHomeActivity.Location17RssLinks.get(i).rssLink5;
        String url6 = MainHomeActivity.Location17RssLinks.get(i).rssLink6;
        String url7 = MainHomeActivity.Location17RssLinks.get(i).rssLink7;
        String url8 = MainHomeActivity.Location17RssLinks.get(i).rssLink8;
        String url9 = MainHomeActivity.Location17RssLinks.get(i).rssLink9;
        String url10 = MainHomeActivity.Location17RssLinks.get(i).rssLink10;
        String url11 = MainHomeActivity.Location17RssLinks.get(i).rssLink11;
        String url12 = MainHomeActivity.Location17RssLinks.get(i).rssLink12;
        String url13 = MainHomeActivity.Location17RssLinks.get(i).rssLink13;
        String url14 = MainHomeActivity.Location17RssLinks.get(i).rssLink14;
        String url15 = MainHomeActivity.Location17RssLinks.get(i).rssLink15;

        if (PrefUtils.getBoolean(PrefUtils.FIRST_OPEN, true)) {
            PrefUtils.putBoolean(PrefUtils.FIRST_OPEN, false);
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
        }

        fab.setColorNormalResId(R.color.colorPrimary);
        fab.setColorPressedResId(R.color.h_dark_red);

        fab.setImageResource(R.drawable.ic_heart_filled);
        fab.setColorNormalResId(R.color.colorPrimary);
        fab.setColorPressedResId(R.color.h_dark_red);


        fab.setImageResource(R.drawable.ic_heart_empty);

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
    }

    private void add(String url) {
        FeedDataContentProvider.addFeed(this, url, demo, false);
    }

    private void update(String url, String position) {
        ContentResolver cr = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(FeedColumns.URL, url);
        values.put(FeedColumns.NAME, position + demo);
        values.put(FeedColumns.RETRIEVE_FULLTEXT, false);
        values.put(FeedColumns.PRIORITY, position);
        try {
            cr.update(FeedColumns.CONTENT_URI(position), values, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        selectDrawerItem(1);
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
        PrefUtils.putBoolean(PrefUtils.IS_REFRESHING, false);
        super.onBackPressed();
    }
}
