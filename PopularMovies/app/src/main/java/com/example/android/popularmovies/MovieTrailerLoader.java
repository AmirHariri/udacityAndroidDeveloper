package com.example.android.popularmovies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Amir on 9/10/2017.
 */

class MovieTrailerLoader extends AsyncTaskLoader <List<Movie.MovieTrailer>> {
    private static final String LOG_TAG = MovieDetail.class.getSimpleName();

    private String mUrl;
    public MovieTrailerLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.i(LOG_TAG, "MovieTrailer loaded");
    }

    @Override
    public List<Movie.MovieTrailer> loadInBackground() {
        if (mUrl == null) {
            return null;
        } else{
            // Perform the network request, parse the response, and extract a list of movies.
            return QueryUtils.fetchYouTubeTrailerInfo(mUrl);

        }
    }

}
