package com.example.myrecipe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Spices extends AppCompatActivity implements View.OnClickListener {

    final int[] sa = {0, 0, 0, 0, 0, 0};


    Button Carom = findViewById(R.id.Carom);
    Button salt = findViewById(R.id.salt);
    Button Cinnamon = findViewById(R.id.Cinnamon);
    Button pepper = findViewById(R.id.pepper);
    Button Cloves = findViewById(R.id.Cloves);
    Button Ginger = findViewById(R.id.Ginger);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spices);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefU = database.getReference((String) getIntent().getSerializableExtra("username"));


        TextView CaromA = findViewById(R.id.CaromA);
        TextView GingerA = findViewById(R.id.GingerA);
        TextView ClovesA = findViewById(R.id.ClovesA);
        TextView pepperA = findViewById(R.id.pepperA);
        TextView CinnamonA = findViewById(R.id.CinnamonA);
        TextView saltA = findViewById(R.id.saltA);

         Button[] spices = {salt, pepper, Cinnamon, Cloves, Ginger, Carom};

        for (int i = 0; i < spices.length; i++) {
            spices[i].setOnClickListener(this);
        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.salt:
                if (sa[0]==0) {
                    Toast.makeText(getApplicationContext(), "salt selected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.GREEN);
                    sa[0]++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "salt unselected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.LTGRAY);
                    sa[0]--;
                }
            case R.id.Carom:

                if (sa[1]==0) {
                    Toast.makeText(getApplicationContext(), "carom selected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.GREEN);
                    sa[0]++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "carom unselected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.LTGRAY);
                    sa[1]--;
                }
            case R.id.Cinnamon:

                if (sa[2]==0) {
                    Toast.makeText(getApplicationContext(), "salt selected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.GREEN);
                    sa[2]++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "salt unselected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.LTGRAY);
                    sa[2]--;
                }
            case R.id.pepper:

                if (sa[3]==0) {
                    Toast.makeText(getApplicationContext(), "salt selected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.GREEN);
                    sa[3]++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "salt unselected", Toast.LENGTH_SHORT).show();
                    salt.setBackgroundColor(Color.LTGRAY);
                    sa[3]--;
                }
        }
    }
}