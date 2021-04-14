package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText EditTextEmail;
    EditText EditTextpassword;
    private FirebaseAuth myAuth;
    private FirebaseUser user;
    private FirebaseDatabase mydatabase;
    private DatabaseReference myref;
    private  String userID;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     EditTextEmail= (EditText) findViewById(R.id.LoginEmail);
      EditTextpassword = (EditText) findViewById(R.id.loginPassword);
      login= (Button) findViewById(R.id.Login);
      myAuth = FirebaseAuth.getInstance();
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              userlogin();


          }

          private void userlogin() {
              String Email = EditTextEmail.getText().toString();
              String Password = EditTextpassword.getText().toString();
              if (Email.isEmpty()) {
                  EditTextEmail.setError("Email is required");
                  EditTextEmail.requestFocus();
                  return;
              }
              if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                  EditTextEmail.setError("Please provide valid email ");
                  EditTextEmail.requestFocus();
                  return;

              }
              if(Password.isEmpty()){
                  EditTextpassword.setError("Password is required");
                  EditTextpassword.requestFocus();
                  return;
              }

              if(Password.length()<6) {
                  EditTextpassword.setError("Password must be 6 characters long ");
                  EditTextpassword.requestFocus();
                  return;
              }

              myAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){

                          mydatabase = FirebaseDatabase.getInstance();
                          user= myAuth.getCurrentUser();
                          myref= mydatabase.getReference("Patients");
                          userID = user.getUid();
                          myref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {
                                  if(snapshot.exists()){


                                      Toast.makeText(LoginActivity.this,"user is a patient",Toast.LENGTH_LONG).show();
                                      Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                      startActivity(intent);


                                  }
                                  else{
                                      Toast.makeText(LoginActivity.this,"user is a cargiver",Toast.LENGTH_LONG).show();
                                      Intent intent = new Intent(LoginActivity.this, CaregiverMenuActivity.class);
                                      startActivity(intent);
                                  }

                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError error) {
                                  Toast.makeText(LoginActivity.this,"Something wrong Happened",Toast.LENGTH_LONG).show();

                              }
                          });





                      }
                      else {
                          Toast.makeText(LoginActivity.this,
                                  "Failed to login! Please check your credentials",
                                  Toast.LENGTH_LONG).show();
                      }

                  }
              });

          }
      });


    }



    public void onClickSignup(View view){
        Intent intent = new Intent(this, AccounTypeActivity.class);
        startActivity(intent);
    }
}