package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class EditPractitionerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_practitioner);

        // Pre-populated the fields from the previous screen based on pressed button.
        Intent intent = getIntent();
        String name = intent.getStringExtra("key_name");
        String title = intent.getStringExtra("key_title");
        String address = intent.getStringExtra("key_address");
        EditText nameTxt = findViewById(R.id.nameTxt);
        nameTxt.setText(name);
        EditText titleTxt = findViewById(R.id.titleTxt);
        titleTxt.setText(title);
        EditText addressTxt = findViewById(R.id.addressTxt);
        addressTxt.setText(address);

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

    public void onClickRemove(View view){
        // TODO: remove practitioner from database

        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }

}