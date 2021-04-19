package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CaregiverMenuActivity extends AppCompatActivity {
    TextView nametxt;
    private FirebaseUser user;
    private FirebaseDatabase mydatabase;
    private DatabaseReference myref;
    private  String userID;

    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregivermenu);

        myAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();
        user= myAuth.getCurrentUser();
        myref= mydatabase.getReference("Caregiver");
        userID = user.getUid();
         nametxt= findViewById(R.id.CaregivernameTxt);


        myref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Caregiver currentCaregiver = snapshot.getValue(Caregiver.class);
                String fname = currentCaregiver.getFirstName();
                String lname =currentCaregiver.getLastName();
                nametxt.setText(fname+" "+lname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void onClickProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onClickAppointments(View view){
        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    public void onClickPractitioners(View view){
        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }

    public void onClickCovid(View view){
        Intent intent = new Intent(this, CovidActivity.class);
        startActivity(intent);
    }
}