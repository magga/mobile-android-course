package id.magga.cardrecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by magga on 7/19/2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<FeedItem> listFeed;

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyRecyclerViewAdapter(Context context, List<FeedItem> listFeed) {
        this.context = context;
        this.listFeed = listFeed;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder cust = new CustomViewHolder(view);
        return cust;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final FeedItem feed = listFeed.get(position);

        if (feed.getThumbnail() != ""){
            Picasso.with(context).load(feed.getThumbnail()).into(holder.imageView);
        }

        holder.txtTitle.setText(feed.getTitle());
        holder.txtDescription.setText(feed.getContent());

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onItemClickListener.onItemClick(feed);
                Toast.makeText(context, feed.getTitle(), Toast.LENGTH_LONG).show();
            }
        };

        holder.imageView.setOnClickListener(listener);
        holder.txtTitle.setOnClickListener(listener);
        holder.txtDescription.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if (listFeed != null){
            return listFeed.size();
        } else {
            return 0;
        }
    }
}
