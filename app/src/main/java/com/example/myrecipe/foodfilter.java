package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class foodfilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodfilter);
        ListView listView = findViewById(R.id.list);
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("all recipes");
        Query kinds = ref.orderByChild("kinds").equalTo("food");
        kinds.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dataKeys = "";
                ArrayList<String> MyRecipeList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    dataKeys = child.getKey();
                    MyRecipeList.add(dataKeys);
                }
                listView.setAdapter(new ArrayAdapter<String>(foodfilter.this, android.R.layout.simple_list_item_1, MyRecipeList));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        myref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(((TextView) view).getText().toString())){
                                    Intent in=new Intent(foodfilter.this,MyRecipes.class);
                                    in.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                                    in.putExtra("recipename",  ((TextView) view).getText().toString());
                                    startActivity(in);
                                }
                                else {
                                    Intent in=new Intent(foodfilter.this,someone_recipe.class);
                                    in.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                                    in.putExtra("recipename",  ((TextView) view).getText().toString());
                                    startActivity(in);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        DatabaseReference reff= ref.child(((TextView) view).getText().toString()).child("user");
                        reff.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }


                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}