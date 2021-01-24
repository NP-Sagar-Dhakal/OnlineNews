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
  <p/>
  <p/>
  Some parts of this software are based on "Sparse rss" under the MIT license (see
  below). Please refers to the original project to identify which parts are under the
  MIT license.
  <p/>
  Copyright (c) 2010-2012 Stefan Handschuh
  <p/>
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  <p/>
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  <p/>
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
 */

package com.newstoday.news_package.recent_news.fragment;

import android.app.Activity;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.newstoday.R;
import com.newstoday.activities.Splash_Activity;
import com.newstoday.news_package.recent_news.service.RefreshService;
import com.newstoday.news_package.recent_news.utils.PrefUtils;
import com.newstoday.services.Custom_JobSheduler;
import com.newstoday.services.SetAppLanguage;

public class GeneralPrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_preferences);

        Preference preference = findPreference(PrefUtils.REFRESH_ENABLED);
        preference.setOnPreferenceChangeListener((preference1, newValue) -> {
            Activity activity = getActivity();
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Boolean.TRUE.equals(newValue)) {
                        JobScheduler jobScheduler = (JobScheduler) activity.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        jobScheduler.cancel(1001);
                        Custom_JobSheduler.scheduleNewsJob(activity);
                    } else {
                        PrefUtils.putLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
                        JobScheduler jobScheduler = (JobScheduler) activity.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                        jobScheduler.cancel(1001);
                    }
                } else {
                    if (Boolean.TRUE.equals(newValue)) {
                        activity.startService(new Intent(activity, RefreshService.class));
                    } else {
                        PrefUtils.putLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
                        activity.stopService(new Intent(activity, RefreshService.class));
                    }
                }
            }
            return true;
        });

        Preference langPreference = findPreference(PrefUtils.LANGUAGE);
        langPreference.setOnPreferenceChangeListener((preference1, newValue) -> {
            SetAppLanguage.setApplicationLocale(getActivity(), newValue.toString());
            startActivity(new Intent(getActivity(), Splash_Activity.class));
            return true;
        });
    }
}
