package com.example.myrecipe;

import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MyRecipes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(("all recipes")).child((String)getIntent().getSerializableExtra("recipename"));
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child((String)getIntent().getSerializableExtra("recipename"));

        FirebaseStorage  mFirebaseStorage = FirebaseStorage.getInstance().getReference().getStorage();

        StorageReference desertRef = FirebaseStorage.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child((String)getIntent().getSerializableExtra("recipename"));
        StorageReference photoRef = mFirebaseStorage.getReferenceFromUrl(String.valueOf(desertRef));

        ImageView recipeIMG=findViewById(R.id.recipeIMG);
        ImageView delete=findViewById(R.id.delete);


        TextView spices=findViewById(R.id.spice);
        TextView Ingredients=findViewById(R.id.Ingredients);
        TextView kind=findViewById(R.id.kind);
        TextView country=findViewById(R.id.country);
        TextView recipeName=findViewById(R.id.recipeName);

        recipeName.setText((String)getIntent().getSerializableExtra("recipename"));

        Picasso.with(this)
                .load(photoRef.toString())
                .centerCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .resize(90,90)
                .into(recipeIMG);

        //__________________________________________________________________________________________
        recipeIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"zzzzklgkuyjkilzhhzzzz",Toast.LENGTH_LONG).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myref.removeValue();
                ref.removeValue();
                desertRef.delete();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.exit(1);
                        finish();
                    }
                },3000);
            }
        });
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



        //__________________________________________________________________________________________


    }

}