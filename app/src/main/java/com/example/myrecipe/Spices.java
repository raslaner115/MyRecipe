package com.example.myrecipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Spices extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spices);



        String[] plantName={"rice","onion","carrot","potatoِ","Eggplant","zucchini","corn","Tomato"};
        Boolean IsSalt=false,IsPepper=false,IsCinnamon=false,IsCarom=false,IsGinger=false,IsCloves=false;

        Button Carom = findViewById(R.id.Carom);
        Button salt = findViewById(R.id.salt);
        Button Cinnamon = findViewById(R.id.Cinnamon);
        Button pepper = findViewById(R.id.pepper);
        Button Cloves = findViewById(R.id.Cloves);
        Button Ginger = findViewById(R.id.Ginger);

        Button save=findViewById(R.id.save);

        TextView CaromA = findViewById(R.id.CaromA);
        TextView GingerA = findViewById(R.id.GingerA);
        TextView ClovesA = findViewById(R.id.ClovesA);
        TextView pepperA = findViewById(R.id.pepperA);
        TextView CinnamonA = findViewById(R.id.CinnamonA);
        TextView saltA = findViewById(R.id.saltA);

        final String[] WSpices = {""};
        Boolean[] IsSpices={IsSalt,IsPepper,IsCinnamon,IsCloves,IsGinger,IsCarom};
        String[] SpicesName={"salt","pepper","cinnamon","cloves","ginger","carom"};
        Button[] spices = {salt, pepper, Cinnamon, Cloves, Ginger, Carom};

         for (int i=0;i<spices.length;i++){
             int ii=i;
             spices[i].setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (!IsSpices[ii]) {
                         IsSpices[ii] = true;
                         spices[ii].setBackgroundColor(Color.GREEN);
                     } else {
                         IsSpices[ii] = false;
                         spices[ii].setBackgroundColor(Color.LTGRAY);
                     }

                 }
             });
        }
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent sp=new Intent(Spices.this,addP.class);
                 sp.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                 for (int i=0;i<spices.length;i++){
                     if (IsSpices[i]){
                         WSpices[0]=WSpices[0]+"  "+SpicesName[i];
                     }
                     sp.putExtra(SpicesName[i],IsSpices[0].toString());

                 }
                 try {
                     for (int i=0;i<plantName.length;i++){
                         sp.putExtra(plantName[i],(String) getIntent().getSerializableExtra(SpicesName[i]));
                     }
                     sp.putExtra("plant",(String) getIntent().getSerializableExtra("plant"));

                 }
                 catch (Exception e){}
                 sp.putExtra("spices",WSpices[0]);
                 startActivity(sp);
             }
         });

    }
}