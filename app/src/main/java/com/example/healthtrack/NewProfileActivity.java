package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

        // Display the correct fields for the previously selected type
        TextView textBox;
        switch (type) {
            case "Condition":
                textBox = findViewById(R.id.editTextTime);
                textBox.setVisibility(View.GONE);
                textBox = findViewById(R.id.editTextDate);
                textBox.setVisibility(View.GONE);
                textBox = findViewById(R.id.remindSwitch);
                textBox.setVisibility(View.GONE);
                if(intent.getBooleanExtra("key_edit",false)){
                    title.setText("Edit " + type);
                    String name = intent.getStringExtra("key_name");
                    TextView txt = findViewById(R.id.editTxt);
                    txt.setText(name);
                }
                break;
            case "Prescription":
                textBox = findViewById(R.id.editTextTime);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.editTextDate);
                textBox.setVisibility(View.GONE);
                textBox = findViewById(R.id.remindSwitch);
                textBox.setVisibility(View.VISIBLE);
                if(intent.getBooleanExtra("key_edit",false)){
                    title.setText("Edit " + type);
                    String name = intent.getStringExtra("key_name");
                    TextView txt = findViewById(R.id.editTxt);
                    txt.setText(name);
                    String time = intent.getStringExtra("key_time");
                    EditText timeTxt = findViewById(R.id.editTextTime);
                    timeTxt.setText(time);
                    Boolean reminder = intent.getBooleanExtra("key_reminder",false);
                    Switch remindSwitch = findViewById(R.id.remindSwitch);
                    remindSwitch.setChecked(reminder);
                }
                break;
            case "Vaccine":
                textBox = findViewById(R.id.editTextTime);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.editTextDate);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.remindSwitch);
                textBox.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send to the database; else prompt user to re-enter data

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}