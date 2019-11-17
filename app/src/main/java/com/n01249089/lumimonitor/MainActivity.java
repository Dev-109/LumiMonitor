package com.n01249089.lumimonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "hello";
    private static Button login_button;
    private static Button signup_button;

    private EditText mEmail, mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        signup_button = findViewById(R.id.button4);
        login_button = findViewById(R.id.button1);
        final FirebaseAuth mAuth;
        final FirebaseAuth.AuthStateListener mAuthListener;

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String pass = mPassword.getText().toString();
                if (!email.equals("") && !pass.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, pass);
                } else {
                    toastMessage("You didn't fill in all the fields.");
                }
                openMenu1();
            }

        });
        signup_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                openSignUp();
            }
        });


    }

    private void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void openMenu1(){
        Intent intent = new Intent(this, Menu1.class);
        startActivity(intent);
    }
    public void openSignUp(){
        Intent intent = new Intent(this, signUp.class);
        startActivity(intent);
    }

}