package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PatientSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        EditText fname = (EditText) findViewById(R.id.FName);
        EditText Lname = (EditText) findViewById(R.id.LName);
        EditText Bday= (EditText) findViewById(R.id.Birthday);
        EditText Height= (EditText) findViewById(R.id.Height);
        EditText Weight = (EditText) findViewById(R.id.Weight);
        EditText email= (EditText) findViewById(R.id.PEmail);
        EditText password= (EditText) findViewById(R.id.PPassword);

    }
    public void onClicksignup(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}