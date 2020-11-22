package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView propic=findViewById(R.id.ProPic);

        String email = (String)getIntent().getSerializableExtra("email");
        String username = (String)getIntent().getSerializableExtra("username");
        String name = (String)getIntent().getSerializableExtra("name");

        propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, profile.class);
                intent.putExtra("email", email);
                intent.putExtra("name", username);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        });
    }
}