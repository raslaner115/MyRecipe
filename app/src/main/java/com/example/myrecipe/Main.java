package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        NavigationView navigationView=findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(Main.this,profile.class));
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(Main.this,profile.class));
                        return true;

                    case R.id.favorite:
                        startActivity(new Intent(Main.this,profile.class));
                        return true;

                    case R.id.food:
                        startActivity(new Intent(Main.this,profile.class));
                        return true;

                    case R.id.drink:
                        startActivity(new Intent(Main.this,profile.class));
                        return true;


                }
                return false;
            }
        });

    }
}