package com.pedrogrilo.random_tvshows_movies;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

public class details_tv_shows extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tv_shows);
        FloatingActionButton returnB = findViewById(R.id.floatingActionButton);
        returnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final Button btnPlay = findViewById(R.id.buttonPlayTvShow);
        final YouTubePlayerView mYouTubePlayerView = findViewById(R.id.youtubeViewTvShow);
        final ProgressBar progressBar = findViewById(R.id.progressBar2);

        TextView title = findViewById(R.id.title);
        TextView overview = findViewById(R.id.overview_s);
        TextView n_episodes = findViewById(R.id.n_episodes);
        TextView n_seasons = findViewById(R.id.n_seasons);
        TextView status = findViewById(R.id.status);
        TextView overview_tv = findViewById(R.id.overview_s);
        overview_tv.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();
        String value = null;

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

            title.setText(json.getString("name"));
            overview.setText(json.getString("overview"));
            n_episodes.setText(json.getString("number_of_episodes"));
            n_seasons.setText(json.getString("number_of_seasons"));
            status.setText(json.getString("status"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void makeView(YouTubePlayerView mYouTubePlayerView, Button btnPlay, String getKeyMovie) {
        new YoutubeConfigClass(mYouTubePlayerView, btnPlay, getKeyMovie);
    }


}
