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

package com.newstoday.Recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newstoday.items.NewsItem;
import com.newstoday.nepali.news.R;
import com.newstoday.services.ChromeOpener;
import com.newstoday.services.InternetIsConnected;

import java.util.List;

public class SocialMedia_Recycler_Adapter extends RecyclerView.Adapter<SocialMedia_Recycler_Adapter.SocialMedia_Viewholder> {

    private final List<NewsItem.News.SocialMedia> socialMedia;
    private final Context context;

    public SocialMedia_Recycler_Adapter(Context context, List<NewsItem.News.SocialMedia> socialMedia) {
        this.socialMedia = socialMedia;
        this.context = context;
    }

    @NonNull
    @Override
    public SocialMedia_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.socialmedia_item_layout, parent, false);
        return new SocialMedia_Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialMedia_Viewholder holder, int position) {
        holder.socialTitle.setText(socialMedia.get(position).siteName);
        Glide.with(context).load(socialMedia.get(position).siteImage).into(holder.socialImage);
        final String link = socialMedia.get(position).siteLink;
        holder.socialLinear.setOnClickListener(v -> {
            InternetIsConnected isConnected = new InternetIsConnected();
            if (isConnected.internetIsConnected()) {
                ChromeOpener opener = new ChromeOpener();
                opener.openLink(context, link);
            } else {
                Toast.makeText(context, context.getResources().getString(R.string.noConnection), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return socialMedia.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    static class SocialMedia_Viewholder extends RecyclerView.ViewHolder {
        final LinearLayout socialLinear;
        final ImageView socialImage;
        final TextView socialTitle;

        SocialMedia_Viewholder(View view) {
            super(view);
            socialLinear = view.findViewById(R.id.socialLinear);
            socialImage = view.findViewById(R.id.socialImage);
            socialTitle = view.findViewById(R.id.socialTitle);
        }
    }
}
