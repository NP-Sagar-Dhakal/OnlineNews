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

package com.newstoday.news_package.news_category.ten.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.newstoday.Constants;
import com.newstoday.nepali.news.R;
import com.newstoday.news_package.news_category.ten.fragment.EntryFragment;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrPosition;

import java.util.Objects;


public class EntryActivity extends AppCompatActivity {

    private EntryFragment mEntryFragment;
    public static AdLoader adLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.pull_in_right, R.anim.holder);

        setContentView(R.layout.cat_ten_activity_entry);


        adLoader = new AdLoader.Builder(EntryActivity.this, this.getString(R.string.native_ad))
                .forNativeAd(unifiedNativeAd -> {
                    FrameLayout frameLayout =
                            findViewById(R.id.adFrame);
                    try {
                        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        NativeAdView adView = (NativeAdView) Objects.requireNonNull(inflater).inflate(R.layout.aa_radio_native, null);
                        populateUnifiedNativeAdView(unifiedNativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

        SlidrConfig config = new SlidrConfig.Builder()
                .position(SlidrPosition.LEFT)
                .sensitivity(1f)
                .scrimColor(Color.BLACK)
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
            Intent intent = getIntent();

            Uri intents = intent.getData();
            mEntryFragment.setData(Objects.requireNonNull(intents));
        }

        Toolbar toolbar = findViewById(R.id.entry_toolbar);
        setSupportActionBar(toolbar);
        //SupportActionBar may produce a null pointer
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        //Called onBackPressed at OnClickListener to apply overridePendingTransition
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
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


    private void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView(adView.findViewById(R.id.native_ad_media_view));
        adView.setHeadlineView(adView.findViewById(R.id.native_ad_headline));
        adView.setCallToActionView(adView.findViewById(R.id.native_ad_call_to_action_button));
        adView.setBodyView(adView.findViewById(R.id.native_ad_body));
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }
        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        if (nativeAd.getHeadline() == null) {
            adView.getHeadlineView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
            adView.getHeadlineView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);
    }
}