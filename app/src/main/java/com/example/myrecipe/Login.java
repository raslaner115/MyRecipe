package com.example.myrecipe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth  auth =FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){

        }
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.editTextTextPassword);
        RelativeLayout relativeLayout=findViewById(R.id.relativelayout);;
        Button login=findViewById(R.id.login);
        TextView reg=findViewById(R.id.register);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(email.getText().toString());
//__________________________________________________________________________________________________
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isgood=true;
                String emailS=email.getText().toString();
                String passwordS=password.getText().toString();

                if(!isConnected(this)) {
                    Toast.makeText(getApplicationContext(),"there is no internet connection ", Toast.LENGTH_LONG).show();
                }
                else {
                    if (emailS.length()==0){
                        email.setError("email cannot be empty");
                        isgood=false;
                    }

                    if (passwordS.length()==0){
                        password.setError("password cannot be empty");
                        isgood=false;
                    }
                    if (isgood){
                            Query checkUser = (reference.orderByChild("username").equalTo(emailS));
                            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        String nameFromDB = dataSnapshot.child(emailS).child("name").getValue(String.class);
                                        String passwordFromDB = dataSnapshot.child(emailS).child("password").getValue(String.class);
                                        String emailFromDB = dataSnapshot.child(emailS).child("email").getValue(String.class);
                                        String usernameFromDB = dataSnapshot.child(emailS).child("username").getValue(String.class);

                                        if (passwordFromDB.equals(passwordS)) {
                                            Intent intent = new Intent(Login.this, Main.class);
                                            intent.putExtra("username", usernameFromDB);
                                            intent.putExtra("name", nameFromDB);
                                            intent.putExtra("email",emailFromDB);
                                            startActivity(intent);
                                        } else {
                                            password.setError("Wrong Password");
                                            password.requestFocus();
                                        }
                                    }
                                    else {
                                        Query checkmail = reference.orderByChild("email").equalTo(emailS);
                                        checkmail.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshotE) {
                                                if (snapshotE.exists()) {
                                                    String key = snapshotE.getKey();
                                                    String dataKeys="";

                                                    for (DataSnapshot child : snapshotE.getChildren()) {
                                                        dataKeys =child.getKey();
                                                    }
                                                     Toast.makeText(getApplicationContext(),dataKeys,Toast.LENGTH_LONG).show();
                                                    String nameFromDB = snapshotE.child(dataKeys).child("name").getValue(String.class);
                                                    String passwordFromDB = snapshotE.child(dataKeys).child("password").getValue(String.class);
                                                    String emailFromDB = snapshotE.child(dataKeys).child("email").getValue(String.class);
                                                    String usernameFromDB = snapshotE.child(dataKeys).child("username").getValue(String.class);

                                                    if (passwordFromDB.equals(passwordS)) {
                                                        Intent intent = new Intent(Login.this, Main.class);
                                                        intent.putExtra("username", usernameFromDB);
                                                        intent.putExtra("name", nameFromDB);
                                                        intent.putExtra("email",emailS);
                                                        startActivity(intent);
                                                    } else {
                                                        password.setError("Wrong Password");
                                                        password.requestFocus();
                                                    }
                                                }
                                                else {
                                                    email.setError("user not exist");
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }}}});
//__________________________________________________________________________________________________
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isConnected(this)) {
                    Toast.makeText(getApplicationContext(),"there is no internet connection ", Toast.LENGTH_LONG).show();

                }else {

                    startActivity(new Intent(Login.this, register.class));

                }
            }
        });
    }
//__________________________________________________________________________________________________
    private boolean isConnected(View.OnClickListener onClickListener) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected(); }
//__________________________________________________________________________________________________
    public void onBackPressed() {
        System.exit(1);
    }
}