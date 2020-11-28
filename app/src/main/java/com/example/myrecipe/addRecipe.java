package com.example.myrecipe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class addRecipe<adapter> extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ImageView addB=findViewById(R.id.addB);


        addB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentt=new Intent(addRecipe.this, addP.class);
                intentt.putExtra("email",(String)getIntent().getSerializableExtra("email"));
                intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                startActivity(intentt);
            }
        });
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mobileArray);
        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }



    public void onBackPressed() {
       Intent intent= new Intent(addRecipe.this,Main.class);
        intent.putExtra("username", (String)getIntent().getSerializableExtra("username"));
        startActivity(intent);
    }
}