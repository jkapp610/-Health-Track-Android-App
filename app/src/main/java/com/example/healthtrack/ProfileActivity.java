package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNew(View view){
        // Determine which type of medical record button was pressed, so we can set up the next
        // screen with the proper input fields (and later send to the correct spot in the database).
        // NOTE: there is probably a better way of doing this, but this will have to work for now...
        Button btn = (Button) view;
        Intent intent = new Intent(this, NewProfileActivity.class);
        if((Button) findViewById(R.id.cond0) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond1) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond2) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond3) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond4) == btn) intent.putExtra("key_type","Condition");

        else if((Button) findViewById(R.id.pres0) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres1) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres2) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres3) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres4) == btn) intent.putExtra("key_type","Prescription");

        else if((Button) findViewById(R.id.vacc0) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc1) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc2) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc3) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc4) == btn) intent.putExtra("key_type","Vaccine");

        startActivity(intent);
    }

}