package com.newstoday.radio.radio_favorites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.newstoday.nepali.news.R;
import com.newstoday.radio.All_Radio_Fragment;
import com.newstoday.radio.Next_Prev_Callback;
import com.newstoday.radio.radio_recent.Recent_Radio_Adapter;
import com.newstoday.radio.radio_recent.Recent_Radio_Items;
import com.newstoday.radio.radioplayer_service.PlaybackStatus;
import com.newstoday.radio.radioplayer_service.RadioManager;
import com.newstoday.radio.radioplayer_service.RadioService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Fav_Detail_Fragment extends Fragment {
    private ProgressBar frag_progress;
    private int currentPage;
    private Favorites_Radio_Items radioItems;
    private ImageView radioImage, frag_prev, frag_next, fragplay_pause, frag_share, frag_fav;
    private TextView radioName, radioDetail;
    private RadioManager radioManager;
    private Next_Prev_Callback callback;
    private Bitmap bitmap;
    private RecyclerView recently_played;

    public Fav_Detail_Fragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.currentPage = getArguments().getInt("current_page", 0);
        }
    }

    private void load() {
        List<Recent_Radio_Items> recentRadioItems = All_Radio_Fragment.recentDatabase.favoriteDao().getFavoriteData();
        Collections.reverse(recentRadioItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recently_played.setLayoutManager(layoutManager);
        Recent_Radio_Adapter adapter = new Recent_Radio_Adapter(this.getContext(), recentRadioItems);
        recently_played.setAdapter(adapter);
    }

    private void setRadioView() {
        this.radioName.setText(this.radioItems.stationName);
        this.radioDetail.setText(this.radioItems.stationDetail);
        Glide.with(this)
                .asBitmap()
                .load(this.radioItems.stationimage)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        bitmap = resource;
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        Glide.with(Objects.requireNonNull(getActivity()))
                .load(this.radioItems.stationimage)
                .into(this.radioImage);
        fragplay_pause.setOnClickListener(v -> {
            Recent_Radio_Items favoriteList = new Recent_Radio_Items();
            favoriteList.setStationName(radioItems.stationName);
            favoriteList.setStationDetail(radioItems.stationDetail);
            favoriteList.setStationimage(radioItems.stationimage);
            favoriteList.setStationLink(radioItems.stationLink);
            favoriteList.setStationLocation(radioItems.stationLocation);
            if (All_Radio_Fragment.recentDatabase.favoriteDao().isFavorite(radioItems.stationName) != 1) {
                All_Radio_Fragment.recentDatabase.favoriteDao().addData(favoriteList);
            } else {
                All_Radio_Fragment.recentDatabase.favoriteDao().delete(favoriteList);
                All_Radio_Fragment.recentDatabase.favoriteDao().addData(favoriteList);
            }
            if (RadioManager.getService().isPlaying()) {
                if (RadioService.current_Url != null) {
                    if (RadioService.current_Url.equals(Fav_Detail_Fragment.this.radioItems.stationLink)) {
                        radioManager.pause();
                    } else {
                        radioManager.pause();
                        radioManager.play(Fav_Detail_Fragment.this.radioItems.stationLink, bitmap, this.radioItems.stationName,
                                this.radioItems.stationDetail, this.radioItems.stationimage);
                    }
                } else {
                    radioManager.play(Fav_Detail_Fragment.this.radioItems.stationLink, bitmap, this.radioItems.stationName,
                            this.radioItems.stationDetail, this.radioItems.stationimage);
                }
            } else {
                if (RadioService.current_Url != null) {
                    if (RadioService.current_Url.equals(Fav_Detail_Fragment.this.radioItems.stationLink)) {
                        radioManager.resume();
                    } else {
                        radioManager.play(Fav_Detail_Fragment.this.radioItems.stationLink, bitmap, this.radioItems.stationName,
                                this.radioItems.stationDetail, this.radioItems.stationimage);
                    }
                } else {
                    radioManager.play(Fav_Detail_Fragment.this.radioItems.stationLink, bitmap, this.radioItems.stationName,
                            this.radioItems.stationDetail, this.radioItems.stationimage);
                }
            }
        });
        frag_share.setOnClickListener(v -> {
            Intent txtIntent = new Intent(Intent.ACTION_SEND);
            txtIntent.setType("text/plain");
            txtIntent.putExtra(Intent.EXTRA_TEXT, "Listen " + Fav_Detail_Fragment.this.radioItems.stationName + " on this radio app.\n\n https://www.play.google.com/https://play.google.com/store/apps/details?id=" + Objects.requireNonNull(getActivity()).getPackageName());
            startActivity(Intent.createChooser(txtIntent, "Share"));
        });
        frag_next.setOnClickListener(v -> callback.nextcallback(currentPage));
        frag_prev.setOnClickListener(v -> callback.nextcallback(currentPage - 2));
        this.frag_fav.setImageResource(R.drawable.ic_heart_filled);
        this.frag_fav.setOnClickListener(v -> {
            Favorites_Radio_Items favoriteList = new Favorites_Radio_Items();
            favoriteList.setStationName(radioItems.stationName);
            favoriteList.setStationDetail(radioItems.stationDetail);
            favoriteList.setStationimage(radioItems.stationimage);
            favoriteList.setStationLink(radioItems.stationLink);
            favoriteList.setStationLocation(radioItems.stationLocation);
            if (All_Radio_Fragment.favoriteDatabase.favoriteDao().isFavorite(radioItems.stationName) != 1) {
                Toast.makeText(this.getContext(), this.getResources().getString(R.string.added_to_fav), Toast.LENGTH_SHORT).show();
                this.frag_fav.setImageResource(R.drawable.ic_heart_filled);
                All_Radio_Fragment.favoriteDatabase.favoriteDao().addData(favoriteList);
            } else {
                Toast.makeText(this.getContext(), this.getResources().getString(R.string.removed_from_fav), Toast.LENGTH_SHORT).show();
                this.frag_fav.setImageResource(R.drawable.ic_heart_empty);
                All_Radio_Fragment.favoriteDatabase.favoriteDao().delete(favoriteList);
            }
        });
        load();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radio_detail, container, false);
        this.frag_fav = v.findViewById(R.id.frag_fav);
        this.recently_played = v.findViewById(R.id.recently_played);
        this.radioName = v.findViewById(R.id.fragradio_name);
        this.frag_progress = v.findViewById(R.id.frag_progress);
        this.radioManager = RadioManager.with(this.getContext());
        this.radioDetail = v.findViewById(R.id.fragradio_detail);
        this.radioImage = v.findViewById(R.id.fragradio_image);
        this.frag_next = v.findViewById(R.id.frag_next);
        this.frag_prev = v.findViewById(R.id.frag_prev);
        this.fragplay_pause = v.findViewById(R.id.fragplay_pause);
        this.frag_share = v.findViewById(R.id.frag_share);
        this.radioItems = Favorite_Radio_Adapter.radioItems.get(this.currentPage - 1);
        if (getActivity() instanceof Next_Prev_Callback)
            callback = (Next_Prev_Callback) getActivity();

        setRadioView();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        try {
            radioManager.unbind();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            radioManager.bind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onEvent(String status) {
        switch (status) {
            case PlaybackStatus.LOADING:
                frag_progress.setVisibility(View.VISIBLE);
                break;
            case PlaybackStatus.ERROR:
                frag_progress.setVisibility(View.GONE);
                Toast.makeText(this.getContext(), R.string.stream_offline, Toast.LENGTH_SHORT).show();
                break;
            case PlaybackStatus.IDLE:
                frag_progress.setVisibility(View.GONE);
                break;
            case PlaybackStatus.PAUSED:
                frag_progress.setVisibility(View.GONE);
                Fav_Detail_Fragment.this.fragplay_pause.setImageResource(R.drawable.ic_play);
                break;
            case PlaybackStatus.PLAYING:
                frag_progress.setVisibility(View.GONE);
                if (RadioService.current_Url.equals(this.radioItems.stationLink)) {
                    Fav_Detail_Fragment.this.fragplay_pause.setImageResource(R.drawable.ic_pause);
                } else {
                    Fav_Detail_Fragment.this.fragplay_pause.setImageResource(R.drawable.ic_play);
                }
                break;

        }

    }
}
