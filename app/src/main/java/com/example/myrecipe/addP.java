package com.example.myrecipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addP extends AppCompatActivity {
    private ImageView pic;
    private StorageReference mStorageRef= FirebaseStorage.getInstance().getReference();
    public Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addp);

        TextView WS=findViewById(R.id.WSpices);
        String WSpices = (String) getIntent().getSerializableExtra("spices");
        WS.setText(WSpices);

        TextView Wp=findViewById(R.id.Ingredients2);
        String Wplant = (String) getIntent().getSerializableExtra("plant");
        Wp.setText(Wplant);

        Spinner kind =findViewById(R.id.kind);
        Spinner country =findViewById(R.id.country);

        String username=(String)getIntent().getSerializableExtra("username");
        EditText Rname=findViewById(R.id.name);
        TextView Ingredient=findViewById(R.id.Ingredients);
        TextView Spice=findViewById(R.id.Spices);
        Button save=findViewById(R.id.save);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefU = database.getReference((String)getIntent().getSerializableExtra("username"));
        DatabaseReference myRefA = database.getReference("all recipes");

        String[] plantName={"rice","onion","carrot","potato","Eggplant","zucchini","corn","Tomato"};
        String[] SpicesName={"salt","pepper","cinnamon","cloves","ginger","carom"};

        ArrayAdapter<CharSequence> kinds = ArrayAdapter.createFromResource(this,
                R.array.kind_array, android.R.layout.simple_spinner_item);
        kinds.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kind.setAdapter(kinds);

        ArrayAdapter<CharSequence> countries = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);
        countries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countries);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Rname.getText().toString()).length()==0){
                    Rname.setError("name can't be empty");
                }
                else if((Wp.toString().length()==0)&&(WS.toString().length()==0)){
                    Ingredient.setError("at least one of them");
                    Spice.setError("at least one of them");

                }
                else {
                    for (int i=0;i<SpicesName.length;i++) {
                        myRefU.child("my recipe").child(Rname.getText().toString()).child("spices").child(SpicesName[i]).setValue((String) getIntent().getSerializableExtra(SpicesName[i]));
                        myRefA.child(Rname.getText().toString()).child("spices").child(SpicesName[i]).setValue((String) getIntent().getSerializableExtra(SpicesName[i]));
                    }
                    for (int i=0;i<plantName.length;i++) {
                        myRefU.child("my recipe").child(Rname.getText().toString()).child("Ingredients").child(plantName[i]).setValue((String) getIntent().getSerializableExtra(plantName[i]));
                        myRefA.child(Rname.getText().toString()).child("Ingredients").child(plantName[i]).setValue((String) getIntent().getSerializableExtra(plantName[i]));
                    }
                    myRefU.child("my recipe").child(Rname.getText().toString()).child("kinds").setValue(kind.getSelectedItem().toString());
                    myRefU.child("my recipe").child(Rname.getText().toString()).child("country").setValue(country.getSelectedItem().toString());
                    myRefA.child(Rname.getText().toString()).child("kinds").setValue(kind.getSelectedItem().toString());
                    myRefA.child(Rname.getText().toString()).child("user").setValue((String)getIntent().getSerializableExtra("username"));
                    myRefA.child(Rname.getText().toString()).child("country").setValue(country.getSelectedItem().toString());
                    String Rname1=Rname.getText().toString();
                    uploadpic(username,Rname1);
                    Intent intent = new Intent(addP.this, addRecipe.class);
                    intent.putExtra("username", (String)getIntent().getSerializableExtra("username"));
                    startActivity(intent);
                }
            }
        });


        Ingredient.setOnClickListener(v -> {

            Intent intent = new Intent(addP.this, Ingredients.class);
            intent.putExtra("username", (String)getIntent().getSerializableExtra("username"));
            try {
                for (int i=0;i<SpicesName.length;i++){
                    intent.putExtra(SpicesName[i],(String) getIntent().getSerializableExtra(SpicesName[i]));
                }
                intent.putExtra("spices",(String) getIntent().getSerializableExtra("spices"));
            }
            catch (Exception e){Toast.makeText(getApplicationContext(),"wrong!!!!!@!@!@!#$$#@!",Toast.LENGTH_SHORT).show();}
            startActivity(intent);


        });

        Spice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt=new Intent(addP.this, Spices.class);
                intentt.putExtra("username",(String)getIntent().getSerializableExtra("username"));
                try {
                    for (int i=0;i<plantName.length;i++){
                        intentt.putExtra(plantName[i],(String) getIntent().getSerializableExtra(SpicesName[i]));
                    }
                    intentt.putExtra("plant",(String) getIntent().getSerializableExtra("plant"));
                }
                catch (Exception e){Toast.makeText(getApplicationContext(),"wrooooooong!!!!!@!@!@!#$$#@!",Toast.LENGTH_SHORT).show();}
                startActivity(intentt);
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
        Intent intent = new Intent(addP.this, addRecipe.class);
        intent.putExtra("username", (String)getIntent().getSerializableExtra("username"));
        startActivity(intent);    }
    //open galery_______________________________________________________________________________________
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

//upload the pic to firebase storage________________________________________________________________

    private void uploadpic(String username,String Rname) {
        StorageReference riversRef = mStorageRef.child(username).child("my recipe").child(Rname);
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // make a massage in the bottom
                        Snackbar.make(findViewById(android.R.id.content),"image uploaded",Snackbar.LENGTH_LONG).show();
                        riversRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                task.getResult();


                            }
                        });

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

}