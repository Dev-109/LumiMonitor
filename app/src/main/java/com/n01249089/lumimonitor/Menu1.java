package com.n01249089.lumimonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Menu1 extends AppCompatActivity {
    private static Button temp_button,btnAddToDatabase,btnSignOut,contactUs_button,lighting_button,mic_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        temp_button = findViewById(R.id.button);
        btnSignOut = findViewById(R.id.button8);
        btnAddToDatabase = findViewById(R.id.button6);
        contactUs_button = findViewById(R.id.button5);
        lighting_button = findViewById(R.id.button3);
        mic_button = findViewById(R.id.button2);



        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTemp();
            }
        });
        mic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMic();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mAuth.signOut();
                    toastMessage("Signing Out...");
                    finish();
              //  Intent intent = new Intent(Menu1.this, MainActivity.class);
                //startActivity(intent);
            }
        });

        btnAddToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu1.this, AddToDatabase.class);
                startActivity(intent);
            }
        });

        contactUs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContact();
            }
        });
        lighting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openLighting();
            }
        });
    }

    public void openTemp(){
        Intent intent = new Intent(this, temperature.class);
        startActivity(intent);
    }

    public void openContact(){
        Intent intent = new Intent(this, contactUs.class);
        startActivity(intent);
    }

    public void openLighting(){
        Intent intent = new Intent(Menu1.this, lighting.class);
        startActivity(intent);
    }

    public void openMic(){
        Intent intent = new Intent(Menu1.this, microphone.class);
        startActivity(intent);
    }


    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }


}


