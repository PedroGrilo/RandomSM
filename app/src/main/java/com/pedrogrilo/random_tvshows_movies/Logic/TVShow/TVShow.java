package com.pedrogrilo.random_tvshows_movies.Logic.TVShow;

public interface TVShow extends com.pedrogrilo.random_tvshows_movies.Logic.Entity.Entity {
    String getStatus();

    void setStatus(String status);

    int getNumberOfEpisodes();

    void setNumberOfEpisodes(int numberOfEpisodes);

    int getNumberOfSeasons();

    void setNumberOfSeasons(int numberOfSeasons);
}
