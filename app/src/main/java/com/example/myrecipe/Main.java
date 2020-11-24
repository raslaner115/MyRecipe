package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

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
                        Toast.makeText(getApplicationContext(),"home", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.profile:
                        Intent intent=new Intent(Main.this, profile.class);
                        intent.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intent.putExtra("name",(String)getIntent().getSerializableExtra("name"));
                        intent.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(intent);
                        return true;

                    case R.id.favorite:
                        Toast.makeText(getApplicationContext(),"favorite", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.food:
                        Toast.makeText(getApplicationContext(),"food", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.drink:
                        Toast.makeText(getApplicationContext(),"drink", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.add:
                        Intent intentt=new Intent(Main.this, addRecipe.class);
                        intentt.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(intentt);
                        return true;

                }
                return false;
            }
        });
    }
    int c=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (c==0){
                Toast.makeText(getApplicationContext(),"press another time to logout",Toast.LENGTH_SHORT).show();
                c=1;
                return true;
            }

            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    c=0;
                }
            }, 200);
            if(c==1){
                Toast.makeText(getApplicationContext(),"logout",Toast.LENGTH_SHORT).show();

            }

        }
        return false;
    }
}