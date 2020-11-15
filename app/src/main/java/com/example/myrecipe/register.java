package com.example.myrecipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class register extends AppCompatActivity {
    private  ImageView Propic;
    public   Uri imageuri;
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
                    Toast.makeText(getApplicationContext(),"there is no internet conniction ", Toast.LENGTH_LONG).show();
                }
                else {
                    String email2 = email.getText().toString();
                    String fname2 = fname.getText().toString();
                    String password2 = password.getText().toString();
                    String username2 = username.getText().toString();

                    char[] alphabet = "abcdefghijklmnopq rstuvwxyzا أ ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ة ء ئ ؤ ".toCharArray();
                    char[] passwordC = "abcdefghijklmnopq rstuvwxyz1234567890!@#$%&".toCharArray();
                    char[] userc = "abcdefghijklmnopq.rstuvwxyz1234567890_-".toCharArray();

                    char[] user = username2.toCharArray();
                    char[] name = fname2.toCharArray();
                    char[] pass = password2.toCharArray();
                    char[] mail = email2.toCharArray();

                    int y=0;
//connect to the firebase___________________________________________________________________________
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(username2);
                    mStorageRef = FirebaseStorage.getInstance().getReference();
//check password____________________________________________________________________________________
                    if (password2.length()<8){
                        password.setError("invaild password");
                        y++;}
                    else if(!checkarray(pass,passwordC)){
                        password.setError("invaild leatter");
                        y++;}
//check name________________________________________________________________________________________
                    if(fname2.length()<6){
                        fname.setError("invield name");
                        y++;
                    }
                    else if(!checkarray(name,alphabet)){
                        fname.setError("invaild leatter");
                        y++;
                    }
//check username____________________________________________________________________________________
                    if(username2.length()<6){
                        username.setError("invield name");
                        y++;
                    }
                    else  if (!checkarray(user,userc)){
                        username.setError("invield letter");
                        y++;
                    }
//check if there some thing wrong___________________________________________________________________
                    if (y==0){
                        myRef.child("full name").setValue(fname2);
                        myRef.child("password").setValue(password2);
                        myRef.child("email").setValue("wait...");
                        startActivity(new Intent(register.this,Login.class));
                    }}}

        });
    }


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
    onActivityResult(1,RESULT_OK,i);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&requestCode==RESULT_OK &&data!=null &&data.getData()!=null){
            imageuri=data.getData();
            Propic.setImageURI(imageuri);
            uploadpic();
        }
    }

    private void uploadpic() {

        StorageReference riversRef = mStorageRef.child("image/");
        riversRef.putFile(imageuri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // make a massage int bottom
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
//__________________________________________________________________________________________________

}