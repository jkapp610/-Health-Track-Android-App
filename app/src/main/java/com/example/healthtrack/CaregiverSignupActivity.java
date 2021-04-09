package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CaregiverSignupActivity extends AppCompatActivity {


    EditText EditTextfname;
    EditText EditTextLname;
    EditText EditTextEmail;
    EditText EditTextpassword;
    Button signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        setContentView(R.layout.activity_cargiver_signup);
       EditTextfname= (EditText) findViewById(R.id.CaregiverFirstname);
       EditTextLname= (EditText) findViewById(R.id.CargiverLastname);
        EditTextEmail= (EditText) findViewById(R.id.CargiverEmail);
        EditTextpassword= (EditText) findViewById(R.id.CargiverPassword);
        signup = (Button) findViewById(R.id.SignupButton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargiversignup();

            }

            private void cargiversignup() {
                String Firstname = EditTextfname.getText().toString();
                String Lastname = EditTextLname.getText().toString();
                String Email = EditTextEmail.getText().toString();
                String Password = EditTextpassword.getText().toString();
                if (Firstname.isEmpty()) {
                    EditTextfname.setError("First name is required");
                    EditTextfname.requestFocus();
                    return;
                }
                if (Lastname.isEmpty()) {
                    EditTextLname.setError("Last name is required");
                    EditTextLname.requestFocus();
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
                    EditTextEmail.setError("Please provide valid email ");
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
                                    Caregiver newCatgiver = new Caregiver();
                                    newCatgiver.setFirstName(Firstname);
                                    newCatgiver.setLastName(Lastname);
                                    newCatgiver.setEmail(Email);
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference newref = database.getReference("Cargiver");
                                    newref.child(mAuth.getCurrentUser().getUid())
                                            .setValue(newCatgiver);

                                }

                            }
                        });




            }
        });




    }

}