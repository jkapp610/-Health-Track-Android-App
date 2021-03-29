package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email = (EditText) findViewById(R.id.LoginEmail);
        EditText password = (EditText) findViewById(R.id.loginPassword);
    }
    public void onClickLogin(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onClickSignup(View view){
        Intent intent = new Intent(this, AccounTypeActivity.class);
        startActivity(intent);
    }
}