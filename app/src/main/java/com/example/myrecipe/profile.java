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
        TextView username2=findViewById(R.id.username2);
        TextView name2=findViewById(R.id.name2);

        String email = (String)getIntent().getSerializableExtra("email");
        String username = (String)getIntent().getSerializableExtra("username");
        String name = (String)getIntent().getSerializableExtra("name");

        email2.setText(email);
        username2.setText(username);
        name2.setText(name);


    }
}