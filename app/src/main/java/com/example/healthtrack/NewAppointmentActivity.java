package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewAppointmentActivity extends AppCompatActivity {

    private String docsName;
    private EditText Datetxt;
    private EditText timetxt;
    private  String remindertxt;
    private boolean doesexist;
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        Datetxt = (EditText)  findViewById(R.id.dateTxt);
        timetxt =(EditText) findViewById(R.id.timeTxt);
        Display_Practitioners_name();

        //TODO actually get list of practitioners from the database!


       /* Spinner spin = findViewById(R.id.practitionerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, practitioners);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                docsName = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send appointment to the database; else prompt user to re-enter data
            doesexist= false;
          String date= Datetxt.getText().toString();
          String time = timetxt.getText().toString();

        if(date.isEmpty()){
              Datetxt.setError("Date required");
              Datetxt.requestFocus();
              return;
          }
          if(time.isEmpty()){
              timetxt.setError("Time is required");
              Datetxt.requestFocus();
              return;
          }
          if(docsName.equals(" ")||docsName.equals("SELECT DOCTOR")){
              Display_Practitioners_name();
              return;
          }
        Switch Reminder = (Switch) findViewById(R.id.remindSwitch);
          Reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  if(isChecked){
                     remindertxt = "true";
                  }
                  remindertxt = "false";

              }

          });
          Appointment newAppointment = new Appointment();
          newAppointment.setDoctor(docsName);
          newAppointment.setDate(date);
          newAppointment.setTime(time);
          //newAppointment.setReminder(remindertxt);
          DatabaseReference myref = mydatabase.getReference("Appointments");
         myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {

                     for(DataSnapshot interds: snapshot.getChildren()){
                         Appointment AP =interds.getValue(Appointment.class);
                         if (AP.getDoctor().equals(newAppointment.getDoctor())){
                             if (AP.getDate().equals(newAppointment.getDate())){
                                 if (AP.getTime().equals(newAppointment.getTime())){
                                     timetxt.setError("You have already stored this appointment in the app ");
                                     timetxt.requestFocus();
                                     doesexist = true;

                                 }

                             }

                         }
                     }
                 if(doesexist){

                 }
                 else{
                     String mykey = myref.push().getKey();
                     myref.child(currentuserID).child(mykey).setValue(newAppointment);

                 }

             }



             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });





        //Intent intent = new Intent(this, AppointmentsActivity.class);
        //startActivity(intent);
    }
    private void Display_Practitioners_name(){
        String[] practitioners = { "SELECT DOCTOR", " ", " ", " ", " "," " };


        Spinner spin = findViewById(R.id.practitionerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, practitioners);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(adapter);


        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = mydatabase.getReference("Patients Practitioner");
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        String currentuserID = myAuth.getCurrentUser().getUid();
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i =1;
                for(DataSnapshot ds:snapshot.getChildren()){
                    Practitioner docs = ds.getValue(Practitioner.class);
                    String docname = docs.getDoctorname();
                    practitioners[i] = docname;
                    i++;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                docsName = parent.getItemAtPosition(position).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}