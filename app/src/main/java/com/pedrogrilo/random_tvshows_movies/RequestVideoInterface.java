package com.pedrogrilo.random_tvshows_movies;

public interface RequestVideoInterface {
    String getKeyMovie();

    void setKeyMovie(String keyMovie);

    void makeRequest();

    boolean isSuccessRequest();

    void setSuccessRequest(boolean successRequest);
}
