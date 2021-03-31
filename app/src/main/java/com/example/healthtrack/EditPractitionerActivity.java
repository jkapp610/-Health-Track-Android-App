package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EditPractitionerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_practitioner);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickEdit(View view){
        // TODO: if valid data, send practitioner to the database; else prompt user to re-enter data

        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }


}