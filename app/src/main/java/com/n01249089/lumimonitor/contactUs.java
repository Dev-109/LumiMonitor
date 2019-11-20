package com.n01249089.lumimonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class contactUs extends AppCompatActivity {
private Button postButton,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        postButton = findViewById(R.id.button9);
        back = findViewById(R.id.button10);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

}
