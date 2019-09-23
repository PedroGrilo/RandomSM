package com.pedrogrilo.random_tvshows_movies;


import com.google.android.youtube.player.YouTubeBaseActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestVideo extends YouTubeBaseActivity implements RequestVideoInterface{

    private final String API_KEY_MOVIEDB = "de305eb0e7fd7fce163f29a248002f06";
    private Integer idMovie;
    private String url = "";
    private String keyMovie = "";
    private boolean successRequest = false;

    public RequestVideo(Integer idMovie) {
        this.idMovie = idMovie;
        url = "https://api.themoviedb.org/3/movie/" + this.idMovie + "/videos?api_key=" + API_KEY_MOVIEDB + "&language=en-US";
        makeRequest();
    }

    @Override
    public String getKeyMovie() {

        return keyMovie;
    }

    @Override
    public void setKeyMovie(String keyMovie) {
        this.keyMovie = keyMovie;
    }

    @Override
    public void makeRequest() {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    try {
                        JSONObject json = new JSONObject(myResponse);
                        System.out.println("DEBUG:");
                        System.out.println(json);
                        System.out.println("KEY :: " + json.getJSONArray("results").getJSONObject(0).getString("key"));
                        setKeyMovie(json.getJSONArray("results").getJSONObject(0).getString("key"));
                        successRequest = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Override
    public boolean isSuccessRequest() {
        return successRequest;
    }

    @Override
    public void setSuccessRequest(boolean successRequest) {
        this.successRequest = successRequest;
    }
}
