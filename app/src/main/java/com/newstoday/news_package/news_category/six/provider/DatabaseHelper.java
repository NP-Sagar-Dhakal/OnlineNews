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

package com.newstoday.news_package.news_category.six.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.newstoday.news_package.news_category.six.provider.FeedData.EntryColumns;
import com.newstoday.news_package.news_category.six.provider.FeedData.FeedColumns;
import com.newstoday.news_package.news_category.six.provider.FeedData.FilterColumns;
import com.newstoday.news_package.news_category.six.provider.FeedData.TaskColumns;

import java.io.File;
import java.util.Objects;

import static com.newstoday.Constants.TYPE_BOOLEAN;
import static com.newstoday.Constants.TYPE_DATE_TIME;
import static com.newstoday.Constants.TYPE_TEXT;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cat_six.db";
    private static final int DATABASE_VERSION = 12;

    private static final String ALTER_TABLE = "ALTER TABLE ";
    private static final String ADD = " ADD ";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(createTable(FeedColumns.TABLE_NAME, FeedColumns.COLUMNS));
        database.execSQL(createTable(FilterColumns.TABLE_NAME, FilterColumns.COLUMNS));
        database.execSQL(createTable(EntryColumns.TABLE_NAME, EntryColumns.COLUMNS));
        database.execSQL(createTable(TaskColumns.TABLE_NAME, TaskColumns.COLUMNS));
    }

    private String createTable(String tableName, String[][] columns) {
        if (tableName == null || columns == null || columns.length == 0) {
            throw new IllegalArgumentException("Invalid parameters for creating table " + tableName);
        } else {
            StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
            stringBuilder.append(tableName);
            stringBuilder.append(" (");
            for (int n = 0, i = columns.length; n < i; n++) {
                if (n > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(columns[n][0]).append(' ').append(columns[n][1]);
            }
            return stringBuilder.append(");").toString();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.REAL_LAST_UPDATE + ' ' + TYPE_DATE_TIME);
        }
        if (oldVersion < 3) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.RETRIEVE_FULLTEXT + ' ' + TYPE_BOOLEAN);
        }
        if (oldVersion < 4) {
            executeCatchedSQL(database, createTable(TaskColumns.TABLE_NAME, TaskColumns.COLUMNS));
            // Remove old FeedEx directory (now useless)
            try {
                deleteFileOrDir(new File(Environment.getExternalStorageDirectory() + "/FeedEx/"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (oldVersion < 5) {
            executeCatchedSQL(database, ALTER_TABLE + TaskColumns.TABLE_NAME + ADD + "UNIQUE(" + TaskColumns.ENTRY_ID + ", " + TaskColumns.IMG_URL_TO_DL + ") ON CONFLICT IGNORE");
        }
        if (oldVersion < 6) {
            executeCatchedSQL(database, ALTER_TABLE + FilterColumns.TABLE_NAME + ADD + FilterColumns.IS_ACCEPT_RULE + ' ' + TYPE_BOOLEAN);
        }
        if (oldVersion < 7) {
            executeCatchedSQL(database, ALTER_TABLE + EntryColumns.TABLE_NAME + ADD + EntryColumns.FETCH_DATE + ' ' + TYPE_DATE_TIME);
        }
        if (oldVersion < 8) {
            executeCatchedSQL(database, ALTER_TABLE + EntryColumns.TABLE_NAME + ADD + EntryColumns.IMAGE_URL + ' ' + TYPE_TEXT);
        }
        if (oldVersion < 9) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.COOKIE_NAME + ' ' + TYPE_TEXT);
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.COOKIE_VALUE + ' ' + TYPE_TEXT);
        }
        if (oldVersion < 10) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.KEEP_TIME + ' ' + TYPE_DATE_TIME);
        }
        if (oldVersion < 11) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.HTTP_AUTH_LOGIN + ' ' + TYPE_TEXT);
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.HTTP_AUTH_PASSWORD + ' ' + TYPE_TEXT);
        }
        if (oldVersion < 12) {
            executeCatchedSQL(database, ALTER_TABLE + FeedColumns.TABLE_NAME + ADD + FeedColumns.IS_GROUP_EXPANDED + ' ' + TYPE_BOOLEAN);
        }
    }

    private void executeCatchedSQL(SQLiteDatabase database, String query) {
        try {
            database.execSQL(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteFileOrDir(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : Objects.requireNonNull(fileOrDirectory.listFiles()))
                deleteFileOrDir(child);

        fileOrDirectory.delete();
    }
}