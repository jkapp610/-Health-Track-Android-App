package com.example.healthtrack;

import android.content.Intent;
import android.location.Address;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewPractitionerActivity extends AppCompatActivity {

    private  static final String TAG = "NewPractitionerActivity";
    private EditText EditTextdoctorName;
    private EditText EditTextitle;
    private EditText EditTextAddress;
    private Button Addbutton;
    private  boolean DoesExist;
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_practitioner);
        EditTextdoctorName = (EditText) findViewById(R.id.nameTxt);
        EditTextitle = (EditText) findViewById(R.id.titleTxt);
        EditTextAddress = (EditText) findViewById(R.id.addressTxt);
        Addbutton = (Button) findViewById(R.id.createBtn);
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                practitionersignup();

            }

            private void practitionersignup() {
                String doctorname =EditTextdoctorName.getText().toString();
                String title = EditTextitle.getText().toString();
                String Address = EditTextAddress.getText().toString();
                if(doctorname.isEmpty()){
                    EditTextdoctorName.setError("Name required");
                    EditTextdoctorName.requestFocus();
                    return;
                }
                if(title.isEmpty()) {
                    EditTextitle.setError("Title Required");
                    EditTextitle.requestFocus();
                    return;

                }
                if(Address.isEmpty()){
                    EditTextAddress.setError("Address Required");
                    EditTextAddress.requestFocus();
                    return;
                }
                //Creating practitioner object
                Practitioner newdoc = new Practitioner();
                newdoc.setDoctorname(doctorname);
                newdoc.setTitle(title);
                newdoc.setAddress(Address);

                //FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
                DatabaseReference myref = mydatabase.getReference("Patients Practitioner");
                //FirebaseAuth myAuth = FirebaseAuth.getInstance();
               //String currentuserID = myAuth.getCurrentUser().getUid();
                myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(checkifdoctorExists(doctorname,title,Address,snapshot)){
                            Addbutton.setError("Practitioner already in databaes");

                            Intent intent = new Intent(NewPractitionerActivity.this, PractitionersActivity.class);
                            startActivity(intent);

                        }
                        else{
                           String mykey = myref.push().getKey();
                           //DatabaseReference myref2 = mydatabase.getReference("Patient Practitioner");
                           myref.child(currentuserID).child(mykey).setValue(newdoc);
                            Intent intent = new Intent(NewPractitionerActivity.this, PractitionersActivity.class);
                            startActivity(intent);
                        }



                    }















                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });



    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean checkifdoctorExists(String doctorname, String title, String Address, DataSnapshot snapshot){
        Log.d(TAG,"Checking method called");
       /* if(!(snapshot.exists())){
            Log.d(TAG,"Checking to see if database table exist");
            DoesExist = false;
            return false;

        }*/
        for (DataSnapshot ds :snapshot.getChildren()){
            Practitioner newdoc = ds.getValue(Practitioner.class);
            if(newdoc.getDoctorname().equals(doctorname)){
                if (newdoc.getTitle().equals(title)){
                    if (newdoc.getAddress().equals(Address)){
                        String mykey = ds.getKey().toString();
                        //Log.d(TAG,"The key is "+mykey);
                        DoesExist =true;
                        return true;

                    }
                }

            }
        }
        return false;
    }

    public void onClickCreate(View view){
        // TODO: if valid data, send practitioner to the database; else prompt user to re-enter data
        //TODO: figure out how many buttons are currently visible on the previous screen & pick an
        // available button to store this information.




        Intent intent = new Intent(this, PractitionersActivity.class);
        startActivity(intent);
    }


}