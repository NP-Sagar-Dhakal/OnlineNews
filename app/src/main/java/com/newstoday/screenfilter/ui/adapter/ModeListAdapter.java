/*
  OnlineNews
  <p/>
  Copyright (c) 2019-2021 Sagar Dhakal
  Copyright (C) 2016 Paper Airplane Dev Team
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
package com.newstoday.screenfilter.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.newstoday.nepali.news.R;
import com.newstoday.screenfilter.Constants;

import java.util.ArrayList;
import java.util.List;

public class ModeListAdapter extends BaseAdapter implements ListAdapter {

    private static final List<ModeItem> MODES;

    static {
        MODES = new ArrayList<>();
        MODES.add(ModeItem.MODE_NORMAL);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            MODES.add(ModeItem.MODE_NO_PERMISSION);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            MODES.add(ModeItem.MODE_OVERLAY_ALL);
        }
    }

    private int current;

    public ModeListAdapter(int current) {
        this.current = current;
    }

    public void setCurrent(int current) {
        this.current = current;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return MODES.size();
    }

    @Override
    public ModeItem getItem(int position) {
        return MODES.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).modeId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.filter_item_list_mode, parent, false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.description = convertView.findViewById(R.id.description);
            holder.radioButton = convertView.findViewById(R.id.radio_button);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(getItem(position).titleResId);
        holder.description.setText(getItem(position).descResId);
        holder.radioButton.setChecked(position == current);
        return convertView;
    }

    private static class ViewHolder {

        TextView title, description;
        RadioButton radioButton;

    }

    public static class ModeItem {

        private static final ModeItem MODE_NORMAL = new ModeItem(
                Constants.AdvancedMode.NONE,
                R.string.mode_text_normal,
                R.string.mode_desc_normal);
        private static final ModeItem MODE_NO_PERMISSION = new ModeItem(
                Constants.AdvancedMode.NO_PERMISSION,
                R.string.mode_text_no_permission,
                R.string.mode_desc_no_permission);
        private static final ModeItem MODE_OVERLAY_ALL = new ModeItem(
                Constants.AdvancedMode.OVERLAY_ALL,
                R.string.mode_text_overlay_all,
                R.string.mode_desc_overlay_all);
        private final int modeId;
        private final int titleResId;
        private final int descResId;

        ModeItem(int modeId, int titleResId, int descResId) {
            this.modeId = modeId;
            this.titleResId = titleResId;
            this.descResId = descResId;
        }

        public int getModeId() {
            return this.modeId;
        }

    }

}
