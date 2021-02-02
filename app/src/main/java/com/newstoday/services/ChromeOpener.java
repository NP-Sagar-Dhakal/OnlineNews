package com.newstoday.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.newstoday.R;

public class ChromeOpener {
    public void openLink(Context context, @NonNull String url) {
        Activity activity = (Activity) context;
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
        if (slideAD >= 10) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, context.getResources().getString(R.string.interstitial_ad), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    interstitialAd.show(activity);
                    SlideAd_Service.putWEBSITE_CLICK(context, 0);
                    super.onAdLoaded(interstitialAd);
                }
            });
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
