package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addp);

        TextView WS=findViewById(R.id.WSpices);



        String WSpices=(String)getIntent().getSerializableExtra("spices");

        WS.setText(WSpices);

        TextView Ingredient=findViewById(R.id.Ingredients);
        TextView Spice=findViewById(R.id.Spices);

        Ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(addP.this,Ingredients.class));
            }
        });

        Spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentt=new Intent(addP.this, Spices.class);
                intentt.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                startActivity(intentt);
            }
        });
    }
}