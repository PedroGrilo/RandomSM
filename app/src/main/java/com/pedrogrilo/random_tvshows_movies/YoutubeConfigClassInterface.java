package com.pedrogrilo.random_tvshows_movies;

import android.widget.Button;

public interface YoutubeConfigClassInterface {
    void initializeYoutubePlay();

    String getStringVideo();

    void setStringVideo(String stringVideo);

    Button getBtnPlay();

    void setBtnPlay(Button btnPlay);

    void setYotubeVideo(String sVideo);
}
