package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent movieDetailIntent = getIntent();
        String mMovieTitle = movieDetailIntent.getStringExtra("MOVIE_TITLE");
        String mMovieReleaseDate = movieDetailIntent.getStringExtra("MOVIE_RELEASE_DATE");
        Double mMovieRating = movieDetailIntent.getDoubleExtra("MOVIE_RATING",1.00);
        String mMovieSynopsis = movieDetailIntent.getStringExtra("MOVIE_SYNOPSIS");
        String mMoviePosterUrl = movieDetailIntent.getStringExtra("MOVIE_THOMBNAIL_URL");


        TextView movieTitle = (TextView)findViewById(R.id.tv_movie_title);
        movieTitle.setText(mMovieTitle);

        ImageView movieThombnail = (ImageView)findViewById(R.id.iv_movie_detail_thombnail);
        Picasso.with(this).load(mMoviePosterUrl).into(movieThombnail);

        TextView releaseDate = (TextView)findViewById(R.id.tv_release_date);
        releaseDate.setText(mMovieReleaseDate);

        TextView movieRating = (TextView)findViewById(R.id.tv_rating);
        String movieRatingPerMaxRating = String.valueOf(mMovieRating)+"/10";
        movieRating.setText(movieRatingPerMaxRating);

        TextView movieSynopsis = (TextView)findViewById(R.id.tv_movie_synopsis);
        movieSynopsis.setText(mMovieSynopsis);





    }
}
