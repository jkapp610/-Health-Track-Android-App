package com.example.healthtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
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

import java.util.ArrayList;
import java.util.List;

public class PractitionersActivity extends AppCompatActivity {
    private  static final String TAG = "PractitionerActivity";
    private  int pract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practitioners);
        Log.d(TAG, "I am here in on create");

        fillPractitioners();
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNew(View view){
        Intent intent = new Intent(this, NewPractitionerActivity.class);
        startActivity(intent);
    }

    /*public void onClickEdit(View view){
        //TODO: get actual data from the database.
        //NOTE: there is probably a much better way of doing this...
        // Pre-populate the fields on the following edit screen.
        Intent intent = new Intent(this, EditPractitionerActivity.class);
        Button btn = (Button) view;
        PractitionersActivity.Practitioner practitioner = new PractitionersActivity.Practitioner();
        List<PractitionersActivity.Practitioner> pract = practitioner.GetItems();
        if( (Button) findViewById(R.id.pract0) == btn) {
            intent.putExtra("key_name", pract.get(0).name);
            intent.putExtra("key_title", pract.get(0).title);
            intent.putExtra("key_address", pract.get(0).address);
        }else if( (Button) findViewById(R.id.pract1) == btn){
            intent.putExtra("key_name",pract.get(1).name);
            intent.putExtra("key_title",pract.get(1).title);
            intent.putExtra("key_address",pract.get(1).address);
        }else if( (Button) findViewById(R.id.pract2) == btn){
            intent.putExtra("key_name",pract.get(2).name);
            intent.putExtra("key_title",pract.get(2).title);
            intent.putExtra("key_address",pract.get(2).address);
        }else if( (Button) findViewById(R.id.pract3) == btn){
            intent.putExtra("key_name",pract.get(3).name);
            intent.putExtra("key_title",pract.get(3).title);
            intent.putExtra("key_address",pract.get(3).address);
        }else if( (Button) findViewById(R.id.pract4) == btn){
            intent.putExtra("key_name",pract.get(4).name);
            intent.putExtra("key_title",pract.get(4).title);
            intent.putExtra("key_address",pract.get(4).address);
        }
        startActivity(intent);
    }*/

    // Just a test for now... //TODO get actual data from the database
    /*public class Practitioner {
        private String name;
        private String title;
        private String address;

        public Practitioner() {

        }

        public Practitioner(String name, String title, String address) {
            this.name = name;
            this.title = title;
            this.address = address;
        }

        public List<PractitionersActivity.Practitioner> GetItems() {
            List<PractitionersActivity.Practitioner> lstItems = new ArrayList<PractitionersActivity.Practitioner>();

            lstItems.add(new PractitionersActivity.Practitioner("Dr. Hilton","Primary Care Physician","1234 Tree Dr. MI"));
            lstItems.add(new PractitionersActivity.Practitioner("Dr. Sparks","Dentist","4567 Flower Ct. MI"));
            lstItems.add(new PractitionersActivity.Practitioner("Dr. Allison","Therapist","CIS/CSC 483"));
            lstItems.add(new PractitionersActivity.Practitioner("Dr. Varde","Neurologist","89 Waterway Blvd. MI"));

            return lstItems;
        }
    }*/

    // Just a test for now... //TODO get actual data from the database
    private void fillPractitioners(){
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        String currentuserID = myAuth.getCurrentUser().getUid();
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = mydatabase.getReference("Patients Practitoner");

        Button btn0 = (Button) findViewById(R.id.pract0);
        Button btn1 = (Button) findViewById(R.id.pract1);
        Button btn2 = (Button) findViewById(R.id.pract2);
        Button btn3 = (Button) findViewById(R.id.pract3);
        Button btn4 = (Button) findViewById(R.id.pract4);


        //btn0.setVisibility(View.INVISIBLE);
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "I am here");
                for (DataSnapshot ds: snapshot.getChildren()){
                     String mykey = ds.getKey();
                        Log.d(TAG,"The key is "+mykey);
                        if(pract ==0){
                            getpractitioner(mykey,btn0);
                        }
                        if (pract ==1){
                            getpractitioner(mykey,btn1);

                        }
                         if (pract ==2){
                            getpractitioner(mykey,btn2);

                        }
                        if (pract ==3){
                            getpractitioner(mykey,btn3);

                        }
                    if (pract ==4){
                        getpractitioner(mykey,btn4);

                    }



                       pract = pract+1;

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*PractitionersActivity.Practitioner practitioner = new PractitionersActivity.Practitioner();
        List<PractitionersActivity.Practitioner> pract = practitioner.GetItems();
        int numPract = pract.size();

        Button btn0 = (Button) findViewById(R.id.pract0);
        if(numPract > 0) {
            btn0.setText(pract.get(0).name + "\n" + pract.get(0).title + "\n" + pract.get(0).address);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.pract1);
        if(numPract > 1) {
            btn1.setText(pract.get(1).name + "\n" + pract.get(1).title + "\n" + pract.get(1).address);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.INVISIBLE);

        Button btn2 = (Button) findViewById(R.id.pract2);
        if(numPract > 2) {
            btn2.setText(pract.get(2).name + "\n" + pract.get(2).title + "\n" + pract.get(2).address);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.INVISIBLE);

        Button btn3 = (Button) findViewById(R.id.pract3);
        if(numPract > 3) {
            btn3.setText(pract.get(3).name + "\n" + pract.get(3).title + "\n" + pract.get(3).address);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.INVISIBLE);

        Button btn4 = (Button) findViewById(R.id.pract4);
        if(numPract > 4) {
            btn4.setText(pract.get(4).name + "\n" + pract.get(4).title + "\n" + pract.get(4).address);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.INVISIBLE);*/
    }
    void getpractitioner(String mykey,Button button ){
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref2 = mydatabase.getReference("Practitioner");
        myref2.child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Practitioner userdoc = snapshot.getValue(Practitioner.class);
                button.setText(userdoc.getDoctorname()+"\n"+userdoc.getTitle()+"\n"+userdoc.getAddress());
                Log.d(TAG,userdoc.getDoctorname()+"\n"+userdoc.getTitle()+"\n"+userdoc.getAddress());
                button.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,"Something went Wrong");

            }
        });

    }

}