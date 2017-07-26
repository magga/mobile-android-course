package id.magga.advancedqueries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void Query(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.whereGreaterThan("score", 100);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    for (ParseObject object : objects) {
                        object.put("score", object.getInt("score") + 50);
                        object.saveInBackground();

                        Toast.makeText(MainActivity.this, "Username " + object.getString("username") + " updated", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No Object(s) Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeParse();
    }

    public void InitializeParse(){
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9c344d161a974bb20f0cbb5e869a455e2bacb9ef")
                .server("http://ec2-52-14-11-143.us-east-2.compute.amazonaws.com:80/parse")
                .build()
        );
    }
}
