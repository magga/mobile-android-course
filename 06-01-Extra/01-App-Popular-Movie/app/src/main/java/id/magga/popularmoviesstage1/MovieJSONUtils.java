package id.magga.popularmoviesstage1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by magga on 7/11/2017.
 */

public class MovieJSONUtils {
    public static Movie[] getListMovieFromJson(MainActivity mainActivity, String jsonMovieResponse) {
        try {
            JSONObject obj = new JSONObject(jsonMovieResponse);

            String resultsString = obj.getString("results");

            JSONArray resultsArray = new JSONArray(resultsString);

            Movie[] movies = new Movie[resultsArray.length()];

            for (int i = 0; i < resultsArray.length(); i++){
                Movie movie = new Movie();

                JSONObject part = resultsArray.getJSONObject(i);

                movie.title = part.getString("original_title");
                movie.releaseDate = part.getString("release_date");
                movie.imgPosterLink = MoviePreferences.IMG_LINK_HELPER + part.getString("poster_path");
                movie.voteAverage = part.getDouble("vote_average");
                movie.overview = part.getString("overview");

                movies[i] = movie;
            }

            return movies;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}