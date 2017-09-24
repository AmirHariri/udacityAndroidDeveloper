package com.example.android.popularmovies;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.popularmovies.data.FavoriteMovieListContract.FavoriteMovieListEntry;
/**
 * Created by Amir on 9/18/2017.
 */
class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteMoviViewHolder> {
    // Class variables for the Cursor that holds task data and the Context
    private Cursor mCursor;
    private Context mContext;
    ArrayList<Movie.FavoriteMovie> favoriteMovies = new ArrayList<>();
    private static final String TAG = FavoriteMovieActivity.class.getSimpleName();

    public FavoriteAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }
    private Context getContext() {
        return mContext;
    }

    @Override
    public FavoriteMoviViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View favoriteView = inflater.inflate(R.layout.movie_task_list_item, parent, false);
        return new FavoriteMoviViewHolder(favoriteView);
    }

    @Override
    public void onBindViewHolder(FavoriteMoviViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(FavoriteMovieListEntry._ID);
        int movieTitleIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_TITLE);
        int movieReleaseDateIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_RELEASE_DATE);
        int movieRatingIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_RATING);
        int movieSynopsisIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_PLOT_SYNOPSYS);


        mCursor.moveToFirst();
        do{
            String movieTitle = mCursor.getString(movieTitleIndex);
            String releaseDate = mCursor.getString(movieReleaseDateIndex);
            Double rating = mCursor.getDouble(movieRatingIndex);
            String movieSynopsis = mCursor.getString(movieSynopsisIndex);
            Movie.FavoriteMovie favoriteMovie = new Movie.FavoriteMovie(movieTitle,movieSynopsis,releaseDate,rating);
            favoriteMovies.add(favoriteMovie);
            Log.e(TAG, "++++++++++++++++++++++++++++++++++++++++++++  :" + movieTitle +
                    " movie Rating " +rating );
        }while (mCursor.moveToNext());

        Movie.FavoriteMovie favoriteMovie = favoriteMovies.get(position);
        holder.mFavoriteTitleView.setText(favoriteMovie.getFavoriteMovieTitle());
        holder.mFavoriteReleaseDateView.setText(favoriteMovie.getFavoriteReleaseDate());
        holder.mFavoriteRatingView.setText(String .valueOf(favoriteMovie.getFavoriteRating()));
        holder.mFavoriteSynopsisView.setText(favoriteMovie.getFavoriteSynopsis());
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    public class FavoriteMoviViewHolder extends RecyclerView.ViewHolder{

        TextView mFavoriteTitleView;
        TextView mFavoriteReleaseDateView;
        TextView mFavoriteRatingView;
        TextView mFavoriteSynopsisView;

        public FavoriteMoviViewHolder(View itemView) {
            super(itemView);
            mFavoriteTitleView = (TextView) itemView.findViewById(R.id.tv_favorite_title);
            mFavoriteReleaseDateView = (TextView)itemView.findViewById(R.id.tv_favorite_release_date);
            mFavoriteRatingView = (TextView) itemView.findViewById(R.id.tv_favorite_rating);
            mFavoriteSynopsisView = (TextView) itemView.findViewById(R.id.tv_favorite_synopsis);
        }
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
