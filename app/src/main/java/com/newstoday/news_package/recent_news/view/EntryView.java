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

package com.newstoday.news_package.recent_news.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.newstoday.R;
import com.newstoday.rssfeedreader.utils.StringUtils;
import com.newstoday.services.ChromeOpener;
import com.newstoday.services.SlideAd_Service;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class EntryView extends ConstraintLayout {

    private InterstitialAd mInterstitialAd;

    public EntryView(Context context) {
        super(context);
        init();
    }

    public EntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EntryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setHtml(Activity activity, String title, String link, String contentText, long timestamp) {
        TextView title_text = findViewById(R.id.title);
        TextView pubDate = findViewById(R.id.pubdate);
        ImageView image = findViewById(R.id.image);
        TextView source = findViewById(R.id.source);
        TextView description = findViewById(R.id.description);
        CardView readFullContent = findViewById(R.id.readFullContent);
        title_text.setText(title);

        long now = System.currentTimeMillis();
        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(timestamp, now, DateUtils.SECOND_IN_MILLIS);
        String pub = ago.toString();
        if (pub.startsWith("in") || pub.equals("Jan 1, 1970") | pub.equals("1 Jan 1970")) {
            pubDate.setText(StringUtils.getDateTimeString(timestamp));
        } else {
            pubDate.setText(pub);
        }
        description.setText(Html.fromHtml(contentText.replaceAll("<img.+?>|<a.+?>", "\n\n"), null, null));

        if (description.length() < 1000) {
            description.setMaxLines(7);
        }

        try {
            source.setText(getDomainName(link));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Document doc = Jsoup.parse(contentText);
        String imagelink = doc.getElementsByTag("img").attr("src");
        if (!imagelink.equals("")) {
            image.setVisibility(View.VISIBLE);
            Picasso.get().load(imagelink).placeholder(R.drawable.placeholder).resize(400, 300).into(image);
        }

        readFullContent.setOnClickListener(v -> {
            mInterstitialAd = new InterstitialAd(getContext());
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            int slideAD = sharedPreferences.getInt("WEBSITE_CLICK", 0) + 1;
            SlideAd_Service.putWEBSITE_CLICK(getContext(), slideAD);
            if (slideAD >= 15) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    SlideAd_Service.putWEBSITE_CLICK(getContext(), 0);
                } else {
                    SlideAd_Service.putWEBSITE_CLICK(getContext(), slideAD);
                    mInterstitialAd = new InterstitialAd(getContext());
                    mInterstitialAd.setAdUnitId(getResources().getString(R.string.intrestial_ad));
                    mInterstitialAd.loadAd(new AdRequest.Builder().addKeyword("Insurance").build());
                    mInterstitialAd.show();
                }
            }
            ChromeOpener opener = new ChromeOpener();
            opener.openLink(activity, link);
        });

    }

    private static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith(" ") ? domain.substring(4) : domain;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.news_detail_news_layout, this, true);
        RelativeLayout adBackground = v.findViewById(R.id.ad_background);
        AdLoader adLoader = new AdLoader.Builder(Objects.requireNonNull(getContext()), getContext().getString(R.string.native_ad))
                .forUnifiedNativeAd(unifiedNativeAd -> {
                    FrameLayout frameLayout =
                            v.findViewById(R.id.adFrame);
                    try {
                        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        UnifiedNativeAdView adView = (UnifiedNativeAdView) Objects.requireNonNull(inflater).inflate(R.layout.aa_news_native, null);
                        populateUnifiedNativeAdView(adBackground, unifiedNativeAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        adBackground.setVisibility(GONE);
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();
        adLoader.loadAd(new AdRequest.Builder().build());
    }

    private void populateUnifiedNativeAdView(RelativeLayout adBackground, UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        adBackground.setVisibility(GONE);
        adView.setMediaView(adView.findViewById(R.id.native_ad_media_view));
        adView.setHeadlineView(adView.findViewById(R.id.native_ad_headline));
        adView.setCallToActionView(adView.findViewById(R.id.native_ad_call_to_action_button));
        adView.setBodyView(adView.findViewById(R.id.native_ad_body));

        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }


        if (nativeAd.getHeadline() == null) {
            adView.getHeadlineView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
            adView.getHeadlineView().setVisibility(View.VISIBLE);
        }
        adView.setNativeAd(nativeAd);

    }

}