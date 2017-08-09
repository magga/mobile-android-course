package id.magga.localdatastoreusingparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etOrganization;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeParse();

        etName = (EditText) findViewById(R.id.etName);
        etOrganization = (EditText) findViewById(R.id.etOrganization);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void InitializeParse(){
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YOUR_APP_ID")
                .server("YOUR_SERVER_LINK")
                .enableLocalDataStore()
                .build()
        );
    }

    public void Read(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Organization");

        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> listObj, ParseException e) {
                textView.setText("");

                if (e == null) {
                    if (listObj.size() < 1){
                        textView.setText("Not Found");
                        return;
                    }

                    for (ParseObject obj : listObj){
                        textView.append("ID : " + obj.getString("id") + "\n");
                        textView.append("Name : " + obj.getString("name") + "\n");
                        textView.append("Organization : " + obj.getString("organization") + "\n\n");
                    }
                } else {
                    textView.setText("ERROR - " + e.getMessage());
                }
            }
        });
    }

    public void Create(View view){
        Random rand = new Random();
        int id = rand.nextInt(1000000000) + 1;

        ParseObject org = new ParseObject("Organization");
        org.put("id", Integer.toString(id));
        org.put("name", etName.getText().toString());
        org.put("organization", etOrganization.getText().toString());

        org.pinInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this, "SAVE BERHASIL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "SAVE ERROR : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Update(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Organization");

        query.fromLocalDatastore();
        query.whereEqualTo("name", etName.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> listObj, ParseException e) {
                if (e == null) {
                    if (listObj.size() < 1){
                        textView.setText("Not Found");
                        return;
                    }

                    for (ParseObject obj : listObj){
                        obj.put("organization", etOrganization.getText().toString());
                        obj.pinInBackground();
                    }

                    Toast.makeText(MainActivity.this, Integer.toString(listObj.size()) + " item(s) updated" , Toast.LENGTH_SHORT).show();

                } else {
                    textView.setText("ERROR - " + e.getMessage());
                }
            }
        });
    }

    public void Delete(View view){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Organization");

        query.fromLocalDatastore();
        query.whereEqualTo("name", etName.getText().toString());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> listObj, ParseException e) {
                if (e == null) {
                    if (listObj.size() < 1){
                        textView.setText("Not Found");
                        return;
                    }

                    for (ParseObject obj : listObj){
                        obj.unpinInBackground();
                    }

                    Toast.makeText(MainActivity.this, Integer.toString(listObj.size()) + " item(s) deleted" , Toast.LENGTH_SHORT).show();

                } else {
                    textView.setText("ERROR - " + e.getMessage());
                }
            }
        });
    }
}
