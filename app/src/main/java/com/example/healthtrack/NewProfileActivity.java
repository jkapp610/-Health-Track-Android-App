package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewProfileActivity extends AppCompatActivity {

    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
    String mykey;
    String type;
    boolean edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
        Intent intent = getIntent();
        mykey = intent.getStringExtra("key_data");
        type = intent.getStringExtra("key_type");
        edit =intent.getBooleanExtra("key_edit",false);
        if (edit == false){mykey ="null";}


        //edit= false;
        //edit = intent.getBooleanExtra("key_edit",false);
        //type = "Prescription";
        TextView title = findViewById(R.id.titleTxt);
        title.setText("Add " + type);

        // Display the correct fields for the previously selected type
        TextView textBox;

        switch (type) {
            case "Condition":
                textBox = findViewById(R.id.editTextTime);
                textBox.setVisibility(View.GONE);
                textBox = findViewById(R.id.EditTextDose);
                textBox.setHint(getString(R.string.OpHint_11));
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.editTextDate);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.remindSwitch);
                textBox.setVisibility(View.GONE);
                textBox = findViewById(R.id.EdittextType);
                textBox.setVisibility(View.GONE);
                if(edit){
                    title.setText("Edit " + type);
                    // String name = intent.getStringExtra("key_name");
                    //TextView txt = findViewById(R.id.editTxt);
                    //txt.setText(name);
                    EditText name = (EditText) findViewById(R.id.editTxt);
                    EditText Date =(EditText) findViewById(R.id.editTextDate);
                    EditText Dose =(EditText) findViewById(R.id.EditTextDose);
                    EditText time = (EditText) findViewById(R.id. editTextTime);
                    EditText txttype =(EditText) findViewById(R.id.EdittextType);
                    displayData(mykey,type,name,Date,Dose,txttype,time);


                    Button btn = findViewById(R.id.removeBtn);
                    btn.setVisibility(View.VISIBLE);
                    btn = findViewById(R.id.createBtn);
                    btn.setText("Edit");
                }
                break;
            case "Prescription":
            case "Vaccine":
                textBox = findViewById(R.id.editTextTime);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.EditTextDose);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.editTextDate);
                textBox.setVisibility(View.VISIBLE);
                textBox = findViewById(R.id.EdittextType);
                textBox.setVisibility(View.VISIBLE);
                //textBox = findViewById(R.id.remindSwitch);
                // textBox.setVisibility(View.VISIBLE);
                if(edit){
                    title.setText("Edit " + type);
                    // String name = intent.getStringExtra("key_name");
                    EditText name = (EditText) findViewById(R.id.editTxt);
                    EditText Date =(EditText) findViewById(R.id.editTextDate);
                    EditText time =(EditText) findViewById(R.id.editTextTime);
                    EditText Dose =(EditText) findViewById(R.id.EditTextDose);
                    EditText txttype =(EditText) findViewById(R.id.EdittextType);
                    displayData(mykey,type,name,Date,Dose,txttype,time);


                    //txt.setText(name);
                    //String time = intent.getStringExtra("key_time");
                    // EditText timeTxt = findViewById(R.id.editTextTime);
                    //timeTxt.setText(time);
                    // Boolean reminder = intent.getBooleanExtra("key_reminder",false);
                    SwitchCompat remindSwitch = findViewById(R.id.remindSwitch);
                    remindSwitch.setChecked(false);
                    Button btn = findViewById(R.id.removeBtn);
                    btn.setVisibility(View.VISIBLE);
                    btn = findViewById(R.id.createBtn);
                    btn.setText("Edit");
                }
                break;
            //TextView txt = findViewById(R.id.editTxt);
            //String date = intent.getStringExtra("key_date");
            //EditText dateTxt = findViewById(R.id.editTextDate);
            // dateTxt.setText(date);
            //Switch remindSwitch = findViewById(R.id.remindSwitch);
            //remindSwitch.setChecked(reminder);
        }

    }



    public void displayData(String mykey , String type, EditText name,EditText Date,EditText dose, EditText txttype,EditText time ){
        if(type.equals("Condition")){
            DatabaseReference myref= mydatabase.getReference("Conditions");
            myref.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Conditions con = snapshot.getValue(Conditions.class);
                    name.setText(con.getCondition());
                    Date.setText(con.getDatediagnosed());
                    dose.setText(con.getTreatment());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(type.equals("Prescription")){
            DatabaseReference myref= mydatabase.getReference("Prescriptions");

            myref.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Prescriptions per  = snapshot.getValue(Prescriptions.class);
                    name.setText(per.getPrescription());
                    Date.setText(per.getDatefrequency());
                    time.setText(per.getTimefrequency());
                    dose.setText(per.getDose());
                    txttype.setText(per.getType());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else if(type.equals("Vaccine")){
            DatabaseReference myref= mydatabase.getReference("Vaccines");
            myref.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Vaccines vac  = snapshot.getValue(Vaccines.class);
                    name.setText(vac.getVaccine());
                    Date.setText(vac.getDatefrequency());
                    time.setText(vac.getTimerequency());
                    dose.setText(vac.getDose());
                    txttype.setText(vac.getType());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



    }
    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view){

        EditText nametxt = (EditText) findViewById(R.id.editTxt);
        EditText Datetxt =(EditText) findViewById(R.id.editTextDate);
        EditText Dosetxt =(EditText) findViewById(R.id.EditTextDose);
        EditText timetxt = (EditText) findViewById(R.id.editTextTime);
        EditText txttype =(EditText) findViewById(R.id.EdittextType);
        String name = nametxt.getText().toString();
        String Date = Datetxt.getText().toString();
        String Dose = Dosetxt.getText().toString();
        String time = timetxt.getText().toString();
        String typedata =txttype.getText().toString();

        if (name.isEmpty()){
            nametxt.setError("name is required");
            nametxt.requestFocus();
            return;

        }
        if(Dose.isEmpty()) {
            Dosetxt.setError("required filed");
            Dosetxt.requestFocus();
            return;

        }
        if(Date.isEmpty()){
            Datetxt.setError("required filed");
            Datetxt.requestFocus();
            return;

        }
        String frequency = Date + " " +time;





        if(type.equals("Condition")){
            Conditions newcon = new Conditions();
            newcon.setCondition(name);
            newcon.setDatediagnosed(Date);
            newcon.setTreatment(Dose);
            DatabaseReference myref= mydatabase.getReference("Conditions");
            if(edit){
                myref.child(currentuserID).child(mykey).setValue(newcon);
            }
            else{
                myref.child(currentuserID);
                String newkey = myref.push().getKey();
                myref.child(currentuserID).child(newkey).setValue(newcon);
            }


        }
        else if(type.equals("Prescription")){
            Prescriptions newpre = new Prescriptions();
            newpre.setPrescription(name);
            newpre.setDose(Dose);
            newpre.setDatefrequency(Date);
            newpre.setTimefrequency(time);
            newpre.setType(typedata);
            DatabaseReference myref= mydatabase.getReference("Prescriptions");
            if(edit){
                myref.child(currentuserID).child(mykey).setValue(newpre);
            }
            else{
                myref.child(currentuserID);
                String newkey = myref.push().getKey();
                myref.child(currentuserID).child(newkey).setValue(newpre);
            }


        }
        else if(type.equals("Vaccine")) {
            Vaccines newvac = new Vaccines();
            newvac.setVaccine(name);
            newvac.setDose(Dose);
            newvac.setDatefrequency(Date);
            newvac.setTimerequency(time);
            newvac.setType(typedata);
            DatabaseReference myref = mydatabase.getReference("Vaccines");
            if(edit){
                myref.child(currentuserID).child(mykey).setValue(newvac);
            }
            else{
                myref.child(currentuserID);
                String newkey = myref.push().getKey();
                myref.child(currentuserID).child(newkey).setValue(newvac);
            }

        }

        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);



    }
















    public void onClickRemove(View view){
        // TODO: remove element from database

        if(type.equals("Condition")){
            DatabaseReference myref= mydatabase.getReference("Conditions");
            myref.child(currentuserID).child(mykey).removeValue();

        }
        else if(type.equals("Prescription")){
            DatabaseReference myref= mydatabase.getReference("Prescriptions");
            myref.child(currentuserID).child(mykey).removeValue();

        }
        else if(type.equals("Prescription")){}
        DatabaseReference myref = mydatabase.getReference("Vaccines");
        myref.child(currentuserID).child(mykey).removeValue();


        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


}