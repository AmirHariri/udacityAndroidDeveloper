package com.example.android.popularmovies;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Movie {

    private String originalMovieTitle;
    // called over view in the api
    private String plotSynopsis;
    // called vote average in the api
    private double userRating;
    //release date millisecond
    private String  releaseDate;
    // for poster image
    private String imageResourceId;
    //for thombnail image in detail screen
    private String  thombnailResourceId;
    private final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";
    //for MovieTrailer class
    private MovieTrailer movieTrailer;
    //for movie Id;
    private int movieId;



    Movie(String vOriginalMovieTitle, String vPlotSynopsis, String  vReleaseDate, double vUserRating,
                 String  vImageResourceId, String  vThombnailResourceId,int vMovieId){
        this.originalMovieTitle = vOriginalMovieTitle;
        this.plotSynopsis = vPlotSynopsis;
        this.releaseDate = vReleaseDate;
        this.userRating = vUserRating;
        this.imageResourceId = vImageResourceId;
        this.thombnailResourceId = vThombnailResourceId;
        this.movieId = vMovieId;


    }
    public static class MovieTrailer implements Serializable{
        private static final String LOG_TAG = Movie.class.getSimpleName();
        public String  movieTrailerYouTubeKey;
        public String  movieTrailerName;

        MovieTrailer(String vMovieTrailerYouTubeKey, String vMovieTrailerName){
            this.movieTrailerYouTubeKey = vMovieTrailerYouTubeKey;
            this.movieTrailerName = vMovieTrailerName;
        }

        public String getMovieTrailerYouTubeKey(){
            return movieTrailerYouTubeKey;
        }
        public String getMovieTrailerName(){
            return movieTrailerName;
        }

    }

    public String getOriginalMovieTitle(){
        return originalMovieTitle;
    }
    public String getPlotSynopsis(){
        return plotSynopsis;
    }
    public String  getReleaseDate(){
        return releaseDate;
    }
    public double getUserRating() {
        return userRating;
    }
    public String getImageResourceId() {
        return stringUrlBuilder(imageResourceId,"w500");
    }
    public String  getThombnailResourceId() {
        return stringUrlBuilder(thombnailResourceId, "original");
    }
    public MovieTrailer getMovieTrailer(){
        return movieTrailer;
    }
    public int getMovieId() {
        return movieId;
    }


    private String stringUrlBuilder(String urlString,String size){
        String url = null;
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_IMAGE_URL);
        builder.append(size);
        builder.append(urlString);
        url = builder.toString();
        return url;
    }
}
