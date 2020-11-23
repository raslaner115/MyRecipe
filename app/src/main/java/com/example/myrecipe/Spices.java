package com.example.myrecipe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Spices extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spices);

        final int[] sa = {0,0,0,0,0,0};
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefU = database.getReference((String)getIntent().getSerializableExtra("username"));

        String[] spices;
        Button salt=findViewById(R.id.salt);
        TextView saltA=findViewById(R.id.saltA);

        salt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[0]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("salt").setValue("true");
                    sa[0]++;
                }
                else if (sa[0] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("salt").setValue("false");
                    sa[0]--;
                }

            }
        });
        //__________________________________________________________________________________________

        Button pepper=findViewById(R.id.pepper);
        TextView pepperA=findViewById(R.id.pepperA);

        pepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[1]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("pepper").setValue("true");
                    sa[1]++;
                }
                else if (sa[1] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("pepper").setValue("false");
                    sa[1]--;
                }
            }
        });
        //__________________________________________________________________________________________
        Button Cinnamon=findViewById(R.id.Cinnamon);
        TextView CinnamonA=findViewById(R.id.CinnamonA);

        Cinnamon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[2]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Cinnamon").setValue("true");
                    sa[2]++;
                }
                else if (sa[2] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Cinnamon").setValue("false");
                    sa[2]--;
                }
            }
        });
        //__________________________________________________________________________________________

        Button Cloves=findViewById(R.id.Cloves);
        TextView ClovesA=findViewById(R.id.ClovesA);

        Cloves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[3]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Cloves").setValue("true");
                    sa[3]++;
                }
                else if (sa[3] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Cloves").setValue("false");
                    sa[3]--;
                }
            }
        });
        //__________________________________________________________________________________________

        Button Ginger=findViewById(R.id.Ginger);
        TextView GingerA=findViewById(R.id.GingerA);

        Ginger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[4]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("ginger").setValue("true");
                    sa[4]++;
                }
                else if (sa[4] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("ginger").setValue("false");
                    sa[4]--;
                }
            }
        });
        //__________________________________________________________________________________________

        Button Carom=findViewById(R.id.Carom);
        TextView CaromA=findViewById(R.id.CaromA);


        Carom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa[5]==0){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Carom").setValue("true");
                    sa[5]++;
                }
                else if (sa[5] ==1){
                    myRefU.child((String)getIntent().getSerializableExtra("username")).child("spices").child("Carom").setValue("false");
                    sa[5]--;
                }
            }
        });
        //__________________________________________________________________________________________

    }
}