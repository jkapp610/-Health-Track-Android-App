package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditAppointmentActivity extends AppCompatActivity {
    private  static final String TAG = "EditAppointmentActivity";
    String date;
    String time;
    String key;
    private String outside;

    String docsName;
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
    private String apdoc;
    private  EditText Datetxt;
    private EditText timetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);


        Intent intent = getIntent();
        key = intent.getStringExtra("key");
         date = intent.getStringExtra("key_date");
        time = intent.getStringExtra("key_time");
        apdoc = intent.getStringExtra("key_name");
        Display_Practitioners_name();
        Datetxt= (EditText) findViewById(R.id.dateTxt);
       timetxt = (EditText) findViewById(R.id.timeTxt);
        Datetxt.setText(date);
       timetxt.setText(time);

        Log.d(TAG,"THIS IS outside"+ outside );

        // Pre-populated the fields from the previous screen based on pressed button.
        /*Intent intent = getIntent();
        String practitioner = intent.getStringExtra("key_practitioner");
        String date = intent.getStringExtra("key_date");
        String time = intent.getStringExtra("key_time");
        Boolean reminder = intent.getBooleanExtra("key_reminder",false);
        for(int i = 0; i < adapter.getCount(); i++) if(practitioner.equals(adapter.getItem(i))) spin.setSelection(i);
        EditText dateTxt = findViewById(R.id.DateTitle);
        dateTxt.setText(date);
        EditText timeTxt = findViewById(R.id.timeTxt);
        timeTxt.setText(time);
        Switch remindSwitch = findViewById(R.id.remindSwitch);
        remindSwitch.setChecked(reminder);*/
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send appointment to the database; else prompt user to re-enter data
        String newdate = Datetxt.getText().toString();
        String newtime = timetxt.getText().toString();
        if(newdate.isEmpty()){
            Datetxt.setError("Date required");
            Datetxt.requestFocus();
            return;
        }
        if(newtime.isEmpty()){
            timetxt.setError("Time is required");
            Datetxt.requestFocus();
            return;
        }
        if (docsName.equals(" ")){
            Display_Practitioners_name();
            return;
        }

        Appointment updatedAppontment = new Appointment();
        updatedAppontment.setDoctor(docsName);
        updatedAppontment.setDate(newdate);
        updatedAppontment.setTime(newtime);
        DatabaseReference myref = mydatabase.getReference("Appointments");
        myref.child(currentuserID).child(key).setValue(updatedAppontment);



        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    public void onClickRemove(View view){
        // TODO: remove appointment from database

        DatabaseReference myref = mydatabase.getReference("Appointments");
        myref.child(currentuserID).child(key).removeValue();

        Intent intent = new Intent(this, AppointmentsActivity.class);
        startActivity(intent);
    }

    private void Display_Practitioners_name(){


        String[] practitioners = { " ", " ", " ", " ", " " };



        Spinner spin = findViewById(R.id.practitionerList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, practitioners);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spin.setAdapter(adapter);

        practitioners[0] = apdoc;


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
                    if (docname.equals(apdoc)){

                    }
                    else {
                        practitioners[i] = docname;
                        i++;
                    }


                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //for(int i = 0; i < adapter.getCount(); i++) if(apdoc.equals(adapter.getItem(i))) spin.setSelection(i);
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
    private void getappoinment(){
        DatabaseReference myref = mydatabase.getReference("Appointments");
        myref.child(currentuserID).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Appointment  AP = snapshot.getValue(Appointment.class);
                DataSnapshot ds = snapshot;

                String mydoc= ds.getKey();
                Log.d(TAG,"THIS IS INSIDE"+mydoc );
                outside = mydoc;
                /*dateTxt= (EditText) findViewById(R.id.dateTxt);
                dateTxt.setText(AP.getDate());
                timeTXT = (EditText) findViewById(R.id.timeTxt);
                timeTXT.setText(AP.getTime());*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



}