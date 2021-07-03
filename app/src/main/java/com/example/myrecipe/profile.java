package com.example.myrecipe;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseStorage  mFirebaseStorage = FirebaseStorage.getInstance().getReference().getStorage();
        StorageReference desertRef = FirebaseStorage.getInstance().getReference().child((String)getIntent().getSerializableExtra("username")).child("my recipe").child((String)getIntent().getSerializableExtra("recipename"));
        StorageReference photoRef = mFirebaseStorage.getReferenceFromUrl(String.valueOf(desertRef));

        TextView email2=findViewById(R.id.email2);
        TextView username2=findViewById(R.id.username2);
        TextView Fname2=findViewById(R.id.Fname2);
        ImageView ProPic=findViewById(R.id.ProPic);

        email2.setText((String)getIntent().getSerializableExtra("email"));
        username2.setText((String)getIntent().getSerializableExtra("username"));
        Fname2.setText((String)getIntent().getSerializableExtra("name"));
        ProPic.setImageURI(Uri.parse(photoRef.toString()));

    }
}