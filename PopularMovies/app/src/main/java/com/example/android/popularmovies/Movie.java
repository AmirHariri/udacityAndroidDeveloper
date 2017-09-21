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
    //for movie Reviewa;
    private List<String> reviews;

    String favoriteMovieTitle;
    String favoriteSynopsis;
    int favoriteReleaseDate;
    String favoriteRating;



    Movie(String vOriginalMovieTitle, String vPlotSynopsis, String  vReleaseDate, double vUserRating,
                 String  vImageResourceId, String  vThombnailResourceId,int vMovieId,List<String> vReviews){
        this.originalMovieTitle = vOriginalMovieTitle;
        this.plotSynopsis = vPlotSynopsis;
        this.releaseDate = vReleaseDate;
        this.userRating = vUserRating;
        this.imageResourceId = vImageResourceId;
        this.thombnailResourceId = vThombnailResourceId;
        this.movieId = vMovieId;
        this.reviews = vReviews;

    }

    public static class MovieTrailer {
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
    public class FavoriteMovie {
        String favoriteMovieTitle;
        String favoriteSynopsis;
        String  favoriteReleaseDate;
        int favoriteRating;

        FavoriteMovie(String vFavoriteMovieTitle,String vPlotSynopsis,String vReleaseDate,int vUserRating) {

            this.favoriteMovieTitle = vFavoriteMovieTitle;
            this.favoriteSynopsis = vPlotSynopsis;
            this.favoriteReleaseDate = vReleaseDate;
            this.favoriteRating = vUserRating;
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
        return stringUrlBuilder(imageResourceId, "original");
    }
    public MovieTrailer getMovieTrailer(){
        return movieTrailer;
    }
    public int getMovieId() {
        return movieId;
    }
    public List<String>  getReviews(){
        return reviews;
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
