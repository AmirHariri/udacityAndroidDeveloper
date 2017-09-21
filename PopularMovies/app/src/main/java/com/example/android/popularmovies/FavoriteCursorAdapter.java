package com.example.android.popularmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.data.FavoriteMovieListContract;

import static android.R.attr.description;
import static com.example.android.popularmovies.data.FavoriteMovieListContract.FavoriteMovieListEntry;
/**
 * Created by Amir on 9/18/2017.
 */

class FavoriteCursorAdapter extends RecyclerView.Adapter<FavoriteCursorAdapter.FavoriteMoviesViewHolder> {

    // Class variables for the Cursor that holds task data and the Context
    private Cursor mCursor;
    private Context mContext;
    public FavoriteCursorAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }


    @Override
    public FavoriteMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.movie_task_list_item, parent, false);
        return new FavoriteMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteMoviesViewHolder holder, int position) {
        int idIndex = mCursor.getColumnIndex(FavoriteMovieListEntry._ID);
        int movieTitleIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_TITLE);
        int movieReleaseDateIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_RELEASE_DATE);
        int movieRatingIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_RATING);
        int movieSynopsisIndex = mCursor.getColumnIndex(FavoriteMovieListEntry.COLUMN_MOVIE_PLOT_SYNOPSYS);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String movieTitle = mCursor.getString(movieTitleIndex);
        String releaseDate = mCursor.getString(movieReleaseDateIndex);
        Double rating = mCursor.getDouble(movieRatingIndex);

        //Set values
        holder.itemView.setTag(id);
        holder.mFavoriteTitleView.setText(movieTitle);
        holder.mFavoriteReleaseDateView.setText(releaseDate);
        String ratingString = String.valueOf(rating)+"/10";
        holder.mFavoriteRatingView.setText(ratingString);
        holder.mFavoriteSynopsisView.setText(movieSynopsisIndex);



    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }


    public class FavoriteMoviesViewHolder extends RecyclerView.ViewHolder{

        TextView mFavoriteTitleView;
        TextView mFavoriteReleaseDateView;
        TextView mFavoriteRatingView;
        TextView mFavoriteSynopsisView;

        public FavoriteMoviesViewHolder(View itemView) {
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
