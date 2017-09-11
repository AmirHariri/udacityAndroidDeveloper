package com.example.android.popularmovies;

import android.content.Intent;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.popularmovies.MainActivity.API_KEY;
import static com.example.android.popularmovies.MainActivity.BASE_URL;
import static com.example.android.popularmovies.MainActivity.BEFORE_API_KEY;


public class MovieDetail extends AppCompatActivity implements LoaderManager.LoaderCallbacks {
    private MovieTrailerAdapter trailerAdapter;
    private ListView trailerListView;
    private static final int TRAILER_LOADER_ID = 2;
    private static final String LOG_TAG = MovieDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent movieDetailIntent = getIntent();
        String mMovieTitle = movieDetailIntent.getStringExtra("MOVIE_TITLE");
        String mMovieReleaseDate = movieDetailIntent.getStringExtra("MOVIE_RELEASE_DATE");
        Double mMovieRating = movieDetailIntent.getDoubleExtra("MOVIE_RATING", 1.00);
        String mMovieSynopsis = movieDetailIntent.getStringExtra("MOVIE_SYNOPSIS");
        String mMoviePosterUrl = movieDetailIntent.getStringExtra("MOVIE_THOMBNAIL_URL");
        Integer mMovieID = movieDetailIntent.getIntExtra("MOVIE_ID", 1);

        ArrayList<Movie.MovieTrailer> movieTrailers = new ArrayList<>();
        trailerAdapter = new MovieTrailerAdapter(this, movieTrailers);
        trailerListView = (ListView) findViewById(R.id.lv_trailer);
        trailerListView.setAdapter(trailerAdapter);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();
        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(TRAILER_LOADER_ID, null, this).forceLoad();



        TextView movieTitle = (TextView) findViewById(R.id.tv_movie_title);
        movieTitle.setText(mMovieTitle);

        ImageView movieThombnail = (ImageView) findViewById(R.id.iv_movie_detail_thombnail);
        Picasso.with(this).load(mMoviePosterUrl)
                .placeholder(R.drawable.movie_poster_placeholder)
                .into(movieThombnail);

        TextView releaseDate = (TextView) findViewById(R.id.tv_release_date);
        releaseDate.setText(mMovieReleaseDate);

        TextView movieRating = (TextView) findViewById(R.id.tv_rating);
        String movieRatingPerMaxRating = String.valueOf(mMovieRating) + "/10";
        movieRating.setText(movieRatingPerMaxRating);

        TextView movieSynopsis = (TextView) findViewById(R.id.tv_movie_synopsis);
        movieSynopsis.setText(mMovieSynopsis);

        CheckBox favoriteCheckBox = (CheckBox) findViewById(R.id.checkbox_favorite);
        favoriteCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    Toast.makeText(MovieDetail.this, "Added to your Favorite Movies", Toast.LENGTH_SHORT).show();

                    //TODO add the movie to the database code here
                }
            }
        });
        Button buttonTrailer = (Button) findViewById(R.id.button_play_trailer)

    }


    @Override
    public Loader<List<Movie.MovieTrailer>> onCreateLoader(int id, Bundle args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(String.valueOf(id) + "/videos");
        stringBuilder.append(BEFORE_API_KEY);
        stringBuilder.append(API_KEY);
        String movieUri = stringBuilder.toString();
        return new MovieTrailerLoader(this, movieUri);
    }


    @Override
    public void onLoadFinished(Loader<List<Movie.MovieTrailer>> loader, List<Movie.MovieTrailer> movieTrailers) {
        trailerAdapter.clear();
        // If there is a valid list of {@link Movie}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movieTrailers != null && !movieTrailers.isEmpty() ) {
            trailerAdapter.addAll(movieTrailers);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        trailerAdapter.clear();

    }

    public void playtrailer(View view) {

    }
}
