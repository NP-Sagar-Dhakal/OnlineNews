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

package com.newstoday.news_package.news_category.six.fragment;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.newstoday.Constants;
import com.newstoday.MainApplication;
import com.newstoday.nepali.news.R;
import com.newstoday.news_package.news_category.six.activity.EntryActivity;
import com.newstoday.news_package.news_category.six.provider.FeedData;
import com.newstoday.news_package.news_category.six.provider.FeedData.EntryColumns;
import com.newstoday.news_package.news_category.six.provider.FeedData.FeedColumns;
import com.newstoday.news_package.news_category.six.service.FetcherService;
import com.newstoday.news_package.news_category.six.utils.PrefUtils;
import com.newstoday.news_package.recent_news.fragment.SwipeRefreshFragment;
import com.newstoday.news_package.recent_news.view.EntryView;
import com.newstoday.services.SlideAd_Service;
import com.newstoday.services.Theme_Service;

import java.util.Objects;

public class EntryFragment extends SwipeRefreshFragment implements
        LoaderManager.LoaderCallbacks<Cursor> {
    private static final String TAG = "EntryFragment";

    private static final String STATE_BASE_URI = "STATE_BASE_URI";
    private static final String STATE_CURRENT_PAGER_POS = "STATE_CURRENT_PAGER_POS";
    private static final String STATE_ENTRIES_IDS = "STATE_ENTRIES_IDS";
    private static final String STATE_INITIAL_ENTRY_ID = "STATE_INITIAL_ENTRY_ID";

    private int mTitlePos = -1;
    private int mDatePos;
    private int mMobilizedHtmlPos;
    private int mAbstractPos;
    private int mLinkPos;
    private int mIsFavoritePos;
    private int mIsReadPos;
    private int mEnclosurePos;
    private int mAuthorPos;

    private int mCurrentPagerPos = -1;
    private Uri mBaseUri;
    private long mInitialEntryId = -1;
    private long[] mEntriesIds;

    private boolean mFavorite, mPreferFullText = true;
    private ViewPager mEntryPager;
    private EntryPagerAdapter mEntryPagerAdapter;
    private final SharedPreferences.OnSharedPreferenceChangeListener mPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (PrefUtils.IS_REFRESHING.equals(key)) {
                Log.d(TAG, "onSharedPreferenceChanged() called with: " + "sharedPreferences = [" + sharedPreferences + "], key = [" + key + "]");
                refreshSwipeProgress();
                if (PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
                    return;
                }
                //if refreshing is done, reload
                mEntryPagerAdapter.displayEntry(mCurrentPagerPos, null, true);
            }
        }
    };
    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mEntryPagerAdapter = new EntryPagerAdapter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_fragment_entry, container, true);

        MobileAds.initialize(getActivity(), initializationStatus -> {
        });


        mEntryPager = rootView.findViewById(R.id.pager);
        //mEntryPager.setPageTransformer(true, new DepthPageTransformer());
        mEntryPager.setAdapter(mEntryPagerAdapter);

        if (savedInstanceState != null) {
            mBaseUri = savedInstanceState.getParcelable(STATE_BASE_URI);
            mEntriesIds = savedInstanceState.getLongArray(STATE_ENTRIES_IDS);
            mInitialEntryId = savedInstanceState.getLong(STATE_INITIAL_ENTRY_ID);
            mCurrentPagerPos = savedInstanceState.getInt(STATE_CURRENT_PAGER_POS);
            Objects.requireNonNull(mEntryPager.getAdapter()).notifyDataSetChanged();
            mEntryPager.setCurrentItem(mCurrentPagerPos);
        }

        mEntryPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                if (String.valueOf(i).endsWith("3") || String.valueOf(i).endsWith("6") || String.valueOf(i).endsWith("9")) {
                    EntryActivity.adLoader.loadAd(new AdRequest.Builder().build());
                }
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SlideAd_Service.putSLIDE_AD(getActivity(), sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0) + 1);
                int slideCount = sharedPreferences.getInt(SlideAd_Service.SLIDE_COUNT, 0);
                if (slideCount >= 25) {
                    if (slideCount <= 25 || slideCount >= 32) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        InterstitialAd.load(getActivity(), getActivity().getResources().getString(R.string.interstitial_ad), adRequest, new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                interstitialAd.show(getActivity());
                                if (slideCount > 50) {
                                    SlideAd_Service.putSLIDE_AD(getActivity(), 0);
                                } else {
                                    SlideAd_Service.putSLIDE_AD(getActivity(), slideCount - 25);
                                }
                                super.onAdLoaded(interstitialAd);
                            }
                        });
                    }
                }
                mCurrentPagerPos = i;
                refreshUI(mEntryPagerAdapter.getCursor(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        disableSwipe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_BASE_URI, mBaseUri);
        outState.putLongArray(STATE_ENTRIES_IDS, mEntriesIds);
        outState.putLong(STATE_INITIAL_ENTRY_ID, mInitialEntryId);
        outState.putInt(STATE_CURRENT_PAGER_POS, mCurrentPagerPos);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mEntryPagerAdapter.onResume();
        PrefUtils.registerOnPrefChangeListener(mPrefListener);
    }

    @Override
    public void onPause() {
        super.onPause();
//        mEntryPagerAdapter.onPause();
        PrefUtils.unregisterOnPrefChangeListener(mPrefListener);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.entry, menu);

        if (mFavorite) {
            MenuItem item = menu.findItem(R.id.menu_star);
            item.setTitle(R.string.menu_unstar).setIcon(R.drawable.ic_heart_filled);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mEntriesIds != null) {
            final Activity activity = getActivity();

            switch (item.getItemId()) {
                case R.id.menu_star: {
                    mFavorite = !mFavorite;

                    if (mFavorite) {
                        item.setTitle(R.string.menu_unstar).setIcon(R.drawable.ic_heart_filled);
                    } else {
                        item.setTitle(R.string.menu_star).setIcon(R.drawable.ic_heart_empty);
                    }

                    final Uri uri = ContentUris.withAppendedId(mBaseUri, mEntriesIds[mCurrentPagerPos]);
                    new Thread() {
                        @Override
                        public void run() {
                            ContentValues values = new ContentValues();
                            values.put(EntryColumns.IS_FAVORITE, mFavorite ? 1 : 0);
                            ContentResolver cr = MainApplication.getContext().getContentResolver();
                            cr.update(uri, values, null, null);

                            // Update the cursor
                            Cursor updatedCursor = cr.query(uri, null, null, null, null);
                            Objects.requireNonNull(updatedCursor).moveToFirst();
                            mEntryPagerAdapter.setUpdatedCursor(mCurrentPagerPos, updatedCursor);
                        }
                    }.start();
                    break;
                }
                case R.id.darker: {
                    Theme_Service.changeTheme(getActivity());
                    break;
                }
                case R.id.menu_share: {
                    Cursor cursor = mEntryPagerAdapter.getCursor(mCurrentPagerPos);
                    if (cursor != null) {
                        String link = cursor.getString(mLinkPos);
                        if (link != null) {
                            String title = cursor.getString(mTitlePos);
                            startActivity(Intent.createChooser(
                                    new Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_SUBJECT, title).putExtra(Intent.EXTRA_TEXT, link)
                                            .setType(Constants.MIMETYPE_TEXT_PLAIN), getString(R.string.menu_share)
                            ));
                        }
                    }
                    break;
                }
                case R.id.menu_copy_clipboard: {
                    Cursor cursor = mEntryPagerAdapter.getCursor(mCurrentPagerPos);
                    String link = cursor.getString(mLinkPos);
                    ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Text", link);
                    Objects.requireNonNull(clipboard).setPrimaryClip(clip);

                    Toast.makeText(activity, R.string.copied_clipboard, Toast.LENGTH_SHORT).show();
                    break;
                }
                case R.id.menu_mark_as_unread: {
                    final Uri uri = ContentUris.withAppendedId(mBaseUri, mEntriesIds[mCurrentPagerPos]);
                    new Thread() {
                        @Override
                        public void run() {
                            ContentResolver cr = MainApplication.getContext().getContentResolver();
                            cr.update(uri, FeedData.getUnreadContentValues(), null, null);
                        }
                    }.start();
                    activity.finish();
                    break;
                }

                case R.id.menu_open_in_browser: {
                    Cursor cursor = mEntryPagerAdapter.getCursor(mCurrentPagerPos);
                    if (cursor != null) {
                        String link = cursor.getString(mLinkPos);
                        if (link != null) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                            startActivity(browserIntent);
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void setData(Uri uri) {
        mCurrentPagerPos = -1;

        mBaseUri = EntryColumns.PARENT_URI(Objects.requireNonNull(uri.getPath()));
        try {
            mInitialEntryId = Long.parseLong(Objects.requireNonNull(uri.getLastPathSegment()));
        } catch (Exception unused) {
            mInitialEntryId = -1;
        }

        if (mBaseUri != null) {
            Bundle b = getActivity().getIntent().getExtras();

            String whereClause = FeedData.shouldShowReadEntries(mBaseUri) ||
                    (b != null && b.getBoolean(Constants.INTENT_FROM_WIDGET, false)) ? null : EntryColumns.WHERE_UNREAD;
            String entriesOrder = PrefUtils.getBoolean(PrefUtils.DISPLAY_OLDEST_FIRST, false) ? Constants.DB_ASC : Constants.DB_DESC;

            // Load the entriesIds list. Should be in a loader... but I was too lazy to do so
            Cursor entriesCursor = MainApplication.getContext().getContentResolver().query(mBaseUri, EntryColumns.PROJECTION_ID,
                    whereClause, null, EntryColumns.DATE + entriesOrder);

            if (entriesCursor != null && entriesCursor.getCount() > 0) {
                mEntriesIds = new long[entriesCursor.getCount()];
                int i = 0;
                while (entriesCursor.moveToNext()) {
                    mEntriesIds[i] = entriesCursor.getLong(0);
                    if (mEntriesIds[i] == mInitialEntryId) {
                        mCurrentPagerPos = i; // To immediately display the good entry
                    }
                    i++;
                }

                entriesCursor.close();
            }
        } else {
            mEntriesIds = null;
        }

        mEntryPagerAdapter.notifyDataSetChanged();
        //mEntryPager.invalidateBullets();
        if (mCurrentPagerPos != -1) {
            mEntryPager.setCurrentItem(mCurrentPagerPos);
        }
    }

    private void refreshUI(Cursor entryCursor) {
        if (entryCursor != null) {
            mFavorite = entryCursor.getInt(mIsFavoritePos) == 1;
            getActivity().invalidateOptionsMenu();
            // Listen the mobilizing task
            if (FetcherService.hasMobilizationTask(mEntriesIds[mCurrentPagerPos])) {
                // If the service is not started, start it here to avoid an infinite loading
                if (!PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
                    MainApplication.getContext().startService(new Intent(MainApplication.getContext(), FetcherService.class).setAction(FetcherService.ACTION_MOBILIZE_FEEDS));
                }
            }

            // Mark the article as read
            if (entryCursor.getInt(mIsReadPos) != 1) {
                final Uri uri = ContentUris.withAppendedId(mBaseUri, mEntriesIds[mCurrentPagerPos]);
                new Thread(() -> {
                    ContentResolver cr = MainApplication.getContext().getContentResolver();
                    cr.update(uri, FeedData.getReadContentValues(), null, null);

                    // Update the cursor
                    Cursor updatedCursor = cr.query(uri, null, null, null, null);
                    if (updatedCursor != null) {
                        updatedCursor.moveToFirst();
                    }
                    mEntryPagerAdapter.setUpdatedCursor(mCurrentPagerPos, updatedCursor);
                }).start();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(), EntryColumns.CONTENT_URI(mEntriesIds[id]), null, null, null, null);
        cursorLoader.setUpdateThrottle(1000);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (mBaseUri != null && cursor != null) { // can be null if we do a setData(null) before
            cursor.moveToFirst();

            if (mTitlePos == -1) {
                mTitlePos = cursor.getColumnIndex(EntryColumns.TITLE);
                mDatePos = cursor.getColumnIndex(EntryColumns.DATE);
                mAbstractPos = cursor.getColumnIndex(EntryColumns.ABSTRACT);
                mMobilizedHtmlPos = cursor.getColumnIndex(EntryColumns.MOBILIZED_HTML);
                mLinkPos = cursor.getColumnIndex(EntryColumns.LINK);
                mIsFavoritePos = cursor.getColumnIndex(EntryColumns.IS_FAVORITE);
                mIsReadPos = cursor.getColumnIndex(EntryColumns.IS_READ);
                mEnclosurePos = cursor.getColumnIndex(EntryColumns.ENCLOSURE);
                mAuthorPos = cursor.getColumnIndex(EntryColumns.AUTHOR);
                int mFeedNamePos = cursor.getColumnIndex(FeedColumns.NAME);
                int mFeedUrlPos = cursor.getColumnIndex(FeedColumns.URL);
                int mFeedIconPos = cursor.getColumnIndex(FeedColumns.ICON);
            }

            int position = loader.getId();
            if (position != -1) {
                mEntryPagerAdapter.displayEntry(position, cursor, false);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mEntryPagerAdapter.setUpdatedCursor(loader.getId(), null);
    }

    @Override
    public void onRefresh() {
        // Nothing to do
    }

    private void refreshSwipeProgress() {
        if (PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false)) {
            showSwipeProgress();
        } else {
            hideSwipeProgress();
        }
    }

    private class EntryPagerAdapter extends PagerAdapter {

        private final SparseArray<EntryView> mEntryViews = new SparseArray<>();

        EntryPagerAdapter() {
        }

        @Override
        public int getCount() {
            return mEntriesIds != null ? mEntriesIds.length : 0;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            EntryView view = new EntryView(getActivity());
            mEntryViews.put(position, view);
            container.addView(view);
            getLoaderManager().restartLoader(position, null, EntryFragment.this);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            getLoaderManager().destroyLoader(position);
            container.removeView((View) object);
            mEntryViews.delete(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        void displayEntry(int pagerPos, Cursor newCursor, boolean forceUpdate) {
            Log.d(TAG, "displayEntry() called with: " + "pagerPos = [" + pagerPos + "], newCursor = [" + newCursor + "], forceUpdate = [" + forceUpdate + "]");
            EntryView view = mEntryViews.get(pagerPos);
            if (view != null) {
                if (newCursor == null) {
                    newCursor = (Cursor) view.getTag(); // get the old one
                }

                if (newCursor != null && newCursor.moveToFirst()) {
                    String contentText = newCursor.getString(mMobilizedHtmlPos);
                    if (contentText == null || (forceUpdate && !mPreferFullText)) {
                        contentText = newCursor.getString(mAbstractPos);
                        mPreferFullText = false;
                    } else {
                        mPreferFullText = true;
                    }
                    if (contentText == null) {
                        contentText = "";
                    }

                    String author = newCursor.getString(mAuthorPos);
                    long timestamp = newCursor.getLong(mDatePos);
                    String link = newCursor.getString(mLinkPos);
                    String title = newCursor.getString(mTitlePos);
                    String enclosure = newCursor.getString(mEnclosurePos);

                    view.setHtml(getActivity(), pagerPos, title, link, contentText, timestamp);
                    view.setTag(newCursor);

                    if (pagerPos == mCurrentPagerPos) {
                        refreshUI(newCursor);
                    }
                }
            }
        }

        Cursor getCursor(int pagerPos) {
            EntryView view = mEntryViews.get(pagerPos);
            if (view != null) {
                return (Cursor) view.getTag();
            }
            return null;
        }

        void setUpdatedCursor(int pagerPos, Cursor newCursor) {
            EntryView view = mEntryViews.get(pagerPos);
            if (view != null) {
                Cursor previousUpdatedOne = (Cursor) view.getTag(R.id.updated_cursor);
                if (previousUpdatedOne != null) {
                    previousUpdatedOne.close();
                }
                view.setTag(newCursor);
                view.setTag(R.id.updated_cursor, newCursor);
            }
        }
    }
}

