package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    ImageButton notifyBtn;
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

        //Start of notification work
        notifyBtn = findViewById(R.id.imageButton2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notifications go here
                //First Notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText("You are scheduled for an appointment with Dr.Hilton, February 28th 2021 at 9:30AM."));
                builder.setSmallIcon(R.drawable.ic_baseline_medical_services_24);
                builder.setContentTitle("Medical Appointment");
                builder.setContentText("You are scheduled for an appointment with Dr.Hilton, February 28th 2021 at 9:30AM.");
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());

                //Second Notification
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText("1st COVID-19 vaccine is scheduled on May 25th, at 12:25 pm."));
                builder.setSmallIcon(R.drawable.ic_baseline_colorize_24);
                builder.setContentTitle("Vaccination Alert");
                builder.setContentText("1st COVID-19 vaccine is scheduled on May 25th, at 12:25 pm.");
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat2 = NotificationManagerCompat.from(MainActivity.this);
                managerCompat2.notify(2,builder.build());

                //Third Notification
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Don't forget to take your Lisnospril today!"));
                builder.setSmallIcon(R.drawable.ic_baseline_local_pharmacy_24);
                builder.setContentTitle("Prescription Alert");
                builder.setContentText("Don't forget to take your Lisnospril today!");
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat3 = NotificationManagerCompat.from(MainActivity.this);
                managerCompat3.notify(3,builder.build());
            }
        });

        // Database stuffz

        myAuth = FirebaseAuth.getInstance();
        mydatabase = FirebaseDatabase.getInstance();
        user= myAuth.getCurrentUser();
        myref= mydatabase.getReference("Patients");
        userID = user.getUid();



        myref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Patient CurrentPatient = snapshot.getValue(Patient.class);
                String FName = CurrentPatient.getFirstName();
                String LName = CurrentPatient.getLastName();
                nameTxt.setText(FName+" "+LName);




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
        Intent intent = new Intent(this, CovidandVaccinesActivity.class);
        startActivity(intent);
    }


}