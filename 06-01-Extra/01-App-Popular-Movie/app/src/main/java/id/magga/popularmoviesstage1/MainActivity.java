package id.magga.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private MovieAdapter mMovieAdapter;
    private RecyclerView mRecyclerView;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_movie);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this, getApplicationContext());
        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData(MoviePreferences.POPULAR);
    }

    private void loadMovieData(String option){
        showMovieDataView();

        new FetchMovieTask().execute(option);
    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Movie selectedMovie) {
        Context context = this;
        //Toast.makeText(context, selectedMovie.imgPosterLink, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("movie", selectedMovie);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_popular) {
            mMovieAdapter.setMovieData(null);
            loadMovieData(MoviePreferences.POPULAR);
            return true;
        } else if (id == R.id.sort_top_rated) {
            mMovieAdapter.setMovieData(null);
            loadMovieData(MoviePreferences.TOP_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if (!isOnline()){
                return null;
            }

            if (params.length == 0) {
                return null;
            }

            String urlString = "";

            if (params[0] == MoviePreferences.POPULAR){
                urlString = MoviePreferences.POPULAR_URL;
            } else if (params[0] == MoviePreferences.TOP_RATED){
                urlString = MoviePreferences.TOP_RATED_URL;
            }

            try {
                URL url = new URL(urlString);

                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(url);

                Movie[] movies = MovieJSONUtils
                        .getListMovieFromJson(MainActivity.this, jsonMovieResponse);

                return movies;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            mLoadingBar.setVisibility(View.INVISIBLE);
            if (movies != null) {
                showMovieDataView();
                mMovieAdapter.setMovieData(movies);
            } else {
                showErrorMessage();
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}














