package com.n01249089.lumimonitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class temperature extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;
    private Button back;

    private TextView temperature;
    private TextView humidity;
    private TextView timestamp;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        this.setTitle("Reading Temperature & Humidity from Database");
        getDatabase();
        findAllViews();
        reterieveData();
    }


    private void findAllViews() {
        temperature = findViewById(R.id.textView2);
        humidity = findViewById(R.id.textView3);
        timestamp = findViewById(R.id.timestamp);
        back = findViewById(R.id.button7);
    }
    private void getDatabase() {
        // TODO: Find the reference form the database.
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();  // read from the user account.
        myRef = database.getReference(path);
    }

    private void reterieveData() {
        // TODO: Get the data on a single node.
        myRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                // name.setText("Name: "+ ds.getName());
                temperature.setText("Temperature: " + ds.getTemp() +" Degrees Celcius");
                humidity.setText("Humidity: " + ds.getHumidity() + " %");

                // Convert from timestamp to Date and time
                timestamp.setText(convertTimestamp(ds.getTimestamp()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                DataStructure ds = dataSnapshot.getValue(DataStructure.class);

                temperature.setText("Temperature:      " + ds.getTemp() +" Degrees Celcius");
                humidity.setText("Humidity:      " + ds.getHumidity() + " %");

                // Convert from timestamps to Date and time
                timestamp.setText(convertTimestamp(ds.getTimestamp()));
            }

            private String convertTimestamp(String timestamp) {

                long yourSeconds = Long.valueOf(timestamp);
                Date mDate = new Date(yourSeconds * 1000);
                DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
                return df.format(mDate);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        // TODO: Get the whole data array on a reference.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructure> arraylist= new ArrayList<DataStructure>();

                // TODO: Now data is retrieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    // iterate all the items in the dataSnapshot
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructure dataStructure = new DataStructure();
                        dataStructure.setTemp(a.getValue(DataStructure.class).getTemp());
                        dataStructure.setHumidity(a.getValue(DataStructure.class).getHumidity());
                        dataStructure.setTimestamp(a.getValue(DataStructure.class).getTimestamp());

                        arraylist.add(dataStructure);  // now all the data is in arraylist.
                        Log.d("MapleLeaf", "dataStructure " + dataStructure.getTimestamp());
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"Cannot retrieve Data at this time", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting data failed, log a message
                Log.d("MapleLeaf", "Data Loading Canceled/Failed.", databaseError.toException());
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
