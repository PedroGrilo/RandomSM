package com.pedrogrilo.random_tvshows_movies.Logic.Entity;

import com.pedrogrilo.random_tvshows_movies.Logic.Request.RequestVideo;

public interface Entity {
    boolean isAdultEntity();

    void setAdultEntity(boolean adultEntity);

    int getId();

    String getTitle();

    void setTitle(String title);

    String[] getGenres();

    void setGenres(String[] genres);

    String getOverview();

    void setOverview(String overview);

    RequestVideo getVideo();

    void setVideo(RequestVideo video);
}
