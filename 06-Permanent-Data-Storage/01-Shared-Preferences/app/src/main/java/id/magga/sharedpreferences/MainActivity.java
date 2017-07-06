package id.magga.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        pref = getSharedPreferences("id.magga.sharedpreferences", Context.MODE_PRIVATE);

        SaveArray();
    }

    private void SaveArray() {
        ArrayList<String> country = new ArrayList<>();

        country.add("Indonesia");
        country.add("Holland");
        country.add("Trinidad Tobago");

        try {

            pref.edit().putString("country", ObjectSerializer.serialize(country)).apply();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void Save(View view){
        pref.edit().putString("data", editText.getText().toString()).apply();

        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show();
    }

    public void Load(View view){
        String data = pref.getString("data", "");
        
        textView.setText(data);

        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    public void ShowArray(View view){
        ArrayList<String> loadedCountry = new ArrayList<>();

        try {

            loadedCountry = (ArrayList<String>) ObjectSerializer.deserialize(pref.getString("country", ObjectSerializer.serialize(new ArrayList<String>())));

            textView.setText(loadedCountry.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
