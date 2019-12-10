package com.n01249089.lumimonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class contactUs extends AppCompatActivity {
private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setupTitleandHomeButton();
        setTitle("Feedback & Review");
        postButton = findViewById(R.id.button9);
        setupTitleandHomeButton();
       postButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               feedbackposted();
           }
       });

    }
    public void feedbackposted(){
        toastMessage("Your feedback has been posted. Thank you");

    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void setupTitleandHomeButton() {
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
