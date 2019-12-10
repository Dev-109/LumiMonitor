package com.n01249089.lumimonitor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class rgbSettings extends AppCompatActivity {

        private FirebaseDatabase database;
        private DatabaseReference myRef;
        DataStructure mData;
        private Button back;

        private TextView Rgb;
        private TextView timestamp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rgb_settings);
            setupTitleandHomeButton();
            this.setTitle("RGB Options");
            getDatabase();
            findAllViews();
            reterieveData();
        }
        /* save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            writeData(temperature.getText(), humidity.getText(), lightLevel.getText(),micIn.getText(),micOut.getText(),rgb.getText());
        }
    });
*/

        private void findAllViews() {
            Rgb = findViewById(R.id.textView2);
            timestamp = findViewById(R.id.timestamp);
        }
        private void getDatabase() {
            // TODO: Find the reference form the database.
            database = FirebaseDatabase.getInstance();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String path = "userdata/" + mAuth.getUid();  // read from the user account.
            myRef = database.getReference(path);
        }

    private DataStructure createData(Editable temperature, Editable humidity, Editable lightLevel, Editable micIn, Editable micOut, Editable rgb){
        // TODO: Get the timestamp
        Long time = System.currentTimeMillis()/1000;
        String timestamp = time.toString();
        return new DataStructure(String.valueOf(temperature),
                String.valueOf(humidity),
                String.valueOf(lightLevel),
                String.valueOf(micIn),
                String.valueOf(micOut),
                String.valueOf(rgb),
                timestamp);
    }

    private void writeData(Editable temperature, Editable humidity, Editable lightLevel, Editable micIn, Editable micOut, Editable rgb) {

        DataStructure mData = createData(temperature, humidity, lightLevel,micIn,micOut,rgb);
        // Select one of the following methods to update the data.
        // 1. To set the value of data
        //myRef.setValue(mData);
        // 2. To create a new node on database.
        // myRef.push().setValue(mData);
        myRef.push().setValue(rgb);
        // TODO: Write the data to the database.
        // 3. To create a new node on database and detect if the writing is successful.
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
                // gotoRead();  // after write the data, read it from another screen.
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Writing failed", Toast.LENGTH_LONG).show();
            }
        });
    }

        private void reterieveData() {
            // TODO: Get the data on a single node.
            myRef.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    DataStructure ds = dataSnapshot.getValue(DataStructure.class);

                    Rgb.setText("RGB Status: " + ds.getRgb());

                    // Convert from timestamp to Date and time
                    timestamp.setText(convertTimestamp(ds.getTimestamp()));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    DataStructure ds = dataSnapshot.getValue(DataStructure.class);

                    Rgb.setText("RGB Status:      " + ds.getRgb());

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
                            dataStructure.setRgb(a.getValue(DataStructure.class).getRgb());
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

    }
    private void setupTitleandHomeButton() {
//        getSupportActionBar().setSubtitle("Firebase temp");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
