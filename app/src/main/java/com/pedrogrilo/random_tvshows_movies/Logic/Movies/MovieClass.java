package com.pedrogrilo.random_tvshows_movies.Logic.Movies;

import com.pedrogrilo.random_tvshows_movies.Logic.Entity.Entity;
import com.pedrogrilo.random_tvshows_movies.Logic.Entity.EntityClass;
import com.pedrogrilo.random_tvshows_movies.Logic.Request.RequestVideo;

public class MovieClass extends EntityClass implements Movie {

    private String release_date;

    public MovieClass(int id, String title, String[] genres, String overview, boolean adultEntity, RequestVideo video, String release_date) {
        super(id, title, genres, overview, adultEntity, video);
        this.release_date = release_date;
    }

    @Override
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String getRelease_date() {
        return release_date;
    }
}
