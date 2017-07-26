package id.magga.advancedparsetechniques;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    public void Create(View view){
        ParseObject score = new ParseObject("Score");

        score.put("username", "magga");
        score.put("score", 93);

        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "Create Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Create Error : " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void GetByID(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

        query.getInBackground("kqQD72ltJ8", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null && object != null) {
                    Toast.makeText(MainActivity.this, "Username : " + object.getString("username") + ", Score : " + Integer.toString(object.getInt("score")), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Object Not Found", Toast.LENGTH_SHORT).show();
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

    private void InitializeParse() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9c344d161a974bb20f0cbb5e869a455e2bacb9ef")
                .server("http://ec2-52-14-11-143.us-east-2.compute.amazonaws.com:80/parse")
                .build()
        );
    }
}
