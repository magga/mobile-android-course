package id.magga.settingupfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etOrganization;
    Button btnCreate;
    TextView textView;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etOrganization = (EditText) findViewById(R.id.etOrganization);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        textView = (TextView) findViewById(R.id.textView);

        dbRef = FirebaseDatabase.getInstance().getReference("user");

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> listUser = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()){
                    User user = data.getValue(User.class);

                    listUser.add(user);
                }

                textView.setText("");

                for (User user : listUser){
                    textView.append("ID : " + user.getId() + "\n");
                    textView.append("Name : " + user.getName() + "\n");
                    textView.append("Organization : " + user.getOrganization() + "\n\n");
                }

                Toast.makeText(MainActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        };

        dbRef.addValueEventListener(listener);
    }

    public void Create(View view){
        Random rand = new Random();
        int id = rand.nextInt(1000000000) + 1;

        User user = new User();
        user.setId(Integer.toString(id));
        user.setName(etName.getText().toString());
        user.setOrganization(etOrganization.getText().toString());

        dbRef.child(user.getId()).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError == null){
                    Toast.makeText(MainActivity.this, databaseReference.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
