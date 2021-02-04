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

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SlideAd_Service {
    public static final String SLIDE_COUNT = "SLIDE_COUNT";
    public static final String BUTTON_CLICK = "BUTTON_CLICK";
    public static final String WEBSITE_CLICK = "WEBSITE_CLICK";
    public static final String NEWS_CLICK = "NEWS_CLICK";

    public static void putSLIDE_AD(Context context, int value) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putInt(SLIDE_COUNT, value);
        editor.apply();
    }

    public static void putBUTTON_CLICK(Context context, int value) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putInt(BUTTON_CLICK, value);
        editor.apply();
    }

    public static void putWEBSITE_CLICK(Context context, int value) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putInt(WEBSITE_CLICK, value);
        editor.apply();
    }

    public static void putNEWS_CLICK(Context context, int value) {
        SharedPreferences m = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = m.edit();
        editor.putInt(NEWS_CLICK, value);
        editor.apply();
    }

    public static int getAdCount(Context context, String key, int defValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(key, defValue);
    }

}
