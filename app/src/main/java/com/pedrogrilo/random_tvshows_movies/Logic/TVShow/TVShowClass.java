package com.pedrogrilo.random_tvshows_movies.Logic.TVShow;

import com.pedrogrilo.random_tvshows_movies.Logic.Entity.EntityClass;
import com.pedrogrilo.random_tvshows_movies.Logic.Request.RequestVideo;

import java.util.Objects;

public class TVShowClass extends EntityClass implements TVShow {

    private String status;
    private int numberOfEpisodes;
    private int numberOfSeasons;

    public TVShowClass(int id, String title, String[] genres, String overview, boolean adultEntity, RequestVideo video, String status, int numberOfEpisodes, int numberOfSeasons) {
        super(id, title, genres, overview, adultEntity, video);
        this.status = status;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    @Override
    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    @Override
    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

}
