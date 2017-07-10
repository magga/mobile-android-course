package id.magga.advancedsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);
        tvData.setText("");

        try {

            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);


            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS newUsers (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");

            eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('Kirsten', 21)");

            eventsDB.execSQL("INSERT INTO newUsers (name, age) VALUES ('Ralphie', 1)");

            //eventsDB.execSQL("DELETE FROM newUsers WHERE id = 1");

            Cursor c = eventsDB.rawQuery("SELECT * FROM newUsers", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (c != null) {

                tvData.append("Name : " + c.getString(nameIndex) + "\n");
                tvData.append("Age  : " + Integer.toString(c.getInt(ageIndex)) + "\n");
                tvData.append("Id   : " + Integer.toString(c.getInt(idIndex)) + "\n\n");

                c.moveToNext();
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
