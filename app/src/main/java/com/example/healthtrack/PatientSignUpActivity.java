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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientSignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EditTextFirstName;
    EditText EditTextLastName;
    EditText EditTextBday;
    EditText EditTextHeight;
    EditText EditTextWeight;
    EditText EditTextEmail;
    EditText EditTextpassword;

    Button signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        mAuth = FirebaseAuth.getInstance();


        EditTextFirstName = (EditText) findViewById(R.id.CaregiverFirstname);
        EditTextLastName = (EditText) findViewById(R.id.pLName);
        EditTextBday = (EditText) findViewById(R.id.Birthday);
        EditTextHeight = (EditText) findViewById(R.id.Height);
        EditTextWeight = (EditText) findViewById(R.id.Weight);
        EditTextEmail = (EditText) findViewById(R.id.CargiverEmail);

        EditTextpassword = (EditText) findViewById(R.id.CargiverPassword);


        signup = (Button) findViewById(R.id.SignupButton);
        signup.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){

        setSignup();
    }

    private void setSignup() {
        String Firstname = EditTextFirstName.getText().toString();
        String Lastname = EditTextLastName.getText().toString();
        String Birthday = EditTextBday.getText().toString();
        String Height = EditTextHeight.getText().toString();
        String Weight = EditTextWeight.getText().toString();
        String Email = EditTextEmail.getText().toString();
        String Password = EditTextpassword.getText().toString();
        if (Firstname.isEmpty()) {
            EditTextFirstName.setError("First name is required");
            EditTextFirstName.requestFocus();
            return;
        }
        if (Lastname.isEmpty()) {
            EditTextLastName.setError("Last name is required");
            EditTextLastName.requestFocus();
            return;
        }
        if (Birthday.isEmpty()) {
            EditTextBday.setError("Birthday is required");
            EditTextBday.requestFocus();
            return;
        }
        if (Height.isEmpty()) {
            EditTextHeight.setError("Height is required");
            EditTextHeight.requestFocus();
            return;
        }
        if (Weight.isEmpty()) {
            EditTextWeight.setError("Weight is required");
            EditTextHeight.requestFocus();
            return;
        }
        if (Email.isEmpty()) {
            EditTextEmail.setError("Email is required");
            EditTextEmail.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            EditTextpassword.setError("Password is required");
            EditTextpassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            EditTextEmail.setError("Please provide valid email");
            EditTextEmail.requestFocus();
            return;

        }
        if(Password.length()<6) {
            EditTextpassword.setError("Password must be 6 characters long ");
            EditTextpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Patient newPatient = new Patient();
                            newPatient.setFirstName(Firstname);
                            newPatient.setLastName(Lastname);
                            newPatient.setBirthday(Birthday);
                            newPatient.setHeight(Height);
                            newPatient.setWeight(Weight);
                            newPatient.setEmail(Email);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference newref = database.getReference("Patients");
                             newref.child(mAuth.getCurrentUser().getUid())
                                     .setValue(newPatient);


                        }

                    }
                });
        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(PatientSignUpActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        });


            }


    public void Displaytoast(String f){

        Toast.makeText(PatientSignUpActivity.this,"the sign in is getting called"+ f,Toast.LENGTH_LONG).show();
        //Intent intent = new Intent(this, MainActivity.class);
       // startActivity(intent);
    }



    }

