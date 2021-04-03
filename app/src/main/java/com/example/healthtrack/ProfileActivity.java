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
        //TODO: figure out whether its a "health condition", "prescription", or "vaccine"
//        Button btn = (Button) view;
//        Intent intent = new Intent(this, NewProfileActivity.class);
//        if((Button) findViewById(R.id.cond0) == btn) intent.putExtra("key_btn","cond");
//        else if((Button) findViewById(R.id.cond1) == btn) intent.putExtra("key_btn","cond");
//        else if((Button) findViewById(R.id.cond2) == btn) intent.putExtra("key_btn","cond");
//        else if((Button) findViewById(R.id.cond3) == btn) intent.putExtra("key_btn","cond");
//        else if((Button) findViewById(R.id.cond4) == btn) intent.putExtra("key_btn","cond");

//        startActivity(intent);
    }

}