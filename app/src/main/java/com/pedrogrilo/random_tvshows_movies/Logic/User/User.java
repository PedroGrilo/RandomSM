package com.pedrogrilo.random_tvshows_movies.Logic.User;

import com.pedrogrilo.random_tvshows_movies.Logic.Movies.Movie;
import com.pedrogrilo.random_tvshows_movies.Logic.TVShow.TVShow;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public interface User {
    UUID getId();

    void setId(UUID id);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    HashSet<Movie> getMovies();

    void setMovies(HashSet<Movie> movies);

    void addMovie(Movie movie);

    void removeMovie(Movie movie);

    HashSet<TVShow> getTvShows();

    void setTvShows(HashSet<TVShow> tvShows);

    void addTvShow(TVShow tvShow);

    void removeTvShow(TVShow tvShow);
}
