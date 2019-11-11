package com.n01249089.lumimonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Menu1 extends AppCompatActivity {
    private static Button temp_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        temp_button = findViewById(R.id.button);
        temp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu2();
            }
        });
    }

    public void openMenu2(){
        Intent intent = new Intent(this, temperature.class);
        startActivity(intent);
    }

}


