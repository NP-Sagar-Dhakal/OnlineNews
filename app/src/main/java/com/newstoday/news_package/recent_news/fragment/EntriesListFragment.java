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

package com.newstoday.news_package.recent_news.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.snackbar.Snackbar;
import com.newstoday.Constants;
import com.newstoday.MainApplication;
import com.newstoday.R;
import com.newstoday.activities.About_Developer;
import com.newstoday.news_package.recent_news.activity.GeneralPrefsActivity;
import com.newstoday.news_package.recent_news.adapter.EntriesCursorAdapter;
import com.newstoday.news_package.recent_news.provider.FeedData;
import com.newstoday.news_package.recent_news.provider.FeedDataContentProvider;
import com.newstoday.news_package.recent_news.service.FetcherService;
import com.newstoday.news_package.recent_news.utils.PrefUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.newstoday.news_package.recent_news.provider.FeedData.EntryColumns;
import com.newstoday.news_package.recent_news.utils.UiUtils;
import com.newstoday.recyclerview.News_Sites_Adapter;
import com.newstoday.services.ChromeOpener;
import com.newstoday.services.Disclaimer_Dialog;
import com.newstoday.services.SlideAd_Service;

import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category10Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category10NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category11Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category11NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category12Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category12NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category13Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category13NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category14Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category14NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category15Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category15NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category1Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category1NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category2Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category2NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category3Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category3NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category4Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category4NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category5Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category5NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category6Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category6NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category7Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category7NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category8Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category8NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category9Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category9NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location10Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location10NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location11Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location11NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location12Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location12NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location13Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location13NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location14Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location14NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location15Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location15NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location16Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location16NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location17Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location17NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location18Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location18NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location19Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location19NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location1Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location1NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location20Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location20NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location21Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location21NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location22Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location22NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location23Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location23NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location24Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location24NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location25Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location25NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location26Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location26NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location27Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location27NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location28Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location28NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location29Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location29NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location2Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location2NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location30Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location30NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location3Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location3NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location4Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location4NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location5Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location5NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location6Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location6NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location7Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location7NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location8Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location8NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location9Name;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location9NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.topNewsSites;


public class EntriesListFragment extends SwipeRefreshListFragment implements ViewTreeObserver.OnScrollChangedListener {
    private static final String STATE_CURRENT_URI = "STATE_CURRENT_URI";
    private static final String STATE_ORIGINAL_URI = "STATE_ORIGINAL_URI";
    private static final String STATE_SHOW_FEED_INFO = "STATE_SHOW_FEED_INFO";
    private static final String STATE_LIST_DISPLAY_DATE = "STATE_LIST_DISPLAY_DATE";

    private static final int ENTRIES_LOADER_ID = 1;
    private static final int NEW_ENTRIES_NUMBER_LOADER_ID = 2;

    private View header;


    private final OnSharedPreferenceChangeListener mPrefListener = new OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (PrefUtils.SHOW_READ.equals(key)) {
                getLoaderManager().restartLoader(ENTRIES_LOADER_ID, null, mEntriesLoader);
            } else if (PrefUtils.IS_REFRESHING.equals(key)) {
                refreshSwipeProgress();
            }
        }
    };
    private Cursor mJustMarkedAsReadEntries;
    private Button mRefreshListBtn;
    private Uri mUri, mOriginalUri;
    private boolean mShowFeedInfo = false;
    private EntriesCursorAdapter mEntriesCursorAdapter;
    private ListView mListView;
    private long mListDisplayDate = new Date().getTime();
    private final LoaderManager.LoaderCallbacks<Cursor> mEntriesLoader = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String entriesOrder = PrefUtils.getBoolean(PrefUtils.DISPLAY_OLDEST_FIRST, false) ? Constants.DB_ASC : Constants.DB_DESC;
            String where = "(" + EntryColumns.FETCH_DATE + Constants.DB_IS_NULL + Constants.DB_OR + EntryColumns.FETCH_DATE + "<=" + mListDisplayDate + ')';
            if (!FeedData.shouldShowReadEntries(mUri)) {
                where += Constants.DB_AND + EntryColumns.WHERE_UNREAD;
            }
            CursorLoader cursorLoader = new CursorLoader(getActivity(), mUri, null, where, null, EntryColumns.DATE + entriesOrder);
            cursorLoader.setUpdateThrottle(150);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mEntriesCursorAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mEntriesCursorAdapter.swapCursor(Constants.EMPTY_CURSOR);
        }
    };
    private FragmentActivity myContext;
    private int mNewEntriesNumber, mOldUnreadEntriesNumber = -1;
    private boolean mAutoRefreshDisplayDate = false;
    private final LoaderManager.LoaderCallbacks<Cursor> mEntriesNumberLoader = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader cursorLoader = new CursorLoader(getActivity(), mUri, new String[]{"SUM(" + EntryColumns.FETCH_DATE + '>' + mListDisplayDate + ")", "SUM(" + EntryColumns.FETCH_DATE + "<=" + mListDisplayDate + Constants.DB_AND + EntryColumns.WHERE_UNREAD + ")"}, null, null, null);
            cursorLoader.setUpdateThrottle(150);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            data.moveToFirst();
            mNewEntriesNumber = data.getInt(0);
            mOldUnreadEntriesNumber = data.getInt(1);

            if (mAutoRefreshDisplayDate && mNewEntriesNumber != 0 && mOldUnreadEntriesNumber == 0) {
                mListDisplayDate = new Date().getTime();
                restartLoaders();
            } else {
                refreshUI();
            }
            mAutoRefreshDisplayDate = false;
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mUri = savedInstanceState.getParcelable(STATE_CURRENT_URI);
            mOriginalUri = savedInstanceState.getParcelable(STATE_ORIGINAL_URI);
            mShowFeedInfo = savedInstanceState.getBoolean(STATE_SHOW_FEED_INFO);
            mListDisplayDate = savedInstanceState.getLong(STATE_LIST_DISPLAY_DATE);

            mEntriesCursorAdapter = new EntriesCursorAdapter(getActivity(), Constants.EMPTY_CURSOR);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mListView.getViewTreeObserver().addOnScrollChangedListener(this);
        refreshSwipeProgress();
        PrefUtils.registerOnPrefChangeListener(mPrefListener);

        if (mUri != null) {
            // If the list is empty when we are going back here, try with the last display date
            if (mNewEntriesNumber != 0 && mOldUnreadEntriesNumber == 0) {
                mListDisplayDate = new Date().getTime();
            } else {
                mAutoRefreshDisplayDate = true; // We will try to update the list after if necessary
            }
            restartLoaders();
        }
    }

    @Override
    public void onScrollChanged() {

    }

    private void addHeaderView() {
        header = getActivity().getLayoutInflater().inflate(R.layout.aa_home_fragment, null);
        RecyclerView newsSitesRecycler = header.findViewById(R.id.categoryRecyclerView);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        newsSitesRecycler.setLayoutManager(layoutManager1);
        News_Sites_Adapter adapter = new News_Sites_Adapter(getActivity(), "topNewsSites", topNewsSites, category1NewsSites, category2NewsSites, category3NewsSites, category4NewsSites, category5NewsSites, category6NewsSites, category7NewsSites, category8NewsSites, category9NewsSites, category10NewsSites, category11NewsSites, category12NewsSites, category13NewsSites, category14NewsSites, category15NewsSites, location1NewsSites, location2NewsSites, location3NewsSites, location4NewsSites, location5NewsSites, location6NewsSites, location7NewsSites, location8NewsSites, location9NewsSites, location10NewsSites, location11NewsSites, location12NewsSites, location13NewsSites, location14NewsSites, location15NewsSites, location16NewsSites, location17NewsSites, location18NewsSites, location19NewsSites, location20NewsSites, location21NewsSites, location22NewsSites, location23NewsSites, location24NewsSites, location25NewsSites, location26NewsSites, location27NewsSites, location28NewsSites, location29NewsSites, location30NewsSites);
        newsSitesRecycler.setAdapter(adapter);

        HorizontalScrollView second_hor = header.findViewById(R.id.second_hor);
        ConstraintLayout full_location = header.findViewById(R.id.full_location);
        ConstraintLayout buttons_categories = header.findViewById(R.id.buttons_categories);

        if (category1Name.equals("null")) {
            buttons_categories.setVisibility(View.GONE);
        }

        if (location1Name.equals("null")) {
            full_location.setVisibility(View.GONE);
        }
        if (location16Name.equals("null")) {
            second_hor.setVisibility(View.GONE);
        }

        CardView one = header.findViewById(R.id.one);
        CardView two = header.findViewById(R.id.two);
        CardView three = header.findViewById(R.id.three);
        CardView four = header.findViewById(R.id.four);
        CardView five = header.findViewById(R.id.five);
        CardView six = header.findViewById(R.id.six);
        CardView seven = header.findViewById(R.id.seven);
        CardView eight = header.findViewById(R.id.eight);
        CardView nine = header.findViewById(R.id.nine);
        CardView ten = header.findViewById(R.id.ten);
        CardView eleven = header.findViewById(R.id.eleven);
        CardView twelve = header.findViewById(R.id.twelve);
        CardView thirteen = header.findViewById(R.id.thirteen);
        CardView fourteen = header.findViewById(R.id.fourteen);
        CardView fifthteen = header.findViewById(R.id.fifthteen);
        CardView sixthteen = header.findViewById(R.id.sixthteen);
        CardView seventeen = header.findViewById(R.id.seventeen);
        CardView eighteen = header.findViewById(R.id.eighteen);
        CardView nineteen = header.findViewById(R.id.nineteen);
        CardView twenty = header.findViewById(R.id.twenty);
        CardView twentyone = header.findViewById(R.id.twentyone);
        CardView twentytwo = header.findViewById(R.id.twentytwo);
        CardView twentythree = header.findViewById(R.id.twentythree);
        CardView twentyfour = header.findViewById(R.id.twentyfour);
        CardView twentyfive = header.findViewById(R.id.twentyfive);
        CardView twentysix = header.findViewById(R.id.twentysix);
        CardView twentyseven = header.findViewById(R.id.twentyseven);
        CardView twentyeight = header.findViewById(R.id.twentyeight);
        CardView twentynine = header.findViewById(R.id.twentynine);
        CardView thirty = header.findViewById(R.id.thirty);


        CardView cat_one = header.findViewById(R.id.cat_one);
        CardView cat_two = header.findViewById(R.id.cat_two);
        CardView cat_three = header.findViewById(R.id.cat_three);
        CardView cat_four = header.findViewById(R.id.cat_four);
        CardView cat_five = header.findViewById(R.id.cat_five);
        CardView cat_six = header.findViewById(R.id.cat_six);
        CardView cat_seven = header.findViewById(R.id.cat_seven);
        CardView cat_eight = header.findViewById(R.id.cat_eight);
        CardView cat_nine = header.findViewById(R.id.cat_nine);
        CardView cat_ten = header.findViewById(R.id.cat_ten);
        CardView cat_eleven = header.findViewById(R.id.cat_eleven);
        CardView cat_twelve = header.findViewById(R.id.cat_twelve);
        CardView cat_thirteen = header.findViewById(R.id.cat_thirteen);
        CardView cat_fourteen = header.findViewById(R.id.cat_fourteen);
        CardView cat_fifthteen = header.findViewById(R.id.cat_fifthteen);

        TextView t_one = header.findViewById(R.id.t_one);
        TextView t_two = header.findViewById(R.id.t_two);
        TextView t_three = header.findViewById(R.id.t_three);
        TextView t_four = header.findViewById(R.id.t_four);
        TextView t_five = header.findViewById(R.id.t_five);
        TextView t_six = header.findViewById(R.id.t_six);
        TextView t_seven = header.findViewById(R.id.t_seven);
        TextView t_eight = header.findViewById(R.id.t_eight);
        TextView t_nine = header.findViewById(R.id.t_nine);
        TextView t_ten = header.findViewById(R.id.t_ten);
        TextView t_eleven = header.findViewById(R.id.t_eleven);
        TextView t_twelve = header.findViewById(R.id.t_twelve);
        TextView t_thirteen = header.findViewById(R.id.t_thirteen);
        TextView t_fourteen = header.findViewById(R.id.t_fourteen);
        TextView t_fifthteen = header.findViewById(R.id.t_fifthteen);
        TextView t_sixthteen = header.findViewById(R.id.t_sixthteen);
        TextView t_seventeen = header.findViewById(R.id.t_seventeen);
        TextView t_eighteen = header.findViewById(R.id.t_eighteen);
        TextView t_nineteen = header.findViewById(R.id.t_nineteen);
        TextView t_twenty = header.findViewById(R.id.t_twenty);
        TextView t_twentyone = header.findViewById(R.id.t_twentyone);
        TextView t_twentytwo = header.findViewById(R.id.t_twentytwo);
        TextView t_twentythree = header.findViewById(R.id.t_twentythree);
        TextView t_twentyfour = header.findViewById(R.id.t_twentyfour);
        TextView t_twentyfive = header.findViewById(R.id.t_twentyfive);
        TextView t_twentysix = header.findViewById(R.id.t_twentysix);
        TextView t_twentyseven = header.findViewById(R.id.t_twentyseven);
        TextView t_twentyeight = header.findViewById(R.id.t_twentyeight);
        TextView t_twentynine = header.findViewById(R.id.t_twentynine);
        TextView t_thirty = header.findViewById(R.id.t_thirty);


        TextView t_cat_one = header.findViewById(R.id.t_cat_one);
        TextView t_cat_two = header.findViewById(R.id.t_cat_two);
        TextView t_cat_three = header.findViewById(R.id.t_cat_three);
        TextView t_cat_four = header.findViewById(R.id.t_cat_four);
        TextView t_cat_five = header.findViewById(R.id.t_cat_five);
        TextView t_cat_six = header.findViewById(R.id.t_cat_six);
        TextView t_cat_seven = header.findViewById(R.id.t_cat_seven);
        TextView t_cat_eight = header.findViewById(R.id.t_cat_eight);
        TextView t_cat_nine = header.findViewById(R.id.t_cat_nine);
        TextView t_cat_ten = header.findViewById(R.id.t_cat_ten);
        TextView t_cat_eleven = header.findViewById(R.id.t_cat_eleven);
        TextView t_cat_twelve = header.findViewById(R.id.t_cat_twelve);
        TextView t_cat_thirteen = header.findViewById(R.id.t_cat_thirteen);
        TextView t_cat_fourteen = header.findViewById(R.id.t_cat_fourteen);
        TextView t_cat_fifthteen = header.findViewById(R.id.t_cat_fifthteen);

        hideButton(location1Name, one, t_one);
        hideButton(location2Name, two, t_two);
        hideButton(location3Name, three, t_three);
        hideButton(location4Name, four, t_four);
        hideButton(location5Name, five, t_five);
        hideButton(location6Name, six, t_six);
        hideButton(location7Name, seven, t_seven);
        hideButton(location8Name, eight, t_eight);
        hideButton(location9Name, nine, t_nine);
        hideButton(location10Name, ten, t_ten);
        hideButton(location11Name, eleven, t_eleven);
        hideButton(location12Name, twelve, t_twelve);
        hideButton(location13Name, thirteen, t_thirteen);
        hideButton(location14Name, fourteen, t_fourteen);
        hideButton(location15Name, fifthteen, t_fifthteen);
        hideButton(location16Name, sixthteen, t_sixthteen);
        hideButton(location17Name, seventeen, t_seventeen);
        hideButton(location18Name, eighteen, t_eighteen);
        hideButton(location19Name, nineteen, t_nineteen);
        hideButton(location20Name, twenty, t_twenty);
        hideButton(location21Name, twentyone, t_twentyone);
        hideButton(location22Name, twentytwo, t_twentytwo);
        hideButton(location23Name, twentythree, t_twentythree);
        hideButton(location24Name, twentyfour, t_twentyfour);
        hideButton(location25Name, twentyfive, t_twentyfive);
        hideButton(location26Name, twentysix, t_twentysix);
        hideButton(location27Name, twentyseven, t_twentyseven);
        hideButton(location28Name, twentyeight, t_twentyeight);
        hideButton(location29Name, twentynine, t_twentynine);
        hideButton(location30Name, thirty, t_thirty);

        hideButton(category1Name, cat_one, t_cat_one);
        hideButton(category2Name, cat_two, t_cat_two);
        hideButton(category3Name, cat_three, t_cat_three);
        hideButton(category4Name, cat_four, t_cat_four);
        hideButton(category5Name, cat_five, t_cat_five);
        hideButton(category6Name, cat_six, t_cat_six);
        hideButton(category7Name, cat_seven, t_cat_seven);
        hideButton(category8Name, cat_eight, t_cat_eight);
        hideButton(category9Name, cat_nine, t_cat_nine);
        hideButton(category10Name, cat_ten, t_cat_ten);
        hideButton(category11Name, cat_eleven, t_cat_eleven);
        hideButton(category12Name, cat_twelve, t_cat_twelve);
        hideButton(category13Name, cat_thirteen, t_cat_thirteen);
        hideButton(category14Name, cat_fourteen, t_cat_fourteen);
        hideButton(category15Name, cat_fifthteen, t_cat_fifthteen);

        one.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.one.activity.HomeActivity.class);
            loadAD(location1Name, intent);
        });

        two.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.two.activity.HomeActivity.class);
            loadAD(location2Name, intent);
        });
        three.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.three.activity.HomeActivity.class);
            loadAD(location3Name, intent);
        });
        four.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.four.activity.HomeActivity.class);
            loadAD(location4Name, intent);
        });
        five.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.five.activity.HomeActivity.class);
            loadAD(location5Name, intent);
        });
        six.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.six.activity.HomeActivity.class);
            loadAD(location6Name, intent);
        });
        seven.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.seven.activity.HomeActivity.class);
            loadAD(location7Name, intent);
        });
        eight.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.eight.activity.HomeActivity.class);
            loadAD(location8Name, intent);
        });
        nine.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.nine.activity.HomeActivity.class);
            loadAD(location9Name, intent);
        });
        ten.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.ten.activity.HomeActivity.class);
            loadAD(location10Name, intent);
        });
        eleven.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.eleven.activity.HomeActivity.class);
            loadAD(location11Name, intent);
        });
        twelve.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twelve.activity.HomeActivity.class);
            loadAD(location12Name, intent);
        });
        thirteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.thirteen.activity.HomeActivity.class);
            loadAD(location13Name, intent);
        });
        fourteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.fourteen.activity.HomeActivity.class);
            loadAD(location14Name, intent);
        });
        fifthteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.fifthteen.activity.HomeActivity.class);
            loadAD(location15Name, intent);
        });
        sixthteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.sixthteen.activity.HomeActivity.class);
            loadAD(location16Name, intent);
        });
        seventeen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.seventeen.activity.HomeActivity.class);
            loadAD(location17Name, intent);
        });
        eighteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.eighteen.activity.HomeActivity.class);
            loadAD(location18Name, intent);
        });
        nineteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.nineteen.activity.HomeActivity.class);
            loadAD(location19Name, intent);
        });
        twenty.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twenty.activity.HomeActivity.class);
            loadAD(location20Name, intent);
        });
        twentyone.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentyone.activity.HomeActivity.class);
            loadAD(location21Name, intent);
        });
        twentytwo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentytwo.activity.HomeActivity.class);
            loadAD(location22Name, intent);
        });
        twentythree.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentythree.activity.HomeActivity.class);
            loadAD(location23Name, intent);
        });
        twentyfour.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentyfour.activity.HomeActivity.class);
            loadAD(location24Name, intent);
        });
        twentyfive.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentyfive.activity.HomeActivity.class);
            loadAD(location25Name, intent);
        });
        twentysix.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentysix.activity.HomeActivity.class);
            loadAD(location26Name, intent);
        });
        twentyseven.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentyseven.activity.HomeActivity.class);
            loadAD(location27Name, intent);
        });
        twentyeight.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentyeight.activity.HomeActivity.class);
            loadAD(location28Name, intent);
        });
        twentynine.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.twentynine.activity.HomeActivity.class);
            loadAD(location29Name, intent);
        });
        thirty.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_location.thirty.activity.HomeActivity.class);
            loadAD(location30Name, intent);
        });


        cat_one.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.one.activity.HomeActivity.class);
            loadAD(category1Name, intent);
        });
        cat_two.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.two.activity.HomeActivity.class);
            loadAD(category2Name, intent);
        });
        cat_three.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.three.activity.HomeActivity.class);
            loadAD(category3Name, intent);
        });
        cat_four.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.four.activity.HomeActivity.class);
            loadAD(category4Name, intent);
        });
        cat_five.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.five.activity.HomeActivity.class);
            loadAD(category5Name, intent);
        });
        cat_six.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.six.activity.HomeActivity.class);
            loadAD(category6Name, intent);
        });
        cat_seven.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.seven.activity.HomeActivity.class);
            loadAD(category7Name, intent);
        });
        cat_eight.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.eight.activity.HomeActivity.class);
            loadAD(category8Name, intent);
        });
        cat_nine.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.nine.activity.HomeActivity.class);
            loadAD(category9Name, intent);
        });
        cat_ten.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.ten.activity.HomeActivity.class);
            loadAD(category10Name, intent);
        });
        cat_eleven.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.eleven.activity.HomeActivity.class);
            loadAD(category11Name, intent);
        });
        cat_twelve.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.twelve.activity.HomeActivity.class);
            loadAD(category12Name, intent);
        });
        cat_thirteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.thirteen.activity.HomeActivity.class);
            loadAD(category13Name, intent);
        });
        cat_fourteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.fourteen.activity.HomeActivity.class);
            loadAD(category14Name, intent);
        });
        cat_fifthteen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.newstoday.news_package.news_category.fifthteen.activity.HomeActivity.class);
            loadAD(category15Name, intent);
        });

        mListView.addHeaderView(header);
    }


    private void hideButton(String name, CardView view, TextView t_view) {
        if (name.equals("null")) {
            view.setVisibility(View.GONE);
        } else {
            t_view.setText(name);
        }
    }

    private void loadAD(String name, Intent intent) {
        MobileAds.initialize(getActivity(), initializationStatus -> {
        });
        InterstitialAd mInterstitialAd = new InterstitialAd(Objects.requireNonNull(getActivity()));
        mInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.intrestial_ad));
        mInterstitialAd.loadAd(new AdRequest.Builder().addKeyword("Insurance").build());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int slideAD = sharedPreferences.getInt("BUTTON_CLICK", 0) + 1;
        SlideAd_Service.putBUTTON_CLICK(getActivity(), slideAD);
        if (slideAD >= 5) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                SlideAd_Service.putBUTTON_CLICK(getActivity(), 0);
                mInterstitialAd = new InterstitialAd(getActivity());
                mInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.intrestial_ad));
                mInterstitialAd.loadAd(new AdRequest.Builder().addKeyword("Insurance").build());
            } else {
                SlideAd_Service.putBUTTON_CLICK(getActivity(), slideAD);
                mInterstitialAd = new InterstitialAd(getActivity());
                mInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.intrestial_ad));
                mInterstitialAd.loadAd(new AdRequest.Builder().addKeyword("Insurance").build());
            }
        }
        intent.putExtra("name", name);
        getActivity().startActivity(intent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void inflateView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.news_fragment_entry_list, container, true);

        if (mEntriesCursorAdapter != null) {
            setListAdapter(mEntriesCursorAdapter);
        }

        mListView = rootView.findViewById(android.R.id.list);
        addHeaderView();

        UiUtils.addEmptyFooterView(mListView, 90);

        mRefreshListBtn = rootView.findViewById(R.id.refreshListBtn);
        mRefreshListBtn.setBackgroundResource(R.drawable.bg_refresh_list_button_selector);
        mRefreshListBtn.setOnClickListener(view -> {
            mNewEntriesNumber = 0;
            mListDisplayDate = new Date().getTime();
            refreshUI();
            if (mUri != null) {
                restartLoaders();
            }
        });
        disableSwipe();
    }

    @Override
    public void onStop() {
        PrefUtils.unregisterOnPrefChangeListener(mPrefListener);
        if (mJustMarkedAsReadEntries != null && !mJustMarkedAsReadEntries.isClosed()) {
            mJustMarkedAsReadEntries.close();
        }
        refreshUI();
        super.onStop();
        mListView.getViewTreeObserver().removeOnScrollChangedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_CURRENT_URI, mUri);
        outState.putParcelable(STATE_ORIGINAL_URI, mOriginalUri);
        outState.putBoolean(STATE_SHOW_FEED_INFO, mShowFeedInfo);
        outState.putLong(STATE_LIST_DISPLAY_DATE, mListDisplayDate);
        refreshUI();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        startRefresh();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        if (id >= 0) { // should not happen, but I had a crash with this on PlayStore...
            startActivity(new Intent(Intent.ACTION_VIEW, ContentUris.withAppendedId(mUri, id)));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear(); // This is needed to remove a bug on Android 4.0.3
        inflater.inflate(R.menu.entry_list, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        if (EntryColumns.isSearchUri(mUri)) {
            searchItem.expandActionView();
            // Without that, it just does not work
            searchView.post(() -> {
                searchView.setQuery(mUri.getLastPathSegment(), false);
                searchView.clearFocus();
            });
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mListView.removeHeaderView(header);
                if (TextUtils.isEmpty(newText)) {
                    setData(mOriginalUri, true);
                } else {
                    setData(EntryColumns.SEARCH_URI(newText), true, true);
                }
                return false;
            }
        });
        searchView.setOnCloseListener(() -> {
            setData(mOriginalUri, true);
            addHeaderView();
            return false;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reresh: {
                startRefresh();
                return true;
            }
            case R.id.setting: {
                startActivity(new Intent(getActivity(), GeneralPrefsActivity.class));
                return true;
            }
            case R.id.more_rate:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                alertDialogBuilder.setMessage("If you are facing any problems, please send us a message else, please give us 5-star ratings. It helps us to do more hard work on this app.");

                alertDialogBuilder.setPositiveButton("Rate us", (dialog, which) -> {
                    String url = "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                });
                alertDialogBuilder.setNegativeButton("Send Problems", (dialog, which) -> {
                    Toast.makeText(myContext, "Please send message only from Gmail.", Toast.LENGTH_SHORT).show();
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"cherrydigital.care@gmail.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, "Problems & Feedback from-- " + getActivity().getPackageName());
                    email.putExtra(Intent.EXTRA_TEXT, "Note : Dont't clear the subject please,\n\n");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Send Mail"));
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.more_invite:
                Intent txtIntent = new Intent(Intent.ACTION_SEND);
                txtIntent.setType("text/plain");
                txtIntent.putExtra(Intent.EXTRA_TEXT, "One of the best News App with online radio, todo &amp; diary, Feed Reader, and many other features in one app. Download now from Google Play Store \n\n" + "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
                startActivity(Intent.createChooser(txtIntent, "Share"));
                break;
            case R.id.more_about:
                startActivity(new Intent(getActivity(), About_Developer.class));
                break;

            case R.id.more_add_publisher:
                Toast.makeText(myContext, "Please send message only from Gmail.", Toast.LENGTH_SHORT).show();
                Intent emaill = new Intent(Intent.ACTION_SEND);
                emaill.putExtra(Intent.EXTRA_EMAIL, new String[]{"cherrydigital.care@gmail.com"});
                emaill.putExtra(Intent.EXTRA_SUBJECT, "Add New Suggestion from " + getActivity().getPackageName());
                emaill.putExtra(Intent.EXTRA_TEXT, "Note : Dont't clear the subject please\n\nNews/Radio Name = \n\nNews/Radio Details = \n\nWebsite Link = ");
                emaill.setType("message/rfc822");
                startActivity(Intent.createChooser(emaill, "Send Mail"));
                break;
            case R.id.more_send:
                Toast.makeText(myContext, "Please send message only from Gmail.", Toast.LENGTH_SHORT).show();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"cherrydigital.care@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Problems & Feedback from " + getActivity().getPackageName());
                email.putExtra(Intent.EXTRA_TEXT, "Note : Dont't clear the subject please,\n\n");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Mail"));
                break;
            case R.id.more_disclaimer:
                Disclaimer_Dialog exampleDialog = new Disclaimer_Dialog();
                exampleDialog.show(myContext.getSupportFragmentManager(), "dialog");
                break;
            case R.id.more_privacy:
                ChromeOpener opener = new ChromeOpener();
                opener.openLink(getActivity(), "https://sites.google.com/view/hamronepal-privacy-policy/home");
                break;
        }
        DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);

//            case R.id.menu_all_read: {
//                if (mEntriesCursorAdapter != null) {
//                    markAllRead();
//                    // If we are on "all items" uri, we can remove the notification here
//                    if (EntryColumns.CONTENT_URI.equals(mUri) && Constants.NOTIF_MGR != null) {
//                        Constants.NOTIF_MGR.cancel(0);
//                    }
//                }
//                return true;
//            }
//            case R.id.menu_unread:
//                if (!PrefUtils.getBoolean(PrefUtils.SHOW_READ, true)) {
//                    PrefUtils.putBoolean(PrefUtils.SHOW_READ, true);
//                    Toast.makeText(getActivity(), "All news", Toast.LENGTH_SHORT).show();
//                    menu.findItem(R.id.menu_unread).setIcon(R.drawable.nav_unread);
//                    return true;
//
//                } else {
//                    PrefUtils.putBoolean(PrefUtils.SHOW_READ, false);
//                    Toast.makeText(getActivity(), "Unread news", Toast.LENGTH_SHORT).show();
//                    menu.findItem(R.id.menu_unread).setIcon(R.drawable.nav_read);
//
//                }
//                return true;

    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @SuppressLint("PrivateResource")
    private void markAllRead() {
        if (mEntriesCursorAdapter != null) {
            Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.coordinator_layout), R.string.marked_as_read, Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(getActivity(), R.color.light_primary_color))
                    .setAction(R.string.undo, v -> new Thread() {
                        @Override
                        public void run() {
                            if (mJustMarkedAsReadEntries != null && !mJustMarkedAsReadEntries.isClosed()) {
                                ArrayList<Integer> ids = new ArrayList<>();
                                while (mJustMarkedAsReadEntries.moveToNext()) {
                                    ids.add(mJustMarkedAsReadEntries.getInt(0));
                                }
                                ContentResolver cr = MainApplication.getContext().getContentResolver();
                                String where = BaseColumns._ID + " IN (" + TextUtils.join(",", ids) + ')';
                                cr.update(EntryColumns.CONTENT_URI, FeedData.getUnreadContentValues(), where, null);

                                mJustMarkedAsReadEntries.close();
                            }
                        }
                    }.start());
            snackbar.getView().setBackgroundResource(R.color.material_grey_900);
            snackbar.show();

            new Thread() {
                @Override
                public void run() {
                    ContentResolver cr = MainApplication.getContext().getContentResolver();
                    String where = EntryColumns.WHERE_UNREAD + Constants.DB_AND + '(' + EntryColumns.FETCH_DATE + Constants.DB_IS_NULL + Constants.DB_OR + EntryColumns.FETCH_DATE + "<=" + mListDisplayDate + ')';
                    if (mJustMarkedAsReadEntries != null && !mJustMarkedAsReadEntries.isClosed()) {
                        mJustMarkedAsReadEntries.close();
                    }
                    mJustMarkedAsReadEntries = cr.query(mUri, new String[]{BaseColumns._ID}, where, null, null);
                    cr.update(mUri, FeedData.getReadContentValues(), where, null);
                }
            }.start();
        }
    }


    private void startRefresh() {
        if (!PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
            if (mUri != null && FeedDataContentProvider.URI_MATCHER.match(mUri) == FeedDataContentProvider.URI_ENTRIES_FOR_FEED) {
                getActivity().startService(new Intent(getActivity(), FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS).putExtra(Constants.FEED_ID,
                        mUri.getPathSegments().get(1)));
            } else {
                getActivity().startService(new Intent(getActivity(), FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS));
            }
        }
        refreshSwipeProgress();
    }

    public Uri getUri() {
        return mOriginalUri;
    }

    public void setData(Uri uri, boolean showFeedInfo) {
        setData(uri, showFeedInfo, false);
    }

    private void setData(Uri uri, boolean showFeedInfo, boolean isSearchUri) {
        mUri = uri;
        if (!isSearchUri) {
            mOriginalUri = mUri;
        }
        mShowFeedInfo = showFeedInfo;

        mEntriesCursorAdapter = new EntriesCursorAdapter(getActivity(), Constants.EMPTY_CURSOR);
        setListAdapter(mEntriesCursorAdapter);

        mListDisplayDate = new Date().getTime();
        if (mUri != null) {
            restartLoaders();
        }
        refreshUI();
    }

    private void restartLoaders() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.restartLoader(ENTRIES_LOADER_ID, null, mEntriesLoader);
        loaderManager.restartLoader(NEW_ENTRIES_NUMBER_LOADER_ID, null, mEntriesNumberLoader);
    }

    private void refreshUI() {
        if (mNewEntriesNumber > 0) {
            if (mRefreshListBtn.getVisibility() == View.GONE)
                YoYo.with(Techniques.BounceInUp).duration(500).playOn(mRefreshListBtn);
            mRefreshListBtn.setText(getResources().getQuantityString(R.plurals.number_of_new_news, mNewEntriesNumber, mNewEntriesNumber));
            mRefreshListBtn.setVisibility(View.VISIBLE);
        } else {
            mRefreshListBtn.setVisibility(View.GONE);
        }
    }

    private void refreshSwipeProgress() {
        if (PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
            showSwipeProgress();
        } else {
            hideSwipeProgress();
        }
    }
}
