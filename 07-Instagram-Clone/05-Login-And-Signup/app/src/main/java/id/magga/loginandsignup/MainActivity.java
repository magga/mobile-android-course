package id.magga.loginandsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    boolean loginMode = true;

    EditText etUsername;
    EditText etPassword;

    Button btnMode;
    TextView tvChangeMode;

    public void BtnClicked(View view){
        if (etUsername.getText().toString().matches("") || etPassword.getText().toString().matches("")) {
            Toast.makeText(this, "A username and password are required.", Toast.LENGTH_SHORT).show();
        } else {
            if (loginMode) {
                ParseUser.logInInBackground(etUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                ParseUser user = new ParseUser();

                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Signup Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void ChangeMode(View view){
        if (loginMode) {
            loginMode = false;
            btnMode.setText("SIGNUP");
            tvChangeMode.setText("Or, LOGIN");
        } else {
            loginMode = true;
            btnMode.setText("LOGIN");
            tvChangeMode.setText("Or, SIGNUP");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeParse();

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnMode = (Button) findViewById(R.id.btnMode);
        tvChangeMode = (TextView) findViewById(R.id.tvChangeMode);
    }

    private void InitializeParse() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9c344d161a974bb20f0cbb5e869a455e2bacb9ef")
                .server("http://ec2-52-14-11-143.us-east-2.compute.amazonaws.com:80/parse")
                .build()
        );
    }
}
