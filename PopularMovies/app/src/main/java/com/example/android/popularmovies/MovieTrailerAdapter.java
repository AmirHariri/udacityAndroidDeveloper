package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 9/8/2017.
 */

public class MovieTrailerAdapter extends ArrayAdapter<Movie.MovieTrailer> {
    public MovieTrailerAdapter(Activity context, ArrayList<Movie.MovieTrailer> movieTrailer) {
        super(context, 0, movieTrailer);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.trailer_list_item, parent, false);
        }
        Movie.MovieTrailer currentTrailer = getItem(position);
        Button buttonTrailer = listItemView.findViewById(R.id.button_play_trailer);
        TextView trailerNameTextView = listItemView.findViewById(R.id.tv_trailer);

        if (currentTrailer != null) {
            trailerNameTextView.setText(currentTrailer.getMovieTrailerName());
        }
        return listItemView;

    }
}
