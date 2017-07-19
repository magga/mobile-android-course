package id.magga.popularmoviesstage1;

/**
 * Created by magga on 7/11/2017.
 */

public class MoviePreferences {
    private static final String API_KEY = "YOUR_API_KEY_HERE";

    public static final String POPULAR = "popular";
    public static final String POPULAR_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&language=en-US&page=1";

    public static final String TOP_RATED = "top_rated";
    public static final String TOP_RATED_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&language=en-US&page=1";

    public static final String IMG_LINK_HELPER = "http://image.tmdb.org/t/p/w185";
}
