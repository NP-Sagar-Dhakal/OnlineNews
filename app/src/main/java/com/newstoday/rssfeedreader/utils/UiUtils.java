/*
  spaRSS
  <p/>
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

package com.newstoday.rssfeedreader.utils;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.collection.LongSparseArray;

import com.google.android.material.snackbar.Snackbar;
import com.newstoday.MainApplication;
import com.newstoday.nepali.news.R;

public class UiUtils {

    static private final LongSparseArray<Bitmap> FAVICON_CACHE = new LongSparseArray<>();

    static public int dpToPixel(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, MainApplication.getContext().getResources().getDisplayMetrics());
    }


    static public Bitmap getFaviconBitmap(long feedId, Cursor cursor, int iconCursorPos) {
        Bitmap bitmap = UiUtils.FAVICON_CACHE.get(feedId);
        if (bitmap == null) {
            byte[] iconBytes = cursor.getBlob(iconCursorPos);
            if (iconBytes != null && iconBytes.length > 0) {
                bitmap = UiUtils.getScaledBitmap(iconBytes, 18);
                UiUtils.FAVICON_CACHE.put(feedId, bitmap);
            }
        }
        return bitmap;
    }

    static public Bitmap getScaledBitmap(byte[] iconBytes, int sizeInDp) {
        if (iconBytes != null && iconBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(iconBytes, 0, iconBytes.length);
            if (bitmap != null && bitmap.getWidth() != 0 && bitmap.getHeight() != 0) {
                int bitmapSizeInDip = UiUtils.dpToPixel(sizeInDp);
                if (bitmap.getHeight() != bitmapSizeInDip) {
                    Bitmap tmp = bitmap;
                    bitmap = Bitmap.createScaledBitmap(tmp, bitmapSizeInDip, bitmapSizeInDip, false);
                    tmp.recycle();
                }

                return bitmap;
            }
        }

        return null;
    }


    static public void addEmptyFooterView(ListView listView, int dp) {
        View view = new View(listView.getContext());
        view.setMinimumHeight(dpToPixel(dp));
        view.setClickable(true);
        listView.addFooterView(view);
    }

    static public void showMessage(@NonNull Activity activity, @StringRes int messageId) {
        showMessage(activity, activity.getString(messageId));
    }

    private static void showMessage(@NonNull Activity activity, @NonNull String message) {
        View coordinatorLayout = activity.findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make((coordinatorLayout != null ? coordinatorLayout : activity.findViewById(android.R.id.content)), message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundResource(R.color.material_grey_900);
        snackbar.show();
    }

}
