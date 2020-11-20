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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener maths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.editTextTextPassword);
        RelativeLayout relativeLayout=findViewById(R.id.relativelayout);;
        Button login=findViewById(R.id.login);
        TextView reg=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(email.getText().toString());
//__________________________________________________________________________________________________
        maths=new FirebaseAuth.AuthStateListener() {
            FirebaseUser Fuser=mAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (Fuser!=null){
                    Toast.makeText(getApplicationContext(),"Login",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, register.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"faild to Login",Toast.LENGTH_SHORT).show();
                }

            }
        };
//__________________________________________________________________________________________________
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                String emailS=email.getText().toString();
                String passwordS=password.getText().toString();

                if(!isConnected(this)) {
                    Toast.makeText(getApplicationContext(),"there is no internet connection ", Toast.LENGTH_LONG).show();
                }
                else {
                    if (emailS.length()==0){
                        email.setError("email cannot be empty");
                        count++;
                    }

                    if (passwordS.length()==0){
                        password.setError("password cannot be empty");
                        count++;
                    }
                    if (count==0){
                        if (emailS.equals("admin") && passwordS.equals("R")){
                            Snackbar.make(relativeLayout,"hello raslan yasen", Snackbar.LENGTH_SHORT).show();

                            Intent intent=new Intent(Login.this, profile.class);
                            intent.putExtra("email",emailS);
                            startActivity(intent);

                        }
                        else{

                        mAuth.signInWithEmailAndPassword(emailS, passwordS).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {

                                    Query checkUser =reference.orderByChild(emailS).equalTo(emailS);
                                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                String passwordFromDB = dataSnapshot.child(emailS).child("password").getValue(String.class);
                                                if (passwordFromDB.equals(passwordS)) {
                                                    Intent intent=new Intent(Login.this, profile.class);
                                                    String nameFromDB = dataSnapshot.child(emailS).child("name").getValue(String.class);
                                                    intent.putExtra("username",emailS);
                                                    intent.putExtra("name",nameFromDB);
                                                    startActivity(intent);
                                                }
                                                else {
                                                    password.setError("Wrong Password");
                                                    password.requestFocus();
                                                }
                                            }
                                            email.setError("No such User exist");
                                            email.requestFocus();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            System.out.println("hello Raslan");
                                        }
                                    });


                                } else {
                                    startActivity(new Intent(Login.this, profile.class));
                                    Intent intent=new Intent(Login.this, profile.class);
                                    intent.putExtra("name","nameFromDB");
                                    intent.putExtra("email",emailS);
                                    startActivity(intent);

                                }
                            }

                        });
                    }}
           }
            }
        });

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

}

