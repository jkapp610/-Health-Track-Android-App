package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccounTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accoun_type);
    }
    public void onClickCaregiver(View view){
        Intent intent = new Intent(this, CargiverSignupActivity.class);
        startActivity(intent);
    }
    public void onClickPatient(View view){
        Intent intent = new Intent(this, PatientSignUpActivity.class);
        startActivity(intent);
    }
}