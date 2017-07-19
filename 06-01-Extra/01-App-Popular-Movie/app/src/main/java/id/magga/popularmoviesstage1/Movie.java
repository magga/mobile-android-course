package id.magga.popularmoviesstage1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by magga on 7/11/2017.
 */

public class Movie implements Parcelable {
    public String title;
    public String releaseDate;
    public String imgPosterLink;
    public Double voteAverage;
    public String overview;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeString(imgPosterLink);
        parcel.writeDouble(voteAverage);
        parcel.writeString(overview);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Movie(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        imgPosterLink = in.readString();
        voteAverage = in.readDouble();
        overview = in.readString();
    }

    public Movie(){}
}
