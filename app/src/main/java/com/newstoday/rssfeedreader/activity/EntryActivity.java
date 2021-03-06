/*
  spaRSS
  <p/>
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

package com.newstoday.rssfeedreader.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;

import com.newstoday.nepali.news.R;
import com.newstoday.rssfeedreader.Constants;
import com.newstoday.rssfeedreader.fragment.EntryFragment;
import com.newstoday.rssfeedreader.utils.PrefUtils;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.util.Objects;

public class EntryActivity extends BaseActivity {

    private EntryFragment mEntryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.pull_in_right, R.anim.holder);
        setContentView(R.layout.feed_activity_entry);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .sensitivity(1f)
                .scrimColor(getResources().getColor(R.color.h_dark_red))
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.25f)
                .edge(true)
                .edgeSize(0.8f)
                .build();

        Slidr.attach(this, config);

        mEntryFragment = (EntryFragment) getFragmentManager().findFragmentById(R.id.entry_fragment);
        if (savedInstanceState == null) { // Put the data only the first time (the fragment will save its state)
            mEntryFragment.setData(Objects.requireNonNull(getIntent().getData()));
        }

        Toolbar toolbar = findViewById(R.id.feed_entry_toolbar);
        setSupportActionBar(toolbar);
        //SupportActionBar may produce a null pointer
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        //Called onBackPressed at OnClickListener to apply overridePendingTransition
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        if (PrefUtils.getBoolean(PrefUtils.DISPLAY_ENTRIES_FULLSCREEN, false)) {
            setImmersiveFullScreen(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Bundle b = getIntent().getExtras();
            if (b != null && b.getBoolean(Constants.INTENT_FROM_WIDGET, false)) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.holder, R.anim.pull_in_left);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mEntryFragment.setData(Objects.requireNonNull(intent.getData()));
    }
}