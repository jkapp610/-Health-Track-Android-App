package com.example.healthtrack;

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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView nameTxt;
    Button notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = (TextView) findViewById(R.id.nameTxt);
        nameTxt.setText("Billy Bob Joe"); //TODO get patient name from database

        notifyBtn = findViewById(R.id.notify_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }

        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notification goes here
                String msgText = "You have an appointment with Dr. Hilton "
                        + "Feb. 28th 2021 at "
                        + "9:30 AM";


                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
                builder.setContentTitle("Appointments");
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(msgText));
                builder.setContentText(msgText).build();
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());

               /* NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
                builder.setContentTitle("Appointments");
                builder.setContentText("Dr. Hilton; Feb. 28th 2021 @ 9:30 AM");
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                //mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1,builder.build());*/

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