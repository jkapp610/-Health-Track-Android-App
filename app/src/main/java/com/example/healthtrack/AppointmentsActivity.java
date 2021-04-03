package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void onClickNew(View view){
        Intent intent = new Intent(this, NewAppointmentActivity.class);
        startActivity(intent);
    }

    public void onClickEdit(View view){
        //TODO: get actual data from the database.
        //NOTE: there is probably a much better way of doing this...
        // Pre-populate the fields on the following edit screen.
        Intent intent = new Intent(this, EditAppointmentActivity.class);
        Button btn = (Button) view;
        AppointmentsActivity.Appointment appointment = new AppointmentsActivity.Appointment();
        List<AppointmentsActivity.Appointment> appt = appointment.GetItems();
        if( (Button) findViewById(R.id.appt0) == btn) {
            intent.putExtra("key_practitioner", appt.get(0).practitioner);
            intent.putExtra("key_date", appt.get(0).date);
            intent.putExtra("key_time", appt.get(0).time);
            intent.putExtra("key_reminder", appt.get(0).reminder);
        }else if( (Button) findViewById(R.id.appt1) == btn){
            intent.putExtra("key_practitioner", appt.get(1).practitioner);
            intent.putExtra("key_date", appt.get(1).date);
            intent.putExtra("key_time", appt.get(1).time);
            intent.putExtra("key_reminder", appt.get(1).reminder);
        }else if( (Button) findViewById(R.id.appt2) == btn){
            intent.putExtra("key_practitioner", appt.get(2).practitioner);
            intent.putExtra("key_date", appt.get(2).date);
            intent.putExtra("key_time", appt.get(2).time);
            intent.putExtra("key_reminder", appt.get(2).reminder);
        }else if( (Button) findViewById(R.id.appt3) == btn){
            intent.putExtra("key_practitioner", appt.get(3).practitioner);
            intent.putExtra("key_date", appt.get(3).date);
            intent.putExtra("key_time", appt.get(3).time);
            intent.putExtra("key_reminder", appt.get(3).reminder);
        }else if( (Button) findViewById(R.id.appt4) == btn){
            intent.putExtra("key_practitioner", appt.get(4).practitioner);
            intent.putExtra("key_date", appt.get(4).date);
            intent.putExtra("key_time", appt.get(4).time);
            intent.putExtra("key_reminder", appt.get(4).reminder);
        }
        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
    public class Appointment {
        private String practitioner;
        private String date;
        private String time;
        private Boolean reminder;

        public Appointment() {

        }

        public Appointment(String practitioner, String date,String time,Boolean reminder) {
            this.practitioner = practitioner;
            this.date = date;
            this.time = time;
            this.reminder = reminder;
        }

        public List<Appointment> GetItems() {
            List<Appointment> lstItems = new ArrayList<Appointment>();

            lstItems.add(new Appointment("Dr. Hilton","Feb. 28th 2021","9:30AM ",true));
            lstItems.add(new Appointment("Dr. Sparks","Aug. 10th 2021","11:00AM ",false));
            lstItems.add(new Appointment("Dr. Allison","May. 4th 2021","10:00AM ",true));
            lstItems.add(new Appointment("Dr. Varde","Sep. 24th 2021","2:00PM",false));

            return lstItems;
        }
    }

    // Just a test for now... //TODO get actual data from the database
    private void fillAppointments(){
        Appointment appointment = new Appointment();
        List<Appointment> appt = appointment.GetItems();
        int numAppt = appt.size();

        Button btn0 = (Button) findViewById(R.id.appt0);
        if(numAppt > 0) {
            btn0.setText(appt.get(0).practitioner + "\n" + appt.get(0).date + "\n" + appt.get(0).time);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.appt1);
        if(numAppt > 1) {
            btn1.setText(appt.get(1).practitioner + "\n" + appt.get(1).date + "\n" + appt.get(1).time);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.INVISIBLE);

        Button btn2 = (Button) findViewById(R.id.appt2);
        if(numAppt > 2) {
            btn2.setText(appt.get(2).practitioner + "\n" + appt.get(2).date + "\n" + appt.get(2).time);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.INVISIBLE);

        Button btn3 = (Button) findViewById(R.id.appt3);
        if(numAppt > 3) {
            btn3.setText(appt.get(3).practitioner + "\n" + appt.get(3).date + "\n" + appt.get(3).time);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.INVISIBLE);

        Button btn4 = (Button) findViewById(R.id.appt4);
        if(numAppt > 4) {
            btn4.setText(appt.get(4).practitioner + "\n" + appt.get(4).date + "\n" + appt.get(4).time);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.INVISIBLE);
    }
}

