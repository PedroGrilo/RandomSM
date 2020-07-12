package com.pedrogrilo.random_tvshows_movies.Logic.Entity;

import com.pedrogrilo.random_tvshows_movies.Logic.Request.RequestVideo;

import java.util.Objects;

public class EntityClass implements com.pedrogrilo.random_tvshows_movies.Logic.Entity.Entity {

    private int id;
    private String title;
    private String[] genres;
    private String overview;
    private RequestVideo video;
    private boolean adultEntity;

    public EntityClass(int id,String title, String[] genres, String overview, boolean adultEntity, RequestVideo video) {
        this.title = title;
        this.genres = genres;
        this.overview = overview;
        this.video = video;
        this.id = id;
        this.adultEntity = adultEntity;
    }

    @Override
    public boolean isAdultEntity() {
        return adultEntity;
    }

    @Override
    public void setAdultEntity(boolean adultEntity) {
        this.adultEntity = adultEntity;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String[] getGenres() {
        return genres;
    }

    @Override
    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public RequestVideo getVideo() {
        return video;
    }

    @Override
    public void setVideo(RequestVideo video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityClass)) return false;
        EntityClass that = (EntityClass) o;
        return id == that.id &&
                title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
