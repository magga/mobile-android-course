package id.magga.cardrecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by magga on 7/19/2017.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;
    protected TextView txtTitle;
    protected TextView txtDescription;

    public CustomViewHolder(View itemView) {
        super(itemView);

        this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        this.txtDescription = (TextView) itemView.findViewById(R.id.txtDescription);
    }
}
