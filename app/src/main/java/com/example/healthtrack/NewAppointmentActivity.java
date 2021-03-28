package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NewAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        //TODO actually get list of practitioners from the database!
        String[] practitioners = { "Dr. Hilton", "Dr. Sparks", "Dr. Allison", "Dr. Varde" };
        Spinner spin = (Spinner) findViewById(R.id.practitionerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, practitioners);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(adapter);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}