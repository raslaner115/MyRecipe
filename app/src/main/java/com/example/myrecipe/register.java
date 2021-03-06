package com.example.myrecipe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register extends AppCompatActivity {
    private  ImageView Propic;
    public   Uri imageuri;
    private FirebaseStorage storage;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//__________________________________________________________________________________________________
        EditText email=findViewById(R.id.email);
        EditText fname=findViewById(R.id.full_name);
        EditText password=findViewById(R.id.password);
        EditText username=findViewById(R.id.username);
        Button reg=findViewById(R.id.register);
        Propic=findViewById(R.id.ProPic);

        mStorageRef=FirebaseStorage.getInstance().getReference();

//__________________________________________________________________________________________________
        Propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectAPic();
            }
        });
//sign in firebase database_________________________________________________________________________
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isConnected(this)){
                     Toast.makeText(getApplicationContext(), "there is no internet connection", Toast.LENGTH_LONG).show();

                }
                else {
                    String email2 = email.getText().toString();
                    String fname2 = fname.getText().toString();
                    String password2 = password.getText().toString();
                    String username2 = username.getText().toString();

                    char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZا أ ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ة ء ئ ؤ ".toCharArray();
                    char[] passwordC = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#").toCharArray();
                    char[] userc = "abcdefghijklmnopqrstuvwxyz1234567890_-.".toCharArray();

                    char[] user = username2.toCharArray();
                    char[] name = fname2.toCharArray();
                    char[] pass = password2.toCharArray();
                    char[] mail = email2.toCharArray();

                     boolean Iswrong = true;

//connect to the firebase___________________________________________________________________________

                    DatabaseReference myRef =FirebaseDatabase.getInstance().getReference(username2);
//check password___________________________________________________________________________________
                    if (password2.length()<8){
                        password.setError("invaild password");
                        Iswrong =false;
                    }
                    else if(!checkarray(pass,passwordC)) {
                        password.setError("invaild leatter");
                        Iswrong = false;
                    }
                    //check name________________________________________________________________________________________
                    if(fname2.length()<6){
                        fname.setError("invield name");
                        Iswrong =false;
                    }
                    else if(!checkarray(name,alphabet)){
                        fname.setError("invaild leatter");
                        Iswrong =false;
                    }
//check username____________________________________________________________________________________
                    if(username2.length()<6){
                        username.setError("invield name");
                        Iswrong =false;
                    }
                    else  if (!checkarray(user,userc)){
                        username.setError("invield letter");
                        Iswrong =false;
                    }
//check username____________________________________________________________________________________
                    if (!isValidEmail(email2)){
                        email.setError("invield email");
                        Iswrong =false;
                    }
////check if there some thing wrong_________________________________________________________________
                    if (Iswrong){
                        myRef.child("name").setValue(fname2);
                        myRef.child("password").setValue(password2);
                        myRef.child("email").setValue((email2));
                        myRef.child("username").setValue(username2);
                        uploadpic(username2);

                        startActivity(new Intent(register.this,Login.class));
                    }}}
        });}
//check conniction__________________________________________________________________________________

    private boolean isConnected(View.OnClickListener onClickListener) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected(); }

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
            if (imageuri != null) {
                Propic.setImageURI(imageuri);
            }
        }
    }

//upload the pic to firebase storage________________________________________________________________

    private void uploadpic(String username) {
        if (imageuri != null) {
            StorageReference riversRef = mStorageRef.child(username).child("profile img");
            riversRef.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // make a massage in the bottom
                    Snackbar.make(findViewById(android.R.id.content), "image uploaded", Snackbar.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), "fail to upload the picture", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double proper = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        }
                    });
        }
    }

//helper funcntion check if the leatters in array ar1 in ar2________________________________________

    public boolean checkarray(char ar1[],char ar2[]){

        for (int i=0;i<ar1.length;i++){
            boolean check = false;
            for (int j=0;j<ar2.length;j++){
                if (ar1[i] == ar2[j]) {
                    check = true;
                }}
            if (!check){
                return false;
            }}
        return true;
    }

//helper function check email_______________________________________________________________________

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}