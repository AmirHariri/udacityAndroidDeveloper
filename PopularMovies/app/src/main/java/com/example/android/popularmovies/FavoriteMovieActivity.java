package com.example.android.popularmovies;


import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.data;
import static com.example.android.popularmovies.data.FavoriteMovieListContract.FavoriteMovieListEntry;

public class FavoriteMovieActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = FavoriteMovieActivity.class.getSimpleName();
    private static final int FAVORITE_LOADER_ID = 3;

    // Member variables for the adapter and RecyclerView
    FavoriteCursorAdapter mAdapter;
    RecyclerView mRecyclerView;
    Cursor mFavoriteData;
    FavoriteCursorAdapter favoriteCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movie);

        ArrayList<Movie.FavoriteMovie> favoriteMovies = new ArrayList<>();
        mAdapter= new FavoriteCursorAdapter(this,mFavoriteData);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_favoritelist);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(FAVORITE_LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new AsyncTaskLoader<Cursor>(this) {

            // Initialize a Cursor, this will hold all the favorite data
            Cursor mFavoriteData = null;

            // onStartLoading() is called when a loader first starts loading data
            @Override
            protected void onStartLoading() {
                if (mFavoriteData != null) {
                    // Delivers any previously loaded data immediately
                    deliverResult(mFavoriteData);
                } else {
                    // Force a new load
                    forceLoad();
                }
            }

            // loadInBackground() performs asynchronous loading of data
            @Override
            public Cursor loadInBackground() {
                // Will implement to load data

                // Query and load all favorite data in the background; sort by id

                try {
                    return getContentResolver().query(FavoriteMovieListEntry.CONTENT_URI,
                            null,
                            null,
                            null,
                            FavoriteMovieListEntry._ID);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;

                }
            }

            public void deliverResult(Cursor data) {
                mFavoriteData = data;
                super.deliverResult(data);
            }


        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
