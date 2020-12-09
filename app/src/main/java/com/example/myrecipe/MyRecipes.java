package com.example.myrecipe;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child((String)getIntent().getSerializableExtra("recipename"));

        ImageView recipeIMG=findViewById(R.id.recipeIMG);
        ImageView delete=findViewById(R.id.delete);
        ImageView edit=findViewById(R.id.edit);

        TextView spices=findViewById(R.id.spice);
        TextView Ingredients=findViewById(R.id.Ingredients);
        TextView recipeName=findViewById(R.id.recipeName);
        recipeName.setText((String)getIntent().getSerializableExtra("recipename"));

        //__________________________________________________________________________________________
        Query getIngredients=ref.child("Ingredients");
        getIngredients.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                String dataKeys="";


                for (DataSnapshot child : snapshot.getChildren()){
                    if (child.getValue().toString().equals("true")){
                        dataKeys =dataKeys+" " +child.getKey();
                    }
                }
                Ingredients.setText(dataKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //__________________________________________________________________________________________
        Query getSpices=ref.child("spices");
        getIngredients.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                String dataKeys="";


                for (DataSnapshot child : snapshot.getChildren()){
                    if (child.getValue().toString().equals("true")){
                        dataKeys =dataKeys+" " +child.getKey();
                    }
                }
                spices.setText(dataKeys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}