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

package com.newstoday.news_package.news_location.twentysix.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.newstoday.Constants;
import com.newstoday.R;
import com.newstoday.news_package.recent_news.activity.MainHomeActivity;
import com.newstoday.news_package.news_location.twentysix.adapter.EntriesCursorAdapter;
import com.newstoday.news_package.news_location.twentysix.provider.FeedData;
import com.newstoday.news_package.news_location.twentysix.provider.FeedDataContentProvider;
import com.newstoday.news_package.news_location.twentysix.provider.FeedData.EntryColumns;
import com.newstoday.news_package.recent_news.fragment.SwipeRefreshListFragment;
import com.newstoday.news_package.news_location.twentysix.service.FetcherService;
import com.newstoday.news_package.recent_news.utils.UiUtils;
import com.newstoday.recyclerview.News_Sites_Adapter;
import com.newstoday.services.FilterService;
import com.newstoday.news_package.news_location.twentysix.utils.PrefUtils;

import java.util.Date;

import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category10NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category11NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category12NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category13NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category14NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category15NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category1NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category2NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category3NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category4NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category5NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category6NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category7NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category8NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.category9NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location10NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location11NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location12NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location13NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location14NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location15NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location16NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location17NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location18NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location19NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location1NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location20NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location21NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location22NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location23NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location24NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location25NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location26NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location27NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location28NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location29NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location2NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location30NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location3NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location4NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location5NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location6NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location7NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location8NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.location9NewsSites;
import static com.newstoday.news_package.recent_news.activity.MainHomeActivity.topNewsSites;


public class EntriesListFragment extends SwipeRefreshListFragment implements ViewTreeObserver.OnScrollChangedListener {
    private static final String STATE_CURRENT_URI = "STATE_CURRENT_URI";
    private static final String STATE_ORIGINAL_URI = "STATE_ORIGINAL_URI";
    private static final String STATE_SHOW_FEED_INFO = "STATE_SHOW_FEED_INFO";
    private static final String STATE_LIST_DISPLAY_DATE = "STATE_LIST_DISPLAY_DATE";

    private static final int ENTRIES_LOADER_ID = 1;
    private static final int NEW_ENTRIES_NUMBER_LOADER_ID = 2;

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
    private View header;
    private RecyclerView news_Recycler;
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

            mEntriesCursorAdapter = new EntriesCursorAdapter(getActivity(), Constants.EMPTY_CURSOR, mShowFeedInfo);
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void inflateView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.news_fragment_entry_list, container, true);

        if (mEntriesCursorAdapter != null) {
            setListAdapter(mEntriesCursorAdapter);
        }

        mListView = rootView.findViewById(android.R.id.list);

        header = getActivity().getLayoutInflater().inflate(R.layout.listview_header, null);
        news_Recycler = header.findViewById(R.id.news_recycler);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        news_Recycler.setLayoutManager(layoutManager1);
        News_Sites_Adapter adapter = new News_Sites_Adapter(getActivity(), "location26NewsSites", topNewsSites, category1NewsSites, category2NewsSites, category3NewsSites, category4NewsSites, category5NewsSites, category6NewsSites, category7NewsSites, category8NewsSites, category9NewsSites, category10NewsSites, category11NewsSites, category12NewsSites, category13NewsSites, category14NewsSites, category15NewsSites, location1NewsSites, location2NewsSites, location3NewsSites, location4NewsSites, location5NewsSites, location6NewsSites, location7NewsSites, location8NewsSites, location9NewsSites, location10NewsSites, location11NewsSites, location12NewsSites, location13NewsSites, location14NewsSites, location15NewsSites, location16NewsSites, location17NewsSites, location18NewsSites, location19NewsSites, location20NewsSites, location21NewsSites, location22NewsSites, location23NewsSites, location24NewsSites, location25NewsSites, location26NewsSites, location27NewsSites, location28NewsSites, location29NewsSites, location30NewsSites);
        news_Recycler.setAdapter(adapter);
        mListView.addHeaderView(header);

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
        inflater.inflate(R.menu.entry_list_location, menu);
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
            header = getActivity().getLayoutInflater().inflate(R.layout.listview_header, null);
            news_Recycler = header.findViewById(R.id.news_recycler);
            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            news_Recycler.setLayoutManager(layoutManager1);
            News_Sites_Adapter adapter = new News_Sites_Adapter(getActivity(), "location26NewsSites", topNewsSites, category1NewsSites, category2NewsSites, category3NewsSites, category4NewsSites, category5NewsSites, category6NewsSites, category7NewsSites, category8NewsSites, category9NewsSites, category10NewsSites, category11NewsSites, category12NewsSites, category13NewsSites, category14NewsSites, category15NewsSites, location1NewsSites, location2NewsSites, location3NewsSites, location4NewsSites, location5NewsSites, location6NewsSites, location7NewsSites, location8NewsSites, location9NewsSites, location10NewsSites, location11NewsSites, location12NewsSites, location13NewsSites, location14NewsSites, location15NewsSites, location16NewsSites, location17NewsSites, location18NewsSites, location19NewsSites, location20NewsSites, location21NewsSites, location22NewsSites, location23NewsSites, location24NewsSites, location25NewsSites, location26NewsSites, location27NewsSites, location28NewsSites, location29NewsSites, location30NewsSites);
            news_Recycler.setAdapter(adapter);
            mListView.addHeaderView(header);
            return false;
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            NotificationChannel channel = new NotificationChannel(
                    com.newstoday.screenfilter.Constants.NOTIFICATION_CHANNEL_ID_RS,
                    getString(R.string.notification_channel_running_status),
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setShowBadge(false);
            channel.enableLights(false);
            channel.enableVibration(false);
            channel.setSound(null, null);

            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reresh: {
                startRefresh();
                return true;
            }
            case R.id.darker: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotificationChannel();
                    }
                    startActivity(new Intent(getActivity(), com.newstoday.screenfilter.ui.MainActivity.class));
                } else {
                    Intent i = new Intent(getActivity(), FilterService.class);
                    if (FilterService.CURRENT_STATE == FilterService.ACTIVE) {
                        getActivity().stopService(i);
                    } else {
                        getActivity().startService(i);
                    }
                }
                break;
            }
            case android.R.id.home: {
                getActivity().onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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

        mEntriesCursorAdapter = new EntriesCursorAdapter(getActivity(), Constants.EMPTY_CURSOR, mShowFeedInfo);
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
            mRefreshListBtn.setText(getResources().getQuantityString(R.plurals.number_of_new_entries, mNewEntriesNumber, mNewEntriesNumber));
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
