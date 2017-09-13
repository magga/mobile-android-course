package id.magga.uberclone;

import android.app.ActionBar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    Switch swUserType;
    String userType;
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HideEverything();
        InitializeParse();

        swUserType = (Switch) findViewById(R.id.swUserType);
        btnGetStarted = (Button) findViewById(R.id.btnGetStarted);
    }

    private void InitializeParse(){
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9c344d161a974bb20f0cbb5e869a455e2bacb9ef")
                .server("http://ec2-52-14-11-143.us-east-2.compute.amazonaws.com:80/parse")
                .build()
        );
    }

    private void HideEverything(){
        // Hide Action Bar
        getSupportActionBar().hide();

        // Hide Status Bar
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
    }

    public void GetStarted(View view) {
        if (swUserType.isChecked()) {
            userType = "driver";
        } else {
            userType = "rider";
        }

        if (ParseUser.getCurrentUser() == null) {
            Toast.makeText(MainActivity.this, "Not Yet", Toast.LENGTH_SHORT).show();

            btnGetStarted.setClickable(false);
            btnGetStarted.setAlpha(.5f);

            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "Anonymous login successful", Toast.LENGTH_SHORT).show();

                        ParseUser.getCurrentUser().put("riderOrDriver", userType);
                        Toast.makeText(getApplicationContext(), "Redirecting as " + ParseUser.getCurrentUser().get("riderOrDriver"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Anonymous login failed - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    btnGetStarted.setClickable(true);
                    btnGetStarted.setAlpha(1f);
                }
            });
        } else {
            ParseUser.getCurrentUser().put("riderOrDriver", userType);
            Toast.makeText(this, "Redirecting as " + ParseUser.getCurrentUser().get("riderOrDriver"), Toast.LENGTH_SHORT).show();
        }
    }
}
