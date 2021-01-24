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

package com.newstoday.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newstoday.R;
import com.newstoday.items.NewsItem;
import com.newstoday.services.ChromeOpener;

import java.util.List;

public class News_Sites_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context c;
    private final String data;

    private final List<NewsItem.News.TopNewsSites> topNewsSites;
    private final List<NewsItem.News.Categories.Category1.Category1NewsSites> category1NewsSites;
    private final List<NewsItem.News.Categories.Category2.Category2NewsSites> category2NewsSites;
    private final List<NewsItem.News.Categories.Category3.Category3NewsSites> category3NewsSites;
    private final List<NewsItem.News.Categories.Category4.Category4NewsSites> category4NewsSites;
    private final List<NewsItem.News.Categories.Category5.Category5NewsSites> category5NewsSites;
    private final List<NewsItem.News.Categories.Category6.Category6NewsSites> category6NewsSites;
    private final List<NewsItem.News.Categories.Category7.Category7NewsSites> category7NewsSites;
    private final List<NewsItem.News.Categories.Category8.Category8NewsSites> category8NewsSites;
    private final List<NewsItem.News.Categories.Category9.Category9NewsSites> category9NewsSites;
    private final List<NewsItem.News.Categories.Category10.Category10NewsSites> category10NewsSites;
    private final List<NewsItem.News.Categories.Category11.Category11NewsSites> category11NewsSites;
    private final List<NewsItem.News.Categories.Category12.Category12NewsSites> category12NewsSites;
    private final List<NewsItem.News.Categories.Category13.Category13NewsSites> category13NewsSites;
    private final List<NewsItem.News.Categories.Category14.Category14NewsSites> category14NewsSites;
    private final List<NewsItem.News.Categories.Category15.Category15NewsSites> category15NewsSites;

    private final List<NewsItem.News.Location.Location1.Location1NewsSites> location1NewsSites;
    private final List<NewsItem.News.Location.Location2.Location2NewsSites> location2NewsSites;
    private final List<NewsItem.News.Location.Location3.Location3NewsSites> location3NewsSites;
    private final List<NewsItem.News.Location.Location4.Location4NewsSites> location4NewsSites;
    private final List<NewsItem.News.Location.Location5.Location5NewsSites> location5NewsSites;
    private final List<NewsItem.News.Location.Location6.Location6NewsSites> location6NewsSites;
    private final List<NewsItem.News.Location.Location7.Location7NewsSites> location7NewsSites;
    private final List<NewsItem.News.Location.Location8.Location8NewsSites> location8NewsSites;
    private final List<NewsItem.News.Location.Location9.Location9NewsSites> location9NewsSites;
    private final List<NewsItem.News.Location.Location10.Location10NewsSites> location10NewsSites;
    private final List<NewsItem.News.Location.Location11.Location11NewsSites> location11NewsSites;
    private final List<NewsItem.News.Location.Location12.Location12NewsSites> location12NewsSites;
    private final List<NewsItem.News.Location.Location13.Location13NewsSites> location13NewsSites;
    private final List<NewsItem.News.Location.Location14.Location14NewsSites> location14NewsSites;
    private final List<NewsItem.News.Location.Location15.Location15NewsSites> location15NewsSites;
    private final List<NewsItem.News.Location.Location16.Location16NewsSites> location16NewsSites;
    private final List<NewsItem.News.Location.Location17.Location17NewsSites> location17NewsSites;
    private final List<NewsItem.News.Location.Location18.Location18NewsSites> location18NewsSites;
    private final List<NewsItem.News.Location.Location19.Location19NewsSites> location19NewsSites;
    private final List<NewsItem.News.Location.Location20.Location20NewsSites> location20NewsSites;
    private final List<NewsItem.News.Location.Location21.Location21NewsSites> location21NewsSites;
    private final List<NewsItem.News.Location.Location22.Location22NewsSites> location22NewsSites;
    private final List<NewsItem.News.Location.Location23.Location23NewsSites> location23NewsSites;
    private final List<NewsItem.News.Location.Location24.Location24NewsSites> location24NewsSites;
    private final List<NewsItem.News.Location.Location25.Location25NewsSites> location25NewsSites;
    private final List<NewsItem.News.Location.Location26.Location26NewsSites> location26NewsSites;
    private final List<NewsItem.News.Location.Location27.Location27NewsSites> location27NewsSites;
    private final List<NewsItem.News.Location.Location28.Location28NewsSites> location28NewsSites;
    private final List<NewsItem.News.Location.Location29.Location29NewsSites> location29NewsSites;
    private final List<NewsItem.News.Location.Location30.Location30NewsSites> location30NewsSites;

    public News_Sites_Adapter(Context c, String data, List<NewsItem.News.TopNewsSites> topNewsSites, List<NewsItem.News.Categories.Category1.Category1NewsSites> category1NewsSites,
                              List<NewsItem.News.Categories.Category2.Category2NewsSites> category2NewsSites,
                              List<NewsItem.News.Categories.Category3.Category3NewsSites> category3NewsSites,
                              List<NewsItem.News.Categories.Category4.Category4NewsSites> category4NewsSites,
                              List<NewsItem.News.Categories.Category5.Category5NewsSites> category5NewsSites,
                              List<NewsItem.News.Categories.Category6.Category6NewsSites> category6NewsSites,
                              List<NewsItem.News.Categories.Category7.Category7NewsSites> category7NewsSites,
                              List<NewsItem.News.Categories.Category8.Category8NewsSites> category8NewsSites,
                              List<NewsItem.News.Categories.Category9.Category9NewsSites> category9NewsSites,
                              List<NewsItem.News.Categories.Category10.Category10NewsSites> category10NewsSites,
                              List<NewsItem.News.Categories.Category11.Category11NewsSites> category11NewsSites,
                              List<NewsItem.News.Categories.Category12.Category12NewsSites> category12NewsSites,
                              List<NewsItem.News.Categories.Category13.Category13NewsSites> category13NewsSites,
                              List<NewsItem.News.Categories.Category14.Category14NewsSites> category14NewsSites,
                              List<NewsItem.News.Categories.Category15.Category15NewsSites> category15NewsSites,

                              List<NewsItem.News.Location.Location1.Location1NewsSites> location1NewsSites,
                              List<NewsItem.News.Location.Location2.Location2NewsSites> location2NewsSites,
                              List<NewsItem.News.Location.Location3.Location3NewsSites> location3NewsSites,
                              List<NewsItem.News.Location.Location4.Location4NewsSites> location4NewsSites,
                              List<NewsItem.News.Location.Location5.Location5NewsSites> location5NewsSites,
                              List<NewsItem.News.Location.Location6.Location6NewsSites> location6NewsSites,
                              List<NewsItem.News.Location.Location7.Location7NewsSites> location7NewsSites,
                              List<NewsItem.News.Location.Location8.Location8NewsSites> location8NewsSites,
                              List<NewsItem.News.Location.Location9.Location9NewsSites> location9NewsSites,
                              List<NewsItem.News.Location.Location10.Location10NewsSites> location10NewsSites,
                              List<NewsItem.News.Location.Location11.Location11NewsSites> location11NewsSites,
                              List<NewsItem.News.Location.Location12.Location12NewsSites> location12NewsSites,
                              List<NewsItem.News.Location.Location13.Location13NewsSites> location13NewsSites,
                              List<NewsItem.News.Location.Location14.Location14NewsSites> location14NewsSites,
                              List<NewsItem.News.Location.Location15.Location15NewsSites> location15NewsSites,
                              List<NewsItem.News.Location.Location16.Location16NewsSites> location16NewsSites,
                              List<NewsItem.News.Location.Location17.Location17NewsSites> location17NewsSites,
                              List<NewsItem.News.Location.Location18.Location18NewsSites> location18NewsSites,
                              List<NewsItem.News.Location.Location19.Location19NewsSites> location19NewsSites,
                              List<NewsItem.News.Location.Location20.Location20NewsSites> location20NewsSites,
                              List<NewsItem.News.Location.Location21.Location21NewsSites> location21NewsSites,
                              List<NewsItem.News.Location.Location22.Location22NewsSites> location22NewsSites,
                              List<NewsItem.News.Location.Location23.Location23NewsSites> location23NewsSites,
                              List<NewsItem.News.Location.Location24.Location24NewsSites> location24NewsSites,
                              List<NewsItem.News.Location.Location25.Location25NewsSites> location25NewsSites,
                              List<NewsItem.News.Location.Location26.Location26NewsSites> location26NewsSites,
                              List<NewsItem.News.Location.Location27.Location27NewsSites> location27NewsSites,
                              List<NewsItem.News.Location.Location28.Location28NewsSites> location28NewsSites,
                              List<NewsItem.News.Location.Location29.Location29NewsSites> location29NewsSites,
                              List<NewsItem.News.Location.Location30.Location30NewsSites> location30NewsSites) {
        this.c = c;
        this.data = data;
        this.topNewsSites = topNewsSites;
        this.category1NewsSites = category1NewsSites;
        this.category2NewsSites = category2NewsSites;
        this.category3NewsSites = category3NewsSites;
        this.category4NewsSites = category4NewsSites;
        this.category5NewsSites = category5NewsSites;
        this.category6NewsSites = category6NewsSites;
        this.category7NewsSites = category7NewsSites;
        this.category8NewsSites = category8NewsSites;
        this.category9NewsSites = category9NewsSites;
        this.category10NewsSites = category10NewsSites;
        this.category11NewsSites = category11NewsSites;
        this.category12NewsSites = category12NewsSites;
        this.category13NewsSites = category13NewsSites;
        this.category14NewsSites = category14NewsSites;
        this.category15NewsSites = category15NewsSites;

        this.location1NewsSites = location1NewsSites;
        this.location2NewsSites = location2NewsSites;
        this.location3NewsSites = location3NewsSites;
        this.location4NewsSites = location4NewsSites;
        this.location5NewsSites = location5NewsSites;
        this.location6NewsSites = location6NewsSites;
        this.location7NewsSites = location7NewsSites;
        this.location8NewsSites = location8NewsSites;
        this.location9NewsSites = location9NewsSites;
        this.location10NewsSites = location10NewsSites;
        this.location11NewsSites = location11NewsSites;
        this.location12NewsSites = location12NewsSites;
        this.location13NewsSites = location13NewsSites;
        this.location14NewsSites = location14NewsSites;
        this.location15NewsSites = location15NewsSites;
        this.location16NewsSites = location16NewsSites;
        this.location17NewsSites = location17NewsSites;
        this.location18NewsSites = location18NewsSites;
        this.location19NewsSites = location19NewsSites;
        this.location20NewsSites = location20NewsSites;
        this.location21NewsSites = location21NewsSites;
        this.location22NewsSites = location22NewsSites;
        this.location23NewsSites = location23NewsSites;
        this.location24NewsSites = location24NewsSites;
        this.location25NewsSites = location25NewsSites;
        this.location26NewsSites = location26NewsSites;
        this.location27NewsSites = location27NewsSites;
        this.location28NewsSites = location28NewsSites;
        this.location29NewsSites = location29NewsSites;
        this.location30NewsSites = location30NewsSites;

    }

    static class News_Sites_ViewHolder extends RecyclerView.ViewHolder {
        final ConstraintLayout newsParent;
        final ImageView siteImage;
        final TextView siteName;

        News_Sites_ViewHolder(View itemView) {
            super(itemView);
            newsParent = itemView.findViewById(R.id.newsParent);
            siteName = itemView.findViewById(R.id.siteName);
            siteImage = itemView.findViewById(R.id.siteImage);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.news_sites_item_layout, parent, false);
        return new News_Sites_ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        News_Sites_ViewHolder newsSitesViewHolder = (News_Sites_ViewHolder) holder;

        switch (data) {
            case "topNewsSites": {
                newsSitesViewHolder.siteName.setText(topNewsSites.get(position).siteName);
                String siteImage = topNewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = topNewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location1NewsSites": {
                newsSitesViewHolder.siteName.setText(location1NewsSites.get(position).siteName);
                String siteImage = location1NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location1NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location2NewsSites": {
                newsSitesViewHolder.siteName.setText(location2NewsSites.get(position).siteName);
                String siteImage = location2NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location2NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location3NewsSites": {
                newsSitesViewHolder.siteName.setText(location3NewsSites.get(position).siteName);
                String siteImage = location3NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location3NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location4NewsSites": {
                newsSitesViewHolder.siteName.setText(location4NewsSites.get(position).siteName);
                String siteImage = location4NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location4NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location5NewsSites": {
                newsSitesViewHolder.siteName.setText(location5NewsSites.get(position).siteName);
                String siteImage = location5NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location5NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location6NewsSites": {
                newsSitesViewHolder.siteName.setText(location6NewsSites.get(position).siteName);
                String siteImage = location6NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location6NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location7NewsSites": {
                newsSitesViewHolder.siteName.setText(location7NewsSites.get(position).siteName);
                String siteImage = location7NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location7NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location8NewsSites": {
                newsSitesViewHolder.siteName.setText(location8NewsSites.get(position).siteName);
                String siteImage = location8NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location8NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location9NewsSites": {
                newsSitesViewHolder.siteName.setText(location9NewsSites.get(position).siteName);
                String siteImage = location9NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location9NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location10NewsSites": {
                newsSitesViewHolder.siteName.setText(location10NewsSites.get(position).siteName);
                String siteImage = location10NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location10NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location11NewsSites": {
                newsSitesViewHolder.siteName.setText(location11NewsSites.get(position).siteName);
                String siteImage = location11NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location11NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location12NewsSites": {
                newsSitesViewHolder.siteName.setText(location12NewsSites.get(position).siteName);
                String siteImage = location12NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location12NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location13NewsSites": {
                newsSitesViewHolder.siteName.setText(location13NewsSites.get(position).siteName);
                String siteImage = location13NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location13NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location14NewsSites": {
                newsSitesViewHolder.siteName.setText(location14NewsSites.get(position).siteName);
                String siteImage = location14NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location14NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location15NewsSites": {
                newsSitesViewHolder.siteName.setText(location15NewsSites.get(position).siteName);
                String siteImage = location15NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location15NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location16NewsSites": {
                newsSitesViewHolder.siteName.setText(location16NewsSites.get(position).siteName);
                String siteImage = location16NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location16NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location17NewsSites": {
                newsSitesViewHolder.siteName.setText(location17NewsSites.get(position).siteName);
                String siteImage = location17NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location17NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location18NewsSites": {
                newsSitesViewHolder.siteName.setText(location18NewsSites.get(position).siteName);
                String siteImage = location18NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location18NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location19NewsSites": {
                newsSitesViewHolder.siteName.setText(location19NewsSites.get(position).siteName);
                String siteImage = location19NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location19NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location20NewsSites": {
                newsSitesViewHolder.siteName.setText(location20NewsSites.get(position).siteName);
                String siteImage = location20NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location20NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location21NewsSites": {
                newsSitesViewHolder.siteName.setText(location21NewsSites.get(position).siteName);
                String siteImage = location21NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location21NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location22NewsSites": {
                newsSitesViewHolder.siteName.setText(location22NewsSites.get(position).siteName);
                String siteImage = location22NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location22NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location23NewsSites": {
                newsSitesViewHolder.siteName.setText(location23NewsSites.get(position).siteName);
                String siteImage = location23NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location23NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location24NewsSites": {
                newsSitesViewHolder.siteName.setText(location24NewsSites.get(position).siteName);
                String siteImage = location24NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location24NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location25NewsSites": {
                newsSitesViewHolder.siteName.setText(location25NewsSites.get(position).siteName);
                String siteImage = location25NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location25NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location26NewsSites": {
                newsSitesViewHolder.siteName.setText(location26NewsSites.get(position).siteName);
                String siteImage = location26NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location26NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location27NewsSites": {
                newsSitesViewHolder.siteName.setText(location27NewsSites.get(position).siteName);
                String siteImage = location27NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location27NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location28NewsSites": {
                newsSitesViewHolder.siteName.setText(location28NewsSites.get(position).siteName);
                String siteImage = location28NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location28NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location29NewsSites": {
                newsSitesViewHolder.siteName.setText(location29NewsSites.get(position).siteName);
                String siteImage = location29NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location29NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "location30NewsSites": {
                newsSitesViewHolder.siteName.setText(location30NewsSites.get(position).siteName);
                String siteImage = location30NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = location30NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;

            }
            case "category1NewsSites": {
                newsSitesViewHolder.siteName.setText(category1NewsSites.get(position).siteName);
                String siteImage = category1NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category1NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category2NewsSites": {
                newsSitesViewHolder.siteName.setText(category2NewsSites.get(position).siteName);
                String siteImage = category2NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category2NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category3NewsSites": {
                newsSitesViewHolder.siteName.setText(category3NewsSites.get(position).siteName);
                String siteImage = category3NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category3NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category4NewsSites": {
                newsSitesViewHolder.siteName.setText(category4NewsSites.get(position).siteName);
                String siteImage = category4NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category4NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category5NewsSites": {
                newsSitesViewHolder.siteName.setText(category5NewsSites.get(position).siteName);
                String siteImage = category5NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category5NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category6NewsSites": {
                newsSitesViewHolder.siteName.setText(category6NewsSites.get(position).siteName);
                String siteImage = category6NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category6NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category7NewsSites": {
                newsSitesViewHolder.siteName.setText(category7NewsSites.get(position).siteName);
                String siteImage = category7NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category7NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category8NewsSites": {
                newsSitesViewHolder.siteName.setText(category8NewsSites.get(position).siteName);
                String siteImage = category8NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category8NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category9NewsSites": {
                newsSitesViewHolder.siteName.setText(category9NewsSites.get(position).siteName);
                String siteImage = category9NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category9NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category10NewsSites": {
                newsSitesViewHolder.siteName.setText(category10NewsSites.get(position).siteName);
                String siteImage = category10NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category10NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category11NewsSites": {
                newsSitesViewHolder.siteName.setText(category11NewsSites.get(position).siteName);
                String siteImage = category11NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category11NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category12NewsSites": {
                newsSitesViewHolder.siteName.setText(category12NewsSites.get(position).siteName);
                String siteImage = category12NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category12NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category13NewsSites": {
                newsSitesViewHolder.siteName.setText(category13NewsSites.get(position).siteName);
                String siteImage = category13NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category13NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category14NewsSites": {
                newsSitesViewHolder.siteName.setText(category14NewsSites.get(position).siteName);
                String siteImage = category14NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category14NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
            case "category15NewsSites": {
                newsSitesViewHolder.siteName.setText(category15NewsSites.get(position).siteName);
                String siteImage = category15NewsSites.get(position).siteImage;
                if (!siteImage.equals("")) {
                    Glide.with(c).load(siteImage).into(((News_Sites_ViewHolder) holder).siteImage);
                }
                newsSitesViewHolder.newsParent.setOnClickListener(v -> {
                    String link = category15NewsSites.get(position).siteLink;
                    startWebView(link);
                });
                break;
            }
        }
    }

    private void startWebView(String link) {
        ChromeOpener opener = new ChromeOpener();
        opener.openLink(c, link);
    }


    @Override
    public int getItemCount() {
        try {
            switch (data) {
                case "topNewsSites":
                    return topNewsSites.size();
                case "location1NewsSites":
                    return location1NewsSites.size();
                case "location2NewsSites":
                    return location2NewsSites.size();
                case "location3NewsSites":
                    return location3NewsSites.size();
                case "location4NewsSites":
                    return location4NewsSites.size();
                case "location5NewsSites":
                    return location5NewsSites.size();
                case "location6NewsSites":
                    return location6NewsSites.size();
                case "location7NewsSites":
                    return location7NewsSites.size();
                case "location8NewsSites":
                    return location8NewsSites.size();
                case "location9NewsSites":
                    return location9NewsSites.size();
                case "location10NewsSites":
                    return location10NewsSites.size();
                case "location11NewsSites":
                    return location11NewsSites.size();
                case "location12NewsSites":
                    return location12NewsSites.size();
                case "location13NewsSites":
                    return location13NewsSites.size();
                case "location14NewsSites":
                    return location14NewsSites.size();
                case "location15NewsSites":
                    return location15NewsSites.size();
                case "location16NewsSites":
                    return location16NewsSites.size();
                case "location17NewsSites":
                    return location17NewsSites.size();
                case "location18NewsSites":
                    return location18NewsSites.size();
                case "location19NewsSites":
                    return location19NewsSites.size();
                case "location20NewsSites":
                    return location20NewsSites.size();
                case "location21NewsSites":
                    return location21NewsSites.size();
                case "location22NewsSites":
                    return location22NewsSites.size();
                case "location23NewsSites":
                    return location23NewsSites.size();
                case "location24NewsSites":
                    return location24NewsSites.size();
                case "location25NewsSites":
                    return location25NewsSites.size();
                case "location26NewsSites":
                    return location26NewsSites.size();
                case "location27NewsSites":
                    return location27NewsSites.size();
                case "location28NewsSites":
                    return location28NewsSites.size();
                case "location29NewsSites":
                    return location29NewsSites.size();
                case "location30NewsSites":
                    return location30NewsSites.size();

                case "category1NewsSites":
                    return category1NewsSites.size();
                case "category2NewsSites":
                    return category2NewsSites.size();
                case "category3NewsSites":
                    return category3NewsSites.size();
                case "category4NewsSites":
                    return category4NewsSites.size();
                case "category5NewsSites":
                    return category5NewsSites.size();
                case "category6NewsSites":
                    return category6NewsSites.size();
                case "category7NewsSites":
                    return category7NewsSites.size();
                case "category8NewsSites":
                    return category8NewsSites.size();
                case "category9NewsSites":
                    return category9NewsSites.size();
                case "category10NewsSites":
                    return category10NewsSites.size();
                case "category11NewsSites":
                    return category11NewsSites.size();
                case "category12NewsSites":
                    return category12NewsSites.size();
                case "category13NewsSites":
                    return category13NewsSites.size();
                case "category14NewsSites":
                    return category14NewsSites.size();
                case "category15NewsSites":
                    return category15NewsSites.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
