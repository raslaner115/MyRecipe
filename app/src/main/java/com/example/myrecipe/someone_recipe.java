package com.example.myrecipe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class someone_recipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_someone_recipe);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(("all recipes")).child((String)getIntent().getSerializableExtra("recipename"));
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username ")).child(("my recipe")).child("favorite");

        ImageView recipeIMG=findViewById(R.id.recipeIMG);
        ImageView follow=findViewById(R.id.follow);
        ImageView like=findViewById(R.id.like);

        TextView spices=findViewById(R.id.spice);
        TextView Ingredients=findViewById(R.id.Ingredients);
        TextView kind=findViewById(R.id.kind);
        TextView country=findViewById(R.id.country);
        TextView recipeName=findViewById(R.id.recipeName);
        recipeName.setText((String)getIntent().getSerializableExtra("recipename"));

        //__________________________________________________________________________________________

        Query getIngredients=ref.child("Ingredients");
        getIngredients.addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Ingredient="";
                for (DataSnapshot child : snapshot.getChildren()){
                    if (child.getValue().toString().equals("true")){
                        Ingredient =Ingredient+" " +child.getKey();
                    }
                }
                Ingredients.setText(Ingredient);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Query getSpices=ref.child("spices");
        getSpices.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotS) {
                String key = snapshotS.getKey();
                String spice="";

                for (DataSnapshot childS : snapshotS.getChildren()){
                    if (childS.getValue().toString().equals("true")){
                        spice =spice+" " +childS.getKey();
                    }
                }
                spices.setText(spice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //__________________________________________________________________________________________
        Query getCountry=ref.child("country");
        getCountry.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotS) {
                country.setText(snapshotS.getValue().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        //__________________________________________________________________________________________
        Query getKind=ref.child("kinds");
        getKind.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotS) {
                kind.setText(snapshotS.getValue().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (like.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.loke).getConstantState()){
                    like.setImageResource(R.drawable.likeunlike);
                    Toast.makeText(getApplicationContext(), "add to faviorty", Toast.LENGTH_SHORT).show();
                    myref.child((String)getIntent().getSerializableExtra("recipename"));

                }
                if (like.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.likeunlike).getConstantState()){
                    like.setImageResource(R.drawable.loke);
                    Toast.makeText(getApplicationContext(), "remove from faviorty", Toast.LENGTH_SHORT).show();
                    myref.child((String)getIntent().getSerializableExtra("recipename")).removeValue();


                }
            }
        });

        //__________________________________________________________________________________________


    }
}