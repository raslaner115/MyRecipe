package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addRecipe<adapter> extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ImageView addB=findViewById(R.id.addB);

        ListView listView = (ListView) findViewById(R.id.mobile_list);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe");

        Query checkmail = ref.orderByChild("salt");
        checkmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String key = snapshot.getKey();
                String dataKeys="";

                ArrayList<String> MyRecipeList = new ArrayList<String>();

                for (DataSnapshot child : snapshot.getChildren()){
                    dataKeys =child.getKey();
                    MyRecipeList.add(dataKeys);
                }
                listView.setAdapter(new ArrayAdapter<String>(addRecipe.this,android.R.layout.simple_list_item_1, MyRecipeList));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentt = new Intent(addRecipe.this, addP.class);
                intentt.putExtra("email", (String) getIntent().getSerializableExtra("email"));
                intentt.putExtra("username", (String) getIntent().getSerializableExtra("username"));
                startActivity(intentt);
            }
        });
    }

        public void onBackPressed() {
       Intent intent= new Intent(addRecipe.this,Main.class);
        intent.putExtra("username", (String)getIntent().getSerializableExtra("username"));
        startActivity(intent);
    }
}