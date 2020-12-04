package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addRecipe<adapter> extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ImageView addB=findViewById(R.id.addB);

        ListView listView = (ListView) findViewById(R.id.mobile_list);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child("spices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                ArrayList<String> Userlist = new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    Userlist.add(String.valueOf(dsp.getValue()));
                }
                listView.setAdapter((ListAdapter) Userlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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