package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointmentsActivity extends AppCompatActivity {
    private  static final String TAG = "AppointmentActivity";
    private  int pract =0;
    private String mykey0;
    private String mykey1;
    private String mykey2;
    private String mykey3;
    private String mykey4;
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();

    DatabaseReference myref = mydatabase.getReference("Appointments");
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

        String text = btn.getText().toString();

        String[] array = text.split("\n");
        String Doctorname =array[0];
        String Datename = array[1];
        String time = array[2];
        Log.d(TAG,Doctorname +" "+Datename+" "+time);
        //AppointmentsActivity.Appointment appointment = new AppointmentsActivity.Appointment();
        //List<AppointmentsActivity.Appointment> appt = appointment.GetItems();
        if( (Button) findViewById(R.id.appt0) == btn) {

            intent.putExtra("key", mykey0);
            intent.putExtra("key_name", Doctorname);
            intent.putExtra("key_date", Datename);
            intent.putExtra("key_time", time);
            //intent.putExtra("key_reminder", appt.get(0).reminder);
        }else if( (Button) findViewById(R.id.appt1) == btn){
            intent.putExtra("key",mykey1);
            intent.putExtra("key_name", Doctorname);
            intent.putExtra("key_date", Datename);
            intent.putExtra("key_time", time);
            //intent.putExtra("key", mykey2);
           // intent.putExtra("key_time", appt.get(1).time);
           // intent.putExtra("key_reminder", appt.get(1).reminder);
        }else if( (Button) findViewById(R.id.appt2) == btn){
            intent.putExtra("key", mykey2);
            intent.putExtra("key_name", Doctorname);
            intent.putExtra("key_date", Datename);
            intent.putExtra("key_time", time);
            //intent.putExtra("key_date", appt.get(2).date);
            //intent.putExtra("key_time", appt.get(2).time);
            //intent.putExtra("key_reminder", appt.get(2).reminder);
        }else if( (Button) findViewById(R.id.appt3) == btn){
            intent.putExtra("key", mykey3);
            intent.putExtra("key_name", Doctorname);
            intent.putExtra("key_date", Datename);
            intent.putExtra("key_time", time);
            //intent.putExtra("key_date", appt.get(3).date);
            //intent.putExtra("key_time", appt.get(3).time);
            //intent.putExtra("key_reminder", appt.get(3).reminder);
        }else if( (Button) findViewById(R.id.appt4) == btn){
            intent.putExtra("key",mykey4);
            intent.putExtra("key_name", Doctorname);
            intent.putExtra("key_date", Datename);
            intent.putExtra("key_time", time);
            //intent.putExtra("key_date", appt.get(4).date);
            //intent.putExtra("key_time", appt.get(4).time);
            //intent.putExtra("key_reminder", appt.get(4).reminder);
        }
        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
   /* public class Appointment {
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
    }*/

    // Just a test for now... //TODO get actual data from the database
    private void fillAppointments(){


        Button btn0 = (Button) findViewById(R.id.appt0);
        Button btn1 = (Button) findViewById(R.id.appt1);
        Button btn2 = (Button) findViewById(R.id.appt2);
        Button btn3 = (Button) findViewById(R.id.appt3);
        Button btn4 = (Button) findViewById(R.id.appt4);
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                   String mykey = ds.getKey();
                   if(pract==0){
                       mykey0=mykey;
                       getAppointment(mykey,btn0);
                   }
                    if(pract==1){
                        mykey1=mykey;
                        getAppointment(mykey,btn1);
                    }
                    if(pract==2){
                        mykey2=mykey;
                        getAppointment(mykey,btn2);
                    }
                    if(pract==3){
                        mykey3=mykey;
                        getAppointment(mykey,btn3);
                    }
                    if(pract==4){
                        mykey4=mykey;
                        getAppointment(mykey,btn4);
                    }
                    pract = pract+1;

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        /*Appointment appointment = new Appointment();
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
        }else btn4.setVisibility(View.INVISIBLE);*/
    }
    public void getAppointment(String mykey, Button button) {
        myref.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Appointment AP = snapshot.getValue(Appointment.class);

                button.setText(AP.getDoctor()+"\n" +AP.getDate()+"\n"+AP.getTime());
                button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}

