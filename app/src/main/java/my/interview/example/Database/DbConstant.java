package my.interview.example.Database;

/**
 * Created by Nackson on 5/17/2016.
 */
public class DbConstant {

    public static final int VERSION = 1;
    public static final String DB_NAME                   = "movies_db";
    public static final String MOVIE_TABLE               = "movies";
    public static final String ID                        = "_id";
    public static final String MOVIE_ID                  = "movieId";
    public static final String MOVIE_NAME                = "movieName";
    public static final String MOVIE_SYNOPSIS            = "movieSynopsis";
    public static final String MOVIE_RELEASE_DATE        = "releaseDate";
    public static final String MOVIE_RATE                = "rate";
    public static final String MOVIE_IMAGE               = "movieImage";
    public static final String MOVIE_URL                 = "movieUrl";
    public static final String MOVIE_TRAILERS            = "movieTrailers";
    public static final String FAVORITE                  = "MarkAsFavorite";


    public static String CREATE_TABLE =
            "CREATE TABLE " + MOVIE_TABLE + " " +
            "(" +ID+" INTEGER PRIMARY KEY,"
            +MOVIE_ID + " INTEGER,"
            +MOVIE_NAME + " TEXT,"
            +MOVIE_SYNOPSIS + " TEXT,"
            +MOVIE_RELEASE_DATE + " TEXT,"
            +MOVIE_RATE + " REAL,"
            +MOVIE_IMAGE + " TEXT,"
            +MOVIE_URL + " TEXT,"
            +MOVIE_TRAILERS+ " TEXT,"
            +FAVORITE + " INTEGER)";

    public static String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE " + DbConstant.MOVIE_TABLE + " IF NOT EXISTS";
}
