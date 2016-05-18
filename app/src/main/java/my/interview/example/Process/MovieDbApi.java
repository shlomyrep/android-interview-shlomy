package my.interview.example.Process;

/**
 * Created by Nackson on 5/17/2016.
 */
public class MovieDbApi {
    public static final String API_KEY = "b3ad8ecaffb48c25a809c67e07242d8a";
    public static final String URL_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key="+API_KEY;
    public static final String IMAGE_URL ="https://image.tmdb.org/t/p/w185/";
    public static final String VIDEO_URL = "http://api.themoviedb.org/3/movie/%s/videos?api_key="+API_KEY;


    public static class JSON_KEYS{
        public static final String RESULTS       = "results";
        public static final String ID            = "id";
        public static final String TITLE         = "title";
        public static final String OVERVIEW      = "overview";
        public static final String RELEASE_DATE  = "release_date";
        public static final String VOTE_AVERAGE  = "vote_average";
        public static final String POSTER_PATH   = "poster_path";
        public static final String TRAILER_KEY   = "key";
    }

}
