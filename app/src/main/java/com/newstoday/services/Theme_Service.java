package com.newstoday.services;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

public class Theme_Service {
    public static void changeTheme(Context context) {
        int isNight = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_YES;
        if (isNight == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
