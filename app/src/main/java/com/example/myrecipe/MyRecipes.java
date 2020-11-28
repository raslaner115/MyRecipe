package com.example.myrecipe;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        ImageView recipeIMG=findViewById(R.id.recipeIMG);
        ImageView delete=findViewById(R.id.delete);
        ImageView edit=findViewById(R.id.edit);
        TextView spice=findViewById(R.id.spice);
        TextView Ingredients=findViewById(R.id.Ingredients);


    }
}