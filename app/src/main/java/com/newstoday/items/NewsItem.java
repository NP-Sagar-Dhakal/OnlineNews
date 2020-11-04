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

package com.newstoday.items;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItem {

    @SerializedName("News")
    public List<News> News;

    public static class News {
        @SerializedName("Categories")
        public List<Categories> Categories;
        @SerializedName("Location")
        public List<Location> Location;
        @SerializedName("SocialMedia")
        public List<SocialMedia> SocialMedia;
        @SerializedName("TopNewsSites")
        public List<TopNewsSites> TopNewsSites;
        @SerializedName("recentRssLinks")
        public List<RecentRssLinks> recentRssLinks;
        @SerializedName("OnlineRadios")
        public List<OnlineRadios> OnlineRadios;

        public static class Categories {
            @SerializedName("category1")
            public Category1 category1;
            @SerializedName("category2")
            public Category2 category2;
            @SerializedName("category3")
            public Category3 category3;
            @SerializedName("category4")
            public Category4 category4;
            @SerializedName("category5")
            public Category5 category5;
            @SerializedName("category6")
            public Category6 category6;
            @SerializedName("category7")
            public Category7 category7;
            @SerializedName("category8")
            public Category8 category8;
            @SerializedName("category9")
            public Category9 category9;
            @SerializedName("category10")
            public Category10 category10;
            @SerializedName("category11")
            public Category11 category11;
            @SerializedName("category12")
            public Category12 category12;
            @SerializedName("category13")
            public Category13 category13;
            @SerializedName("category14")
            public Category14 category14;
            @SerializedName("category15")
            public Category15 category15;

            public static class Category1 {
                @SerializedName("category1Name")
                public String category1Name;
                @SerializedName("category1NewsSites")
                public List<Category1NewsSites> category1NewsSites;
                @SerializedName("category1RssLinks")
                public List<Category1RssLinks> category1RssLinks;

                public static class Category1NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category1RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category2 {
                @SerializedName("category2Name")
                public String category2Name;
                @SerializedName("category2NewsSites")
                public List<Category2NewsSites> category2NewsSites;
                @SerializedName("category2RssLinks")
                public List<Category2RssLinks> category2RssLinks;

                public static class Category2NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category2RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category3 {
                @SerializedName("category3Name")
                public String category3Name;
                @SerializedName("category3NewsSites")
                public List<Category3NewsSites> category3NewsSites;
                @SerializedName("category3RssLinks")
                public List<Category3RssLinks> category3RssLinks;

                public static class Category3NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category3RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category4 {
                @SerializedName("category4Name")
                public String category4Name;
                @SerializedName("category4NewsSites")
                public List<Category4NewsSites> category4NewsSites;
                @SerializedName("category4RssLinks")
                public List<Category4RssLinks> category4RssLinks;

                public static class Category4NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category4RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category5 {
                @SerializedName("category5Name")
                public String category5Name;
                @SerializedName("category5NewsSites")
                public List<Category5NewsSites> category5NewsSites;
                @SerializedName("category5RssLinks")
                public List<Category5RssLinks> category5RssLinks;

                public static class Category5NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category5RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category6 {
                @SerializedName("category6Name")
                public String category6Name;
                @SerializedName("category6NewsSites")
                public List<Category6NewsSites> category6NewsSites;
                @SerializedName("category6RssLinks")
                public List<Category6RssLinks> category6RssLinks;

                public static class Category6NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category6RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category7 {
                @SerializedName("category7Name")
                public String category7Name;
                @SerializedName("category7NewsSites")
                public List<Category7NewsSites> category7NewsSites;
                @SerializedName("category7RssLinks")
                public List<Category7RssLinks> category7RssLinks;

                public static class Category7NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category7RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category8 {
                @SerializedName("category8Name")
                public String category8Name;
                @SerializedName("category8NewsSites")
                public List<Category8NewsSites> category8NewsSites;
                @SerializedName("category8RssLinks")
                public List<Category8RssLinks> category8RssLinks;

                public static class Category8NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category8RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category9 {
                @SerializedName("category9Name")
                public String category9Name;
                @SerializedName("category9NewsSites")
                public List<Category9NewsSites> category9NewsSites;
                @SerializedName("category9RssLinks")
                public List<Category9RssLinks> category9RssLinks;

                public static class Category9NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category9RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category10 {
                @SerializedName("category10Name")
                public String category10Name;
                @SerializedName("category10NewsSites")
                public List<Category10NewsSites> category10NewsSites;
                @SerializedName("category10RssLinks")
                public List<Category10RssLinks> category10RssLinks;

                public static class Category10NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category10RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category11 {
                @SerializedName("category11Name")
                public String category11Name;
                @SerializedName("category11NewsSites")
                public List<Category11NewsSites> category11NewsSites;
                @SerializedName("category11RssLinks")
                public List<Category11RssLinks> category11RssLinks;

                public static class Category11NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category11RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category12 {
                @SerializedName("category12Name")
                public String category12Name;
                @SerializedName("category12NewsSites")
                public List<Category12NewsSites> category12NewsSites;
                @SerializedName("category12RssLinks")
                public List<Category12RssLinks> category12RssLinks;

                public static class Category12NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category12RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category13 {
                @SerializedName("category13Name")
                public String category13Name;
                @SerializedName("category13NewsSites")
                public List<Category13NewsSites> category13NewsSites;
                @SerializedName("category13RssLinks")
                public List<Category13RssLinks> category13RssLinks;

                public static class Category13NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category13RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category14 {
                @SerializedName("category14Name")
                public String category14Name;
                @SerializedName("category14NewsSites")
                public List<Category14NewsSites> category14NewsSites;
                @SerializedName("category14RssLinks")
                public List<Category14RssLinks> category14RssLinks;

                public static class Category14NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category14RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Category15 {
                @SerializedName("category15Name")
                public String category15Name;
                @SerializedName("category15NewsSites")
                public List<Category15NewsSites> category15NewsSites;
                @SerializedName("category15RssLinks")
                public List<Category15RssLinks> category15RssLinks;

                public static class Category15NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Category15RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }
        }

        public static class Location {
            @SerializedName("location1")
            public Location1 location1;
            @SerializedName("location2")
            public Location2 location2;
            @SerializedName("location3")
            public Location3 location3;
            @SerializedName("location4")
            public Location4 location4;
            @SerializedName("location5")
            public Location5 location5;
            @SerializedName("location6")
            public Location6 location6;
            @SerializedName("location7")
            public Location7 location7;
            @SerializedName("location8")
            public Location8 location8;
            @SerializedName("location9")
            public Location9 location9;
            @SerializedName("location10")
            public Location10 location10;
            @SerializedName("location11")
            public Location11 location11;
            @SerializedName("location12")
            public Location12 location12;
            @SerializedName("location13")
            public Location13 location13;
            @SerializedName("location14")
            public Location14 location14;
            @SerializedName("location15")
            public Location15 location15;
            @SerializedName("location16")
            public Location16 location16;
            @SerializedName("location17")
            public Location17 location17;
            @SerializedName("location18")
            public Location18 location18;
            @SerializedName("location19")
            public Location19 location19;
            @SerializedName("location20")
            public Location20 location20;
            @SerializedName("location21")
            public Location21 location21;
            @SerializedName("location22")
            public Location22 location22;
            @SerializedName("location23")
            public Location23 location23;
            @SerializedName("location24")
            public Location24 location24;
            @SerializedName("location25")
            public Location25 location25;
            @SerializedName("location26")
            public Location26 location26;
            @SerializedName("location27")
            public Location27 location27;
            @SerializedName("location28")
            public Location28 location28;
            @SerializedName("location29")
            public Location29 location29;
            @SerializedName("location30")
            public Location30 location30;

            public static class Location1 {
                @SerializedName("location1Name")
                public String location1Name;
                @SerializedName("location1NewsSites")
                public List<Location1NewsSites> location1NewsSites;
                @SerializedName("location1RssLinks")
                public List<Location1RssLinks> location1RssLinks;

                public static class Location1NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location1RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location2 {
                @SerializedName("location2Name")
                public String location2Name;
                @SerializedName("location2NewsSites")
                public List<Location2NewsSites> location2NewsSites;
                @SerializedName("location2RssLinks")
                public List<Location2RssLinks> location2RssLinks;

                public static class Location2NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location2RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location3 {
                @SerializedName("location3Name")
                public String location3Name;
                @SerializedName("location3NewsSites")
                public List<Location3NewsSites> location3NewsSites;
                @SerializedName("location3RssLinks")
                public List<Location3RssLinks> location3RssLinks;

                public static class Location3NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location3RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location4 {
                @SerializedName("location4Name")
                public String location4Name;
                @SerializedName("location4NewsSites")
                public List<Location4NewsSites> location4NewsSites;
                @SerializedName("location4RssLinks")
                public List<Location4RssLinks> location4RssLinks;

                public static class Location4NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location4RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location5 {
                @SerializedName("location5Name")
                public String location5Name;
                @SerializedName("location5NewsSites")
                public List<Location5NewsSites> location5NewsSites;
                @SerializedName("location5RssLinks")
                public List<Location5RssLinks> location5RssLinks;

                public static class Location5NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location5RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location6 {
                @SerializedName("location6Name")
                public String location6Name;
                @SerializedName("location6NewsSites")
                public List<Location6NewsSites> location6NewsSites;
                @SerializedName("location6RssLinks")
                public List<Location6RssLinks> location6RssLinks;

                public static class Location6NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location6RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location7 {
                @SerializedName("location7Name")
                public String location7Name;
                @SerializedName("location7NewsSites")
                public List<Location7NewsSites> location7NewsSites;
                @SerializedName("location7RssLinks")
                public List<Location7RssLinks> location7RssLinks;

                public static class Location7NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location7RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location8 {
                @SerializedName("location8Name")
                public String location8Name;
                @SerializedName("location8NewsSites")
                public List<Location8NewsSites> location8NewsSites;
                @SerializedName("location8RssLinks")
                public List<Location8RssLinks> location8RssLinks;

                public static class Location8NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location8RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location9 {
                @SerializedName("location9Name")
                public String location9Name;
                @SerializedName("location9NewsSites")
                public List<Location9NewsSites> location9NewsSites;
                @SerializedName("location9RssLinks")
                public List<Location9RssLinks> location9RssLinks;

                public static class Location9NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location9RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location10 {
                @SerializedName("location10Name")
                public String location10Name;
                @SerializedName("location10NewsSites")
                public List<Location10NewsSites> location10NewsSites;
                @SerializedName("location10RssLinks")
                public List<Location10RssLinks> location10RssLinks;

                public static class Location10NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location10RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location11 {
                @SerializedName("location11Name")
                public String location11Name;
                @SerializedName("location11NewsSites")
                public List<Location11NewsSites> location11NewsSites;
                @SerializedName("location11RssLinks")
                public List<Location11RssLinks> location11RssLinks;

                public static class Location11NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location11RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location12 {
                @SerializedName("location12Name")
                public String location12Name;
                @SerializedName("location12NewsSites")
                public List<Location12NewsSites> location12NewsSites;
                @SerializedName("location12RssLinks")
                public List<Location12RssLinks> location12RssLinks;

                public static class Location12NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location12RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location13 {
                @SerializedName("location13Name")
                public String location13Name;
                @SerializedName("location13NewsSites")
                public List<Location13NewsSites> location13NewsSites;
                @SerializedName("location13RssLinks")
                public List<Location13RssLinks> location13RssLinks;

                public static class Location13NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location13RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location14 {
                @SerializedName("location14Name")
                public String location14Name;
                @SerializedName("location14NewsSites")
                public List<Location14NewsSites> location14NewsSites;
                @SerializedName("location14RssLinks")
                public List<Location14RssLinks> location14RssLinks;

                public static class Location14NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location14RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location15 {
                @SerializedName("location15Name")
                public String location15Name;
                @SerializedName("location15NewsSites")
                public List<Location15NewsSites> location15NewsSites;
                @SerializedName("location15RssLinks")
                public List<Location15RssLinks> location15RssLinks;

                public static class Location15NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location15RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location16 {
                @SerializedName("location16Name")
                public String location16Name;
                @SerializedName("location16NewsSites")
                public List<Location16NewsSites> location16NewsSites;
                @SerializedName("location16RssLinks")
                public List<Location16RssLinks> location16RssLinks;

                public static class Location16NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location16RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location17 {
                @SerializedName("location17Name")
                public String location17Name;
                @SerializedName("location17NewsSites")
                public List<Location17NewsSites> location17NewsSites;
                @SerializedName("location17RssLinks")
                public List<Location17RssLinks> location17RssLinks;

                public static class Location17NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location17RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location18 {
                @SerializedName("location18Name")
                public String location18Name;
                @SerializedName("location18NewsSites")
                public List<Location18NewsSites> location18NewsSites;
                @SerializedName("location18RssLinks")
                public List<Location18RssLinks> location18RssLinks;

                public static class Location18NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location18RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location19 {
                @SerializedName("location19Name")
                public String location19Name;
                @SerializedName("location19NewsSites")
                public List<Location19NewsSites> location19NewsSites;
                @SerializedName("location19RssLinks")
                public List<Location19RssLinks> location19RssLinks;

                public static class Location19NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location19RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location20 {
                @SerializedName("location20Name")
                public String location20Name;
                @SerializedName("location20NewsSites")
                public List<Location20NewsSites> location20NewsSites;
                @SerializedName("location20RssLinks")
                public List<Location20RssLinks> location20RssLinks;

                public static class Location20NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location20RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location21 {
                @SerializedName("location21Name")
                public String location21Name;
                @SerializedName("location21NewsSites")
                public List<Location21NewsSites> location21NewsSites;
                @SerializedName("location21RssLinks")
                public List<Location21RssLinks> location21RssLinks;

                public static class Location21NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location21RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location22 {
                @SerializedName("location22Name")
                public String location22Name;
                @SerializedName("location22NewsSites")
                public List<Location22NewsSites> location22NewsSites;
                @SerializedName("location22RssLinks")
                public List<Location22RssLinks> location22RssLinks;

                public static class Location22NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location22RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location23 {
                @SerializedName("location23Name")
                public String location23Name;
                @SerializedName("location23NewsSites")
                public List<Location23NewsSites> location23NewsSites;
                @SerializedName("location23RssLinks")
                public List<Location23RssLinks> location23RssLinks;

                public static class Location23NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location23RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location24 {
                @SerializedName("location24Name")
                public String location24Name;
                @SerializedName("location24NewsSites")
                public List<Location24NewsSites> location24NewsSites;
                @SerializedName("location24RssLinks")
                public List<Location24RssLinks> location24RssLinks;

                public static class Location24NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location24RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location25 {
                @SerializedName("location25Name")
                public String location25Name;
                @SerializedName("location25NewsSites")
                public List<Location25NewsSites> location25NewsSites;
                @SerializedName("location25RssLinks")
                public List<Location25RssLinks> location25RssLinks;

                public static class Location25NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location25RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location26 {
                @SerializedName("locatio26nName")
                public String locatio26nName;
                @SerializedName("location26NewsSites")
                public List<Location26NewsSites> location26NewsSites;
                @SerializedName("location26RssLinks")
                public List<Location26RssLinks> location26RssLinks;

                public static class Location26NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location26RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location27 {
                @SerializedName("location27Name")
                public String location27Name;
                @SerializedName("location27NewsSites")
                public List<Location27NewsSites> location27NewsSites;
                @SerializedName("location27RssLinks")
                public List<Location27RssLinks> location27RssLinks;

                public static class Location27NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location27RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location28 {
                @SerializedName("location28Name")
                public String location28Name;
                @SerializedName("location28NewsSites")
                public List<Location28NewsSites> location28NewsSites;
                @SerializedName("location28RssLinks")
                public List<Location28RssLinks> location28RssLinks;

                public static class Location28NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location28RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location29 {
                @SerializedName("location29Name")
                public String location29Name;
                @SerializedName("location29NewsSites")
                public List<Location29NewsSites> location29NewsSites;
                @SerializedName("location29RssLinks")
                public List<Location29RssLinks> location29RssLinks;

                public static class Location29NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location29RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }

            public static class Location30 {
                @SerializedName("location30Name")
                public String location30Name;
                @SerializedName("location30NewsSites")
                public List<Location30NewsSites> location30NewsSites;
                @SerializedName("location30RssLinks")
                public List<Location30RssLinks> location30RssLinks;

                public static class Location30NewsSites {
                    @SerializedName("siteImage")
                    public String siteImage;
                    @SerializedName("siteLink")
                    public String siteLink;
                    @SerializedName("siteName")
                    public String siteName;
                }

                public static class Location30RssLinks {
                    @SerializedName("rssLink1")
                    public String rssLink1;
                    @SerializedName("rssLink10")
                    public String rssLink10;
                    @SerializedName("rssLink11")
                    public String rssLink11;
                    @SerializedName("rssLink12")
                    public String rssLink12;
                    @SerializedName("rssLink13")
                    public String rssLink13;
                    @SerializedName("rssLink14")
                    public String rssLink14;
                    @SerializedName("rssLink15")
                    public String rssLink15;
                    @SerializedName("rssLink2")
                    public String rssLink2;
                    @SerializedName("rssLink3")
                    public String rssLink3;
                    @SerializedName("rssLink4")
                    public String rssLink4;
                    @SerializedName("rssLink5")
                    public String rssLink5;
                    @SerializedName("rssLink6")
                    public String rssLink6;
                    @SerializedName("rssLink7")
                    public String rssLink7;
                    @SerializedName("rssLink8")
                    public String rssLink8;
                    @SerializedName("rssLink9")
                    public String rssLink9;
                }
            }
        }

        public static class SocialMedia {
            @SerializedName("siteImage")
            public String siteImage;
            @SerializedName("siteLink")
            public String siteLink;
            @SerializedName("siteName")
            public String siteName;
        }

        public static class TopNewsSites {
            @SerializedName("siteImage")
            public String siteImage;
            @SerializedName("siteLink")
            public String siteLink;
            @SerializedName("siteName")
            public String siteName;
        }

        public static class RecentRssLinks {
            @SerializedName("rssLink1")
            public String rssLink1;
            @SerializedName("rssLink10")
            public String rssLink10;
            @SerializedName("rssLink11")
            public String rssLink11;
            @SerializedName("rssLink12")
            public String rssLink12;
            @SerializedName("rssLink13")
            public String rssLink13;
            @SerializedName("rssLink14")
            public String rssLink14;
            @SerializedName("rssLink15")
            public String rssLink15;
            @SerializedName("rssLink2")
            public String rssLink2;
            @SerializedName("rssLink3")
            public String rssLink3;
            @SerializedName("rssLink4")
            public String rssLink4;
            @SerializedName("rssLink5")
            public String rssLink5;
            @SerializedName("rssLink6")
            public String rssLink6;
            @SerializedName("rssLink7")
            public String rssLink7;
            @SerializedName("rssLink8")
            public String rssLink8;
            @SerializedName("rssLink9")
            public String rssLink9;
            @SerializedName("rssLink16")
            public String rssLink16;
            @SerializedName("rssLink17")
            public String rssLink17;
            @SerializedName("rssLink18")
            public String rssLink18;
            @SerializedName("rssLink19")
            public String rssLink19;
            @SerializedName("rssLink20")
            public String rssLink20;
        }

        public static class OnlineRadios {
            @SerializedName("stationDetail")
            public String stationDetail;
            @SerializedName("stationLink")
            public String stationLink;
            @SerializedName("stationLocation")
            public String stationLocation;
            @SerializedName("stationName")
            public String stationName;
            @SerializedName("stationimage")
            public String stationimage;
        }
    }
}
