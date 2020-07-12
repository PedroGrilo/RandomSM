package com.pedrogrilo.random_tvshows_movies.Logic.User;

import com.pedrogrilo.random_tvshows_movies.Logic.Movies.Movie;
import com.pedrogrilo.random_tvshows_movies.Logic.TVShow.TVShow;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Phaser;

public class UserClass implements User {

    private UUID id;
    private String email;
    private String password;
    private Date createdAt;
    private HashSet<Movie> movies;
    private HashSet<TVShow> tvShows;

    public UserClass(String email, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.movies = new HashSet<Movie>();
        this.tvShows = new HashSet<TVShow>();
    }

    public UserClass(String email){
        new UserClass(email,"ExternalLogin");
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public HashSet<Movie> getMovies() {
        return movies;
    }

    @Override
    public void setMovies(HashSet<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    @Override
    public void removeMovie(Movie movie){
        this.movies.remove(movie);
    }

    @Override
    public HashSet<TVShow> getTvShows() {
        return tvShows;
    }

    @Override
    public void setTvShows(HashSet<TVShow> tvShows) {
        this.tvShows = tvShows;
    }

    @Override
    public void addTvShow(TVShow tvShow){
        this.tvShows.add(tvShow);
    }

    @Override
    public void removeTvShow(TVShow tvShow){
        this.tvShows.remove(tvShow);
    }
}
