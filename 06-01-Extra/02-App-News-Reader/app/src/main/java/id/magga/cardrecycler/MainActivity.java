package id.magga.cardrecycler;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<FeedItem> listFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String url = "http://www.stacktips.com/?json=get_category_posts&slug=news&count=30";

        new DownloadTask().execute(url);
    }

    class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
//            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            StringBuilder response = new StringBuilder();

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    String line = r.readLine();
                    while (line != null) {
                        response.append(line);
                        line = r.readLine();
                    }
                    //parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d("error", e.getLocalizedMessage());
            }
            return response.toString(); //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(String result) {
            parseResult(result);

            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this, listFeed);
            recyclerView.setAdapter(adapter);
//            adapter.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(FeedItem item) {
//                    Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_LONG).show();
//
//                }
//            });

            progressBar.setVisibility(View.GONE);
        }
    }

    public void parseResult(String result){

        try {
            JSONObject response = new JSONObject(result);

//            String arrString = response.getString("posts");
//
//            JSONArray arr = new JSONArray(arrString);

            JSONArray arr = response.getJSONArray("posts");

            listFeed = new ArrayList<>();

            for (int i = 0; i < arr.length(); i++) {
                JSONObject post = arr.getJSONObject(i);
                FeedItem item = new FeedItem();
                item.setTitle(post.getString("title"));
                item.setThumbnail(post.getString("thumbnail"));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    item.setContent(Html.fromHtml(post.getString("content"), Html.FROM_HTML_MODE_LEGACY).toString());
                }else{
                    item.setContent(Html.fromHtml(post.getString("content")).toString());
                }

                listFeed.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }

}
