package my.interview.example.Process;


import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created by Nackson on 5/17/2016.
 */
public class MovieModel implements Serializable {

   private int id;
   private long movieId;
   private String movieName;
   private String movieSynopsis;
   private String releaseDate;
   private double rate;
   private String movieImage;
   private String movieUrl;
   private String movieTrailers;
   private boolean isFavorite;


    public boolean isFavorite() {
        return isFavorite;
    }

    public int getId() {
        return id;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieSynopsis() {
        return movieSynopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRate() {
        return rate;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public String getMovieTrailers() {
        return movieTrailers;
    }

    public MovieModel(long movieId, String movieName, String movieSynopsis,
                      String releaseDate, double rate, String movieImage, String movieUrl, String movieTrailers,boolean isFavorite) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieSynopsis = movieSynopsis;
        this.releaseDate = releaseDate;
        this.rate = rate;
        this.movieImage = movieImage;
        this.movieUrl = movieUrl;
        this.movieTrailers = movieTrailers;
        this.isFavorite = isFavorite;
    }
    public MovieModel(int id,long movieId, String movieName, String movieSynopsis,
                      String releaseDate, double rate, String movieImage, String movieUrl, String movieTrailers,boolean isFavorite) {
        this.id = id;
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieSynopsis = movieSynopsis;
        this.releaseDate = releaseDate;
        this.rate = rate;
        this.movieImage = movieImage;
        this.movieUrl = movieUrl;
        this.movieTrailers = movieTrailers;
        this.isFavorite = isFavorite;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MovieModel) {
            MovieModel other = (MovieModel) obj;
            // Google Guava provides great utilities for equals
            return Objects.equal(movieId, other.movieId);
        } else {
            return false;
        }
    }

}
