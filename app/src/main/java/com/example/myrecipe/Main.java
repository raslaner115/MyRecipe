package com.example.myrecipe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar toolbar=findViewById(R.id.appBar);
        NavigationView navigationView=findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.bar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.DraweLayot);
        mDrawerLayout.closeDrawers();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"you at home", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.profile:
                        Intent intent=new Intent(Main.this, profile.class);
                        intent.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intent.putExtra("name",(String)getIntent().getSerializableExtra("name"));
                        intent.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(intent);
                        return true;

                    case R.id.food:
                        Intent intent1=new Intent(Main.this, foodfilter.class);
                        intent1.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intent1.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        intent1.putExtra("kind","food");
                        startActivity(intent1);
                        return true;

                    case R.id.drink:
                        Intent inten=new Intent(Main.this, drinkfilter.class);
                        inten.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        inten.putExtra("username",(String)getIntent().getSerializableExtra("username"));

                        startActivity(inten);
                        return true;

                    case R.id.add:
                        Intent inte=new Intent(Main.this, addRecipe.class);
                        inte.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        inte.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(inte);
                        return true;

                    case R.id.sweat:
                        Intent intee=new Intent(Main.this, sweatfilter.class);
                        intee.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intee.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(intee);
                        return true;

                    case R.id.follow:
                        Intent intee1=new Intent(Main.this, addRecipe.class);
                        intee1.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intee1.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        startActivity(intee1);
                        return true;

                    case R.id.favorite:
                        Intent intentt=new Intent(Main.this, addRecipe.class);
                        intentt.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                        intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        intentt.putExtra("kind","favorite");
                        startActivity(intentt);
                        return true;
                }

                return false;
            }
        });
    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startActivity(new Intent(Main.this,Login.class));
            super.onBackPressed();
            return;
        }
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to logout", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}