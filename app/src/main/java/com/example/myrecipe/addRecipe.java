package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class addRecipe<adapter> extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ImageView addB=findViewById(R.id.addB);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        final int[] m = {0};
        final String[] salt = new String[100];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child("spices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    salt[m[0]]=snapshot.child("salt").getValue().toString();
                    m[0]++;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentt=new Intent(addRecipe.this, addP.class);
                intentt.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
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