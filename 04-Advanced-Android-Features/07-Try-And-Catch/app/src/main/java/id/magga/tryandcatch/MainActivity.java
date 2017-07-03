package id.magga.tryandcatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] array = new int[3];

        try {
            for (int i = 0; i < 4; i++) {
                array[i] = i;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            Log.i("Error Array", e.getMessage());
        }
        catch (Exception e) {
            Log.i("Error", e.getMessage());
        }

        Log.i("Info", Arrays.toString(array));
    }
}