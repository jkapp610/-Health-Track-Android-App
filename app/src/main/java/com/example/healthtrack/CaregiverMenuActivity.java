package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CaregiverMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregivermenu);
    }


    public void onClickProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onClickAppointments(View view){
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    public void onClickPractitioners(View view){
        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }

    public void onClickCovid(View view){
        Intent intent = new Intent(this, CovidActivity.class);
        startActivity(intent);
    }
}