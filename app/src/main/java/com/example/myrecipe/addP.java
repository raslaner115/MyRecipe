package com.example.myrecipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addP extends AppCompatActivity {
    private ImageView pic;
    private StorageReference mStorageRef;
    public Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addp);

        TextView WS=findViewById(R.id.WSpices);
        final String[] WSpices = {(String) getIntent().getSerializableExtra("spices")};
        WS.setText(WSpices[0]);

        EditText Rname=findViewById(R.id.name);
        TextView Ingredient=findViewById(R.id.Ingredients);
        TextView Spice=findViewById(R.id.Spices);
        Button save=findViewById(R.id.save);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference MyRecipeE = database.getReference(DotToPlus(new StringBuilder((String)getIntent().getSerializableExtra("email"))));
        DatabaseReference MyRecipeU = database.getReference((String)getIntent().getSerializableExtra("username"));
        DatabaseReference AllRecipe = database.getReference("all recipe");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRecipeE.child("my recipe").child("spices").child("salt").setValue((Boolean)getIntent().getSerializableExtra("IsSalt"));
                MyRecipeE.child("my recipe").child("spices").child("Pepper").setValue((Boolean)getIntent().getSerializableExtra("IsPepper"));
                MyRecipeE.child("my recipe").child("spices").child("Cinnamon").setValue((Boolean)getIntent().getSerializableExtra("IsCinnamon"));
                MyRecipeE.child("my recipe").child("spices").child("Cloves").setValue((Boolean)getIntent().getSerializableExtra("IsCloves"));
                MyRecipeE.child("my recipe").child("spices").child("Ginger").setValue((Boolean)getIntent().getSerializableExtra("IsGinger"));
                MyRecipeE.child("my recipe").child("spices").child("Carom").setValue((Boolean)getIntent().getSerializableExtra("IsCarom"));

                MyRecipeU.child("my recipe").child("spices").child("salt").setValue((Boolean)getIntent().getSerializableExtra("IsSalt"));
                MyRecipeU.child("my recipe").child("spices").child("Pepper").setValue((Boolean)getIntent().getSerializableExtra("IsPepper"));
                MyRecipeU.child("my recipe").child("spices").child("Cinnamon").setValue((Boolean)getIntent().getSerializableExtra("IsCinnamon"));
                MyRecipeU.child("my recipe").child("spices").child("Cloves").setValue((Boolean)getIntent().getSerializableExtra("IsCloves"));
                MyRecipeU.child("my recipe").child("spices").child("Ginger").setValue((Boolean)getIntent().getSerializableExtra("IsGinger"));
                MyRecipeU.child("my recipe").child("spices").child("Carom").setValue((Boolean)getIntent().getSerializableExtra("IsCarom"));

                AllRecipe.child("my recipe").child("spices").child("salt").setValue((Boolean)getIntent().getSerializableExtra("IsSalt"));
                AllRecipe.child("my recipe").child("spices").child("Pepper").setValue((Boolean)getIntent().getSerializableExtra("IsPepper"));
                AllRecipe.child("my recipe").child("spices").child("Cinnamon").setValue((Boolean)getIntent().getSerializableExtra("IsCinnamon"));
                AllRecipe.child("my recipe").child("spices").child("Cloves").setValue((Boolean)getIntent().getSerializableExtra("IsCloves"));
                AllRecipe.child("my recipe").child("spices").child("Ginger").setValue((Boolean)getIntent().getSerializableExtra("IsGinger"));
                AllRecipe.child("my recipe").child("spices").child("Carom").setValue((Boolean)getIntent().getSerializableExtra("IsCarom"));


                uploadpic((String)getIntent().getSerializableExtra("email"),Rname.toString());
                uploadpic((String)getIntent().getSerializableExtra("username"),Rname.toString());
            }
        });

        Ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(addP.this,Ingredients.class));
            }
        });

        Spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WSpices[0] =null;
                WS.setText(null);
                startActivity(new Intent(addP.this, Spices.class));
            }
        });

         pic=findViewById(R.id.pic);

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAPic();
            }
        });

    }
    @Override
    public void onBackPressed() {
    startActivity(new Intent(addP.this,addRecipe.class));
    }
//__________________________________________________________________________________________________
    private void SelectAPic() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(i.ACTION_GET_CONTENT);
        startActivityForResult(i,1);

    }

//change the pic____________________________________________________________________________________
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1&&resultCode==RESULT_OK &&data!=null &&data.getData()!=null){
            imageuri=data.getData();
            pic.setImageURI(imageuri);
        }
    }

//__________________________________________________________________________________________________

    private void uploadpic(String username,String Rname) {

        StorageReference riversRef = mStorageRef.child(username).child("my recipe").child(Rname);
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // make a massage in the bottom
                        Snackbar.make(findViewById(android.R.id.content),"image uploaded",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"fail to upload the picture",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double proper =(100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    }
                });
    }

    public String DotToPlus(StringBuilder email){

        String Semail=email.toString();
        char[] CSemail=Semail.toCharArray();

        for (int i=0;i<CSemail.length;i++){
            if (CSemail[i]=='.'){
                email.setCharAt(i,'+');
            }
        }
        return email.toString();
    }

}