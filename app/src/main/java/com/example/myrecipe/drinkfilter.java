package com.example.myrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class drinkfilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinkfilter);
        ListView listView =findViewById(R.id.list);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("all recipes");

        Query kinds = ref.orderByChild("kinds").equalTo("drinks");
        kinds.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dataKeys="";
                ArrayList<String> MyRecipeList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()){
                    dataKeys =child.getKey();
                    MyRecipeList.add(dataKeys);
                }

                listView.setAdapter(new ArrayAdapter<String>(drinkfilter.this,android.R.layout.simple_list_item_1, MyRecipeList));

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in=new Intent(drinkfilter.this,MyRecipes.class);
                        in.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                        in.putExtra("recipename",  ((TextView) view).getText().toString());
                        startActivity(in);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }
}