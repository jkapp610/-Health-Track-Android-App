package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        fillAppointments();
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNewAppointment(View view){
        Intent intent = new Intent(this, NewAppointmentActivity.class);
        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
    public class Appointment {
        private String practitoner;
        private String date;

        public Appointment() {

        }

        public Appointment(String practitoner, String date) {
            this.practitoner = practitoner;
            this.date = date;
        }

        public List<Appointment> GetItems() {
            List<Appointment> lstItems = new ArrayList<Appointment>();

            lstItems.add(new Appointment("Dr. Hilton","9:30AM Feb. 28th 2021"));
            lstItems.add(new Appointment("Dr. Sparks","11:00AM Aug. 10th 2021"));
            lstItems.add(new Appointment("Dr. Allison","10:00AM May. 4th 2021"));
            lstItems.add(new Appointment("Dr. Varde","2:00PM Sep. 24th 2021"));

            return lstItems;
        }
    }

    // Just a test for now... //TODO get actual data from the database
    private void fillAppointments(){
        Appointment appointment = new Appointment();
        List<Appointment> appt = appointment.GetItems();
        int numAppt = appt.size();

        Button btn0 = (Button) findViewById(R.id.apt0);
        if(numAppt > 0) {
            btn0.setText(appt.get(0).practitoner + "\n" + appt.get(0).date);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.apt1);
        if(numAppt > 1) {
            btn1.setText(appt.get(1).practitoner + "\n" + appt.get(1).date);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.INVISIBLE);

        Button btn2 = (Button) findViewById(R.id.apt2);
        if(numAppt > 2) {
            btn2.setText(appt.get(2).practitoner + "\n" + appt.get(2).date);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.INVISIBLE);

        Button btn3 = (Button) findViewById(R.id.apt3);
        if(numAppt > 3) {
            btn3.setText(appt.get(3).practitoner + "\n" + appt.get(3).date);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.INVISIBLE);

        Button btn4 = (Button) findViewById(R.id.apt4);
        if(numAppt > 4) {
            btn4.setText(appt.get(4).practitoner + "\n" + appt.get(4).date);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.INVISIBLE);
    }
}

