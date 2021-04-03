package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class NewPractitionerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_practitioner);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send practitioner to the database; else prompt user to re-enter data
        //TODO: figure out how many buttons are currently visible on the previous screen & pick an
        // available button to store this information.
        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }


}