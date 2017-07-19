package id.magga.popularmoviesstage1;

import android.content.Context;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static id.magga.popularmoviesstage1.R.id.imageView;

/**
 * Created by magga on 7/11/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Movie[] mMovieData;
    private Context mContext;

    private final MovieAdapterOnClickHandler mClickHandler;

    public interface MovieAdapterOnClickHandler {
        void onClick(Movie selectedMovie);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler, Context context){
        mClickHandler = clickHandler;

        mContext = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie selectedMovie = mMovieData[position];

        Picasso.with(mContext).load(selectedMovie.imgPosterLink).into(holder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) {
            return 0;
        }

        return mMovieData.length;
    }

    public void setMovieData(Movie[] movieData){
        mMovieData = movieData;

        notifyDataSetChanged();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMoviePoster;

        public MovieViewHolder(View view){
            super(view);

            mMoviePoster = (ImageView) view.findViewById(imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Movie selectedMovie = mMovieData[adapterPosition];
            mClickHandler.onClick(selectedMovie);
        }
    }
}