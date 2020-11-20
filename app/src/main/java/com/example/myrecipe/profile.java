package com.example.myrecipe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView email2=findViewById(R.id.email2);
        String email = (String)getIntent().getSerializableExtra("email");

        email2.setText(email);


    }

}