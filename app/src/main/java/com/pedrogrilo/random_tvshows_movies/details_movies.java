package com.pedrogrilo.random_tvshows_movies;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class details_movies extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movies);
        FloatingActionButton returnB = findViewById(R.id.floatingActionButton);
        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        final Button btnPlay = findViewById(R.id.buttonPlay);
        final YouTubePlayerView mYouTubePlayerView = findViewById(R.id.youtubeView);
        TextView title = findViewById(R.id.title_m);
        TextView overview_m = findViewById(R.id.overview_m);
        overview_m.setMovementMethod(new ScrollingMovementMethod());
        TextView release_date = findViewById(R.id.release_date);
        TextView genres = findViewById(R.id.genres);
        ImageView adult = findViewById(R.id.adult);
        Bundle extras = getIntent().getExtras();
        String value = null;
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        if (extras != null)
            value = extras.getString("JSON");


        try {
            JSONObject json = new JSONObject(value);
            final RequestVideo rv = new RequestVideo(json.getInt("id"));

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    makeView(mYouTubePlayerView, btnPlay, rv.getKeyMovie());
                    progressBar.setVisibility(View.INVISIBLE);
                    if (rv.isSuccessRequest()) {
                        mYouTubePlayerView.setVisibility(View.VISIBLE);
                        btnPlay.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "Unavailable Trailer", Toast.LENGTH_LONG).show();
                    }
                }
            }, 2000);


            title.setText(json.getString("title"));
            overview_m.setText(json.getString("overview"));
            release_date.setText(json.getString("release_date"));
            genres.setText(getGenres(json.getJSONArray("genres")));

            if (json.getString("adult").equals("true"))
                adult.setVisibility(View.VISIBLE);
            else
                adult.setVisibility(View.INVISIBLE);


        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }

    private void makeView(YouTubePlayerView mYouTubePlayerView, Button btnPlay, String getKeyMovie) {
        new YoutubeConfigClass(mYouTubePlayerView, btnPlay, getKeyMovie);
    }

    private String getGenres(JSONArray genres) {

        String genreString = "";
        String continueString = ", ";

        for (int i = 0; i < genres.length(); i++) {

            if (i == genres.length() - 1)
                continueString = ".";

            try {
                genreString += genres.getJSONObject(i).getString("name") + continueString;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return genreString;
    }


}
