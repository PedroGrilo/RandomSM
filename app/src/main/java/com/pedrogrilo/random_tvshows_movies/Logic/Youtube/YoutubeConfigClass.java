package com.pedrogrilo.random_tvshows_movies.Logic.Youtube;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeConfigClass extends YouTubeBaseActivity implements YoutubeConfigClassInterface {

    private static final String API_KEY = "AIzaSyDrDcTT8ndMti-vJgc4C5yo7nNt9CrJJm4";
    private static final String TAG = "YotubeConfig";
    YouTubePlayer.OnInitializedListener mOnInitializedListener;
    private String stringVideo;
    private YouTubePlayerView mYouTubePlayerView;
    private Button btnPlay;

    public YoutubeConfigClass(YouTubePlayerView PlayerView, Button buttonPlay, String stringVideo) {
        mYouTubePlayerView = PlayerView;
        btnPlay = buttonPlay;
        this.stringVideo = stringVideo;
        initializeYoutubePlay();
    }

    public static String getApiKey() {
        return API_KEY;
    }

    @Override
    public void initializeYoutubePlay() {
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess: Done");
                System.out.println("KEY :: " + getStringVideo());

                youTubePlayer.loadVideo(getStringVideo());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure: Fail");
            }
        };
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Initializing ");
                mYouTubePlayerView.initialize(getApiKey(), mOnInitializedListener);
                btnPlay.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public String getStringVideo() {
        return stringVideo;
    }

    @Override
    public void setStringVideo(String stringVideo) {
        this.stringVideo = stringVideo;
    }

    @Override
    public Button getBtnPlay() {
        return btnPlay;
    }

    @Override
    public void setBtnPlay(Button btnPlay) {
        this.btnPlay = btnPlay;
    }

    @Override
    public void setYotubeVideo(String sVideo) {
        stringVideo = sVideo;
    }
}
