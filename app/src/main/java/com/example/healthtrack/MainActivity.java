package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView nameTxt;
    private FirebaseUser user;
    private FirebaseDatabase mydatabase;
    private DatabaseReference myref;
    private  String userID;

    private  FirebaseAuth myAuth;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        myAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();
        user= myAuth.getCurrentUser();
        myref= mydatabase.getReference("Patients");
        userID = user.getUid();



        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userID)){


                   Toast.makeText(MainActivity.this,"user is a patient",Toast.LENGTH_LONG).show();


                }
                else{
                    Toast.makeText(MainActivity.this,"user is a cargiver",Toast.LENGTH_LONG).show();
                }
               /* Patient CurrentPatient = snapshot.getValue(Patient.class);
                String FName = CurrentPatient.getFirstName();
                String LName = CurrentPatient.getLastName();
                nameTxt.setText(FName+" "+LName);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"Something wrong Happened",Toast.LENGTH_LONG).show();

            }
        });


        //nameTxt.setText("Billy Bob Joe"); //TODO get patient name from database

        logout = (Button)  findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
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