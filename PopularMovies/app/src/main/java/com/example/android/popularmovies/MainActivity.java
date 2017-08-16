package com.example.android.popularmovies;
import android.content.Intent;
import android.content.Loader;
import android.app.LoaderManager;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>{
    /** Tag for the log messages */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    //TODO: add your API key Here
    private final String API_KEY = "ADD YOUR API KEY HERE";
    private final String BEFORE_API_KEY = "?api_key=";
    private static final int MOVIE_LOADER_ID = 1;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView moviesGridView = (GridView) findViewById(R.id.grid_view_movies);
        ArrayList<Movie> movies = new ArrayList<>();

        movieAdapter= new MovieAdapter(this, movies);
        moviesGridView.setAdapter(movieAdapter);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(MOVIE_LOADER_ID, null, this);

        moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current movie that was clicked on
                Movie currentMovie = movieAdapter.getItem(position);
                //Find Movie Information for the current movie clicked
                String movieTitle = currentMovie.getOriginalMovieTitle();
                String movieReleaseDate =currentMovie.getReleaseDate();
                double movieRating = currentMovie.getUserRating();
                String movieSynopsis = currentMovie.getPlotSynopsis();
                String movieThombnailUrl = currentMovie.getThombnailResourceId();
                //start the activity for movie detail screen and send the information to it
                Intent movieDetailIntent = new Intent (MainActivity.this, MovieDetail.class);
                movieDetailIntent.putExtra("MOVIE_TITLE", movieTitle);
                movieDetailIntent.putExtra("MOVIE_RELEASE_DATE",movieReleaseDate);
                movieDetailIntent.putExtra("MOVIE_RATING", movieRating);
                movieDetailIntent.putExtra("MOVIE_RATING",movieRating);
                movieDetailIntent.putExtra("MOVIE_SYNOPSIS",movieSynopsis);
                movieDetailIntent.putExtra("MOVIE_THOMBNAIL_URL",movieThombnailUrl);
                startActivity(movieDetailIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BASE_URL);
        stringBuilder.append(orderBy);
        stringBuilder.append(BEFORE_API_KEY);
        stringBuilder.append(API_KEY);
        String movieUri = stringBuilder.toString();
        return new MovieLoader(this, movieUri);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<Movie>> loader, List<Movie> movies) {
        // Clear the adapter of previous earthquake data
        movieAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (movies != null && !movies.isEmpty()) {
            movieAdapter.addAll(movies);
        }
    }
    @Override
    public void onLoaderReset(android.content.Loader<List<Movie>> loader) {
        // Loader reset, so we can clear out our existing data.
        movieAdapter.clear();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
