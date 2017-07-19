package id.magga.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by magga on 7/11/2017.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView mImagePoster;
    TextView mTvTitle;
    TextView mTvReleaseDate;
    TextView mTvVote;
    TextView mTvOverview;

    Movie movie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mImagePoster = (ImageView) findViewById(R.id.imgPoster);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        mTvVote = (TextView) findViewById(R.id.tvVote);
        mTvOverview = (TextView) findViewById(R.id.tvOverview);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")){
            movie = (Movie) intent.getParcelableExtra("movie");

            if (isOnline()){
                Picasso.with(getApplicationContext()).load(movie.imgPosterLink).into(mImagePoster);
            }

            mTvTitle.setText(movie.title);
            mTvReleaseDate.setText(movie.releaseDate);
            mTvVote.setText(Double.toString(movie.voteAverage));
            mTvOverview.setText(movie.overview);
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
