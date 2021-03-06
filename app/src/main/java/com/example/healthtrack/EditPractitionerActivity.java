package com.example.healthtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditPractitionerActivity extends AppCompatActivity {

    private  static final String TAG = "EditPractitionerActivity";
    EditText nameTxt;
    EditText titleTxt;
    EditText addressTxt;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_practitioner);

        // Pre-populated the fields from the previous screen based on pressed button.
        Intent intent = getIntent();
         key = intent.getStringExtra("key_name");
        Log.d(TAG,"The key is "+key + " in the hEditPractitionerActivity");

        //String title = intent.getStringExtra("key_title");
        //String address = intent.getStringExtra("key_address");
        nameTxt = (EditText) findViewById(R.id.nameTxt);
        titleTxt = (EditText) findViewById(R.id.titleTxt);
         addressTxt = (EditText) findViewById(R.id.addressTxt);
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        String currentuserID = myAuth.getCurrentUser().getUid();
        DatabaseReference myref2 = mydatabase.getReference("Patients Practitioner");
        myref2.child(currentuserID).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Practitioner userdoc = snapshot.getValue(Practitioner.class);
               nameTxt.setText(userdoc.getDoctorname());
               titleTxt.setText(userdoc.getTitle());
               addressTxt.setText(userdoc.getAddress());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,"Something went Wrong");

            }
        });




    }




    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickEdit(View view){
        // TODO: if valid data, send practitioner to the database; else prompt user to re-enter data
        String drname = nameTxt.getText().toString();
        String title = titleTxt.getText().toString();
        String address = addressTxt.getText().toString();
        if(drname.isEmpty()){
            nameTxt.setError("Name is required");
            nameTxt.requestFocus();
            return;
        }
        if(title.isEmpty()){
            titleTxt.setError("Title is required");
            titleTxt.requestFocus();
            return;

        }
        if(address.isEmpty()){
            addressTxt.setError("Address is required");
            addressTxt.requestFocus();
            return;
        }
        Practitioner updatedPractitioner = new Practitioner();
        updatedPractitioner.setDoctorname(drname);
        updatedPractitioner.setTitle(title);
        updatedPractitioner.setAddress(address);
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        String currentuserID = myAuth.getCurrentUser().getUid();
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = mydatabase.getReference("Patients Practitioner");
        myref.child(currentuserID).child(key).setValue(updatedPractitioner);


        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }

    public void onClickRemove(View view){
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        String currentuserID = myAuth.getCurrentUser().getUid();
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = mydatabase.getReference("Patients Practitioner");
        Log.d(TAG, "onClickRemove: the vurrent user is: "+currentuserID +" and the key is: "+key );
        myref.child(currentuserID).child(key).removeValue();






        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }

}