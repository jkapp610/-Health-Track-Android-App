package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        Intent intent = getIntent();
        String type = intent.getStringExtra("key_type");
        TextView title = findViewById(R.id.titleTxt);
        title.setText("Add " + type);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send appointment to the database; else prompt user to re-enter data

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}