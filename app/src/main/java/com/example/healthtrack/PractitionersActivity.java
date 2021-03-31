package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PractitionersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practitioners);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNew(View view){
        Intent intent = new Intent(this, NewPractitionerActivity.class);
        startActivity(intent);
    }

    public void onClickEdit(View view){
        //TODO: init values for the specific practitioner we clicked on, so we can pre-populate the fields in
        // the next screen. We're already grabbing the data & we know which button was pressed, so
        // this is the easiest way to get the existing practitioner info.
        Intent intent = new Intent(this, EditPractitionerActivity.class);
        startActivity(intent);
    }

}