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

package com.newstoday.news_package.news_category.twelve.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.newstoday.R;
import com.newstoday.news_package.news_category.twelve.utils.NetworkUtils;
import com.newstoday.news_package.news_category.twelve.provider.FeedData.EntryColumns;
import com.newstoday.news_package.news_category.twelve.provider.FeedData.FeedColumns;
import com.newstoday.rssfeedreader.utils.StringUtils;
import com.squareup.picasso.Picasso;

public class EntriesCursorAdapter extends ResourceCursorAdapter {

    private int mIdPos;
    private int mTitlePos;
    private int mMainImgPos;
    private int mDatePos;
    private int mFavoritePos;

    public EntriesCursorAdapter(Context context, Cursor cursor) {
        super(context, R.layout.news_list_item_layout, cursor, 0);

        reinit(cursor);
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder.titleTextView != null) {
            String titleText = cursor.getString(mTitlePos);
            holder.titleTextView.setText(titleText);
            final long entryID = cursor.getLong(mIdPos);
            holder.entryID = entryID;
            String mainImgUrl = cursor.getString(mMainImgPos);
            mainImgUrl = TextUtils.isEmpty(mainImgUrl) ? null : NetworkUtils.getDownloadedOrDistantImageUrl(entryID, mainImgUrl);

            if (mainImgUrl != null) {
                holder.imageCard.setVisibility(View.VISIBLE);
                Picasso.get().load(mainImgUrl).resize(400, 300).into(holder.mainImgView);
            } else {
                holder.imageCard.setVisibility(View.GONE);
            }

            holder.isFavorite = cursor.getInt(mFavoritePos) == 1;

            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(cursor.getLong(mDatePos), now, DateUtils.SECOND_IN_MILLIS);
            String pub = ago.toString();
            if (pub.startsWith("in") || pub.equals("Jan 1, 1970") | pub.equals("1 Jan 1970")) {
                holder.dateTextView.setText(StringUtils.getDateTimeString(cursor.getLong(mDatePos)));
            } else {
                holder.dateTextView.setText(pub);
            }
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        View view;
        int type = cursor.getPosition();
        String adPosition = String.valueOf(type);
        if (adPosition.endsWith("3")) {
            String mainImgUrl = cursor.getString(cursor.getPosition());
            if (mainImgUrl != null) {
                view = LayoutInflater.from(context).inflate(R.layout.news_list_item_layout_second, parent, false);
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.news_list_item_layout, parent, false);
            }
        } else if (adPosition.equals("5")) {
            String mainImgUrl = cursor.getString(cursor.getPosition());
            if (mainImgUrl != null) {
                view = LayoutInflater.from(context).inflate(R.layout.news_list_item_layout_second, parent, false);
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.news_list_item_layout, parent, false);
            }
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.news_list_item_layout, parent, false);
        }
        holder.imageCard = view.findViewById(R.id.image_card);
        holder.titleTextView = view.findViewById(R.id.title);
        holder.dateTextView = view.findViewById(R.id.pubdate);
        holder.mainImgView = view.findViewById(R.id.image);
        holder.frameLayout = view.findViewById(R.id.adFrame);

        view.setTag(holder);
        return view;
    }

    @Override
    public void changeCursor(Cursor cursor) {
        reinit(cursor);
        super.changeCursor(cursor);
    }

    @Override
    public Cursor swapCursor(Cursor newCursor) {
        reinit(newCursor);
        return super.swapCursor(newCursor);
    }

    @Override
    public void notifyDataSetChanged() {
        reinit(null);
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        reinit(null);
        super.notifyDataSetInvalidated();
    }

    private void reinit(Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            mIdPos = cursor.getColumnIndex(EntryColumns._ID);
            mTitlePos = cursor.getColumnIndex(EntryColumns.TITLE);
            mMainImgPos = cursor.getColumnIndex(EntryColumns.IMAGE_URL);
            mDatePos = cursor.getColumnIndex(EntryColumns.DATE);
            mFavoritePos = cursor.getColumnIndex(EntryColumns.IS_FAVORITE);
            int mFeedNamePos = cursor.getColumnIndex(FeedColumns.NAME);
        }
    }

    private static class ViewHolder {
        FrameLayout frameLayout;
        CardView imageCard;
        TextView dateTextView, titleTextView;
        ImageView mainImgView;
        boolean isFavorite;
        long entryID = -1;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }

}
