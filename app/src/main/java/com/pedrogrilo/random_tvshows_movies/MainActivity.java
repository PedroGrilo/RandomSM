package com.pedrogrilo.random_tvshows_movies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private final String API_KEY_MOVIEDB = "de305eb0e7fd7fce163f29a248002f06";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRequest();
    }

    public void configureButtonDetails(final String json, final boolean movies) {

        Button details = findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = null;
                if (movies)
                    i = new Intent(MainActivity.this, details_movies.class);
                else
                    i = new Intent(MainActivity.this, details_tv_shows.class);

                i.putExtra("JSON", json);
                startActivity(i);
            }
        });
    }

    private void filter(JSONObject json, boolean movies, final Response response) {
        try {
            if (!movies) {
                if ((json.getString("first_air_date").equals("null")) || (json.getInt("number_of_seasons") == 0)) {
                    response.close();
                    getRequest();
                }
            } else {
                if ((json.getString("release_date").equals(""))) {
                    response.close();
                    getRequest();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void get(View view) {
        getRequest();
    }

    public void getRequest() {

        final TextView titleShowMovie = findViewById(R.id.titleShowMovie);
        final TextView year = findViewById(R.id.year);
        final TextView num_seasons = findViewById(R.id.num_seasons);
        final TextView num_seasons_text = findViewById(R.id.num_seasons_text);
        final Switch switch1 = findViewById(R.id.switch1);
        final ProgressBar loading = findViewById(R.id.loading);
        final Button randomize = findViewById(R.id.randomize);
        final TextView nocover = findViewById(R.id.nobanner);
        String url;

        Random rand = new Random();

        int rand_int1 = rand.nextInt(92800) + 1;

        System.out.println("\n\n " + rand_int1 + "\n\n");

        loading.setVisibility(View.VISIBLE);
        randomize.setVisibility(View.INVISIBLE);

        if (switch1.isChecked()) {
            //Shows
            url = "https://api.themoviedb.org/3/tv/" + rand_int1 + "?api_key=" + API_KEY_MOVIEDB;
        } else {
            //Movies
            url = "https://api.themoviedb.org/3/movie/" + rand_int1 + "?api_key=" + API_KEY_MOVIEDB;
        }

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
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.code() == 404) {
                    getRequest();
                }
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(myResponse);

                                if (switch1.isChecked())
                                    filter(json, false, response);
                                else
                                    filter(json, true, response);

                                if (json.getString("poster_path") == null)
                                    nocover.setVisibility(View.VISIBLE);
                                else
                                    nocover.setVisibility(View.INVISIBLE);

                                new DownloadImageFromInternet((ImageView) findViewById(R.id.banner)).execute("https://image.tmdb.org/t/p/w500" + json.getString("poster_path"));

                                loading.setVisibility(View.INVISIBLE);
                                randomize.setVisibility(View.VISIBLE);

                                if (switch1.isChecked()) {
                                    //tv_shows
                                    configureButtonDetails(myResponse, false);

                                    titleShowMovie.setText(json.getString("name"));
                                    num_seasons_text.setText("Number of Seasons:");
                                    num_seasons.setText(json.getString("number_of_seasons"));
                                    year.setText(json.getString("first_air_date"));

                                } else {
                                    //movies
                                    configureButtonDetails(myResponse, true);
                                    titleShowMovie.setText(json.getString("title"));
                                    year.setText(json.getString("release_date"));
                                    num_seasons_text.setText("Genres:");
                                    num_seasons.setText(json.getJSONArray("genres").getJSONObject(0).getString("name") + " & more...");
                                }

                             } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    response.close();
                }
            }
        });
    }


    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            //Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
