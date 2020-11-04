package com.newstoday.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.newstoday.R;

public class ChromeOpener {
    public void openLink(Context context, @NonNull String url) {
        PackageManager pm = context.getPackageManager();

        boolean installed = false;
        try {
            pm.getPackageInfo("com.android.chrome", PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int slideAD = sharedPreferences.getInt("WEBSITE_CLICK", 0) + 1;
        SlideAd_Service.putWEBSITE_CLICK(context, slideAD);
        if (slideAD >= 15) {
            InterstitialAd mInterstitialAd = new InterstitialAd(context);
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                SlideAd_Service.putWEBSITE_CLICK(context, 0);
            } else {
                SlideAd_Service.putWEBSITE_CLICK(context, slideAD);
                mInterstitialAd = new InterstitialAd(context);
                mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.intrestial_ad));
                mInterstitialAd.loadAd(new AdRequest.Builder().addKeyword("Insurance").build());
                mInterstitialAd.show();
            }
        }

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(Color.WHITE);
        builder.setShowTitle(true);
        CustomTabsIntent customTabsIntent = builder.build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            customTabsIntent.intent.putExtra(Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://cherrydigital//" + context.getPackageName()));
        }
        if (installed) {
            customTabsIntent.intent.setPackage("com.android.chrome");
            customTabsIntent.launchUrl(context, Uri.parse(url));
        } else {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }
    }
}