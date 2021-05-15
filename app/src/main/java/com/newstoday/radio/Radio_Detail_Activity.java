package com.newstoday.radio;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.firebase.database.annotations.NotNull;
import com.newstoday.nepali.news.R;
import com.newstoday.services.SlideAd_Service;

import java.util.Objects;

public class Radio_Detail_Activity extends AppCompatActivity implements Next_Prev_Callback {
    private ViewPager pager;
    public static AdLoader adLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radio_detail);

        MobileAds.initialize(Radio_Detail_Activity.this, initializationStatus -> {
        });

        adLoader = new AdLoader.Builder(Radio_Detail_Activity.this, this.getString(R.string.native_ad))
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
                    public void onAdFailedToLoad(@NotNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();

        try {
            adLoader.loadAd(new AdRequest.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                if (String.valueOf(position).endsWith("3") || String.valueOf(position).endsWith("6") || String.valueOf(position).endsWith("9")) {
                    try {
                        adLoader.loadAd(new AdRequest.Builder().build());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Radio_Detail_Activity.this);
                SlideAd_Service.putSLIDE_AD(Radio_Detail_Activity.this, sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0) + 1);
                int slideCount = sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0);
                if (slideCount >= 25) {
                    if (slideCount <= 25 || slideCount >= 32) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        InterstitialAd.load(Radio_Detail_Activity.this, Radio_Detail_Activity.this.getResources().getString(R.string.interstitial_ad), adRequest, new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                interstitialAd.show(Radio_Detail_Activity.this);
                                if (slideCount > 50) {
                                    SlideAd_Service.putSLIDE_AD(Radio_Detail_Activity.this, 0);
                                } else {
                                    SlideAd_Service.putSLIDE_AD(Radio_Detail_Activity.this, slideCount - 25);
                                }
                                super.onAdLoaded(interstitialAd);
                            }
                        });
                    }
                }
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
