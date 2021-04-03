package com.example.healthtrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class EditAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        //TODO actually get list of practitioners from the database!
        String[] practitioners = { "Dr. Hilton", "Dr. Sparks", "Dr. Allison", "Dr. Varde" };
        Spinner spin = findViewById(R.id.practitionerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, practitioners);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(adapter);

        // Pre-populated the fields from the previous screen based on pressed button.
        Intent intent = getIntent();
        String practitioner = intent.getStringExtra("key_practitioner");
        String date = intent.getStringExtra("key_date");
        String time = intent.getStringExtra("key_time");
        Boolean reminder = intent.getBooleanExtra("key_reminder",false);
        for(int i = 0; i < adapter.getCount(); i++) if(practitioner.equals(adapter.getItem(i))) spin.setSelection(i);
        EditText dateTxt = findViewById(R.id.dateTxt);
        dateTxt.setText(date);
        EditText timeTxt = findViewById(R.id.timeTxt);
        timeTxt.setText(time);
        Switch remindSwitch = findViewById(R.id.remindSwitch);
        remindSwitch.setChecked(reminder);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send appointment to the database; else prompt user to re-enter data

        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    public void onClickRemove(View view){
        // TODO: remove appointment from database

        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }


}