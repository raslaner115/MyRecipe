package com.example.myrecipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class Spices extends AppCompatActivity  {
    HashMap<String,Boolean> spice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spices);

        spice=new HashMap<>();
        String[] SpicesName={"salt","pepper","cinnamon","cloves","ginger","carom"};
        String[] plantName={"rice","onion","carrot","potato?","Eggplant","zucchini","corn","Tomato"};
        for (String s : SpicesName) {
            spice.put(s,false);
        }

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
        Button[] spices = {salt, pepper, Cinnamon, Cloves, Ginger, Carom};

        for (int i=0;i<spices.length;i++){
            int ii=i;
            spices[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spice.put(SpicesName[ii],true);
                    spices[ii].setBackgroundColor(Color.GREEN);
                }
            });
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp=new Intent(Spices.this,addP.class);
                sp.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                for (int i=0;i<spices.length;i++){
                    if (spice.get(SpicesName[i])){
                        WSpices[0]=WSpices[0]+"  "+SpicesName[i];
                    }
                    sp.putExtra(SpicesName[i],spice.put(SpicesName[i],true).toString());

                }
                try {
                    for (int i=0;i<plantName.length;i++){
                        sp.putExtra(plantName[i],(String) getIntent().getSerializableExtra(plantName[i]));
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