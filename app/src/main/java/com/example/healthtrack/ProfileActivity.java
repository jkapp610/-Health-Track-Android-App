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

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    String currentuserID = myAuth.getCurrentUser().getUid();
    private  static final String TAG = "ProfileActivity";
    private  int numcond;
    private  int numvac;
    private  int numpres;

    FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
    private String Condmykey0;
    private String Condmykey1;
    private String Condmykey2;
    private String Condmykey3;
    private String Condmykey4;
    private String presmykey0;
    private String presmykey1;
    private String presmykey2;
    private String presmykey3;
    private String presmykey4;
    private String vacmykey0;
    private String vacmykey1;
    private String vacmykey2;
    private String vacmykey3;
    private String vacmykey4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fillConditions();
        fillPrescriptions();
        fillVaccines();
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickBtn(View view){
        // Determine which type of medical record button was pressed, so we can set up the next
        // screen with the proper input fields (and later send to the correct spot in the database).
        // Also, determine whether we're adding a new element or editing an existing one.
        // NOTE: there is probably a better way of doing this, but this will have to work for now...
        Button btn = (Button) view;
        Intent intent = new Intent(this, NewProfileActivity.class);







        if((Button) findViewById(R.id.cond0) == btn){

            if (!btn.getText().toString().isEmpty()) {
                intent.putExtra("key_edit", true);
                //Condmykey0= "null";
                intent.putExtra("key_data",Condmykey0);

                Log.d(TAG, "clicked on ADD" );

            }
            intent.putExtra("key_type","Condition");




        }
       else if((Button) findViewById(R.id.cond1) == btn){


            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                Log.d(TAG, "clicked on ADD" );
                //Condmykey1 = "null";
                intent.putExtra("key_data",Condmykey1);

            }
            intent.putExtra("key_type","Condition");



        }
       else if((Button) findViewById(R.id.cond2) == btn){

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",Condmykey2);


            }
            intent.putExtra("key_type","Condition");


        }
        else if((Button) findViewById(R.id.cond3) == btn){

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);

                intent.putExtra("key_data",Condmykey3);
            }
            intent.putExtra("key_type","Condition");

        }
        else if((Button) findViewById(R.id.cond4) == btn){

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",Condmykey4);
            }
            intent.putExtra("key_type","Condition");

        }
        else if((Button) findViewById(R.id.pres0) == btn) {

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",presmykey0);
            }
            intent.putExtra("key_type", "Prescription");


        }
        else if((Button) findViewById(R.id.pres1) == btn) {

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",presmykey1);
            }
            intent.putExtra("key_type", "Prescription");

        }
        else if((Button) findViewById(R.id.pres2) == btn) {


            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);

                intent.putExtra("key_data",presmykey2);

            }
            intent.putExtra("key_type", "Prescription");


        }
        else if((Button) findViewById(R.id.pres3) == btn) {

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                Log.d(TAG, "clicked on ADD" );
                intent.putExtra("key_data",presmykey3);

            }
            intent.putExtra("key_type", "Prescription");


        }
        else if((Button) findViewById(R.id.pres4) == btn) {
            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                Log.d(TAG, "clicked on ADD" );
                intent.putExtra("key_data",presmykey4);

            }
            intent.putExtra("key_type", "Prescription");
        }
        else if((Button) findViewById(R.id.vacc0) == btn) {

            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",vacmykey0);

            }
            intent.putExtra("key_type", "Vaccine");

        }
        else if((Button) findViewById(R.id.vacc1) == btn) {
            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",vacmykey1);

            }
            intent.putExtra("key_type", "Vaccine");
        }
        else if((Button) findViewById(R.id.vacc2) == btn) {
            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",vacmykey2);

            }
            intent.putExtra("key_type", "Vaccine");
        }
        else if((Button) findViewById(R.id.vacc3) == btn) {
            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",vacmykey3);

            }
            intent.putExtra("key_type", "Vaccine");
        }
        else if((Button) findViewById(R.id.vacc4) == btn) {
            if (!(btn.getText().toString().isEmpty())) {
                intent.putExtra("key_edit", true);
                intent.putExtra("key_data",vacmykey4);

            }
            intent.putExtra("key_type", "Vaccine");
        }


        startActivity(intent);



        //ProfileActivity.Condition condition = new ProfileActivity.Condition();
        /*List<ProfileActivity.Condition> cond = condition.GetItems();
        int numCond = cond.size();
        ProfileActivity.Prescription prescription = new ProfileActivity.Prescription();
        List<ProfileActivity.Prescription> pres = prescription.GetItems();
        int numPres = pres.size();
        ProfileActivity.Vaccine vaccine = new ProfileActivity.Vaccine();
        List<ProfileActivity.Vaccine> vacc = vaccine.GetItems();
        int numVacc = vacc.size();

        if((Button) findViewById(R.id.cond0) == btn){
            intent.putExtra("key_type","Condition");
            if(numCond > 0) {
                if (!cond.get(0).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", cond.get(0).name);
                }
            }
        }else if((Button) findViewById(R.id.cond1) == btn){
            intent.putExtra("key_type", "Condition");
            if(numCond > 1) {
                if (!cond.get(1).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", cond.get(1).name);
                }
            }
        }else if((Button) findViewById(R.id.cond2) == btn){
            intent.putExtra("key_type", "Condition");
            if(numCond > 2) {
                if (!cond.get(2).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", cond.get(2).name);
                }
            }
        }else if((Button) findViewById(R.id.cond3) == btn){
            intent.putExtra("key_type", "Condition");
            if(numCond > 3) {
                if (!cond.get(3).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", cond.get(3).);
                }
            }
        }else if((Button) findViewById(R.id.cond4) == btn){
            intent.putExtra("key_type", "Condition");
            if(numCond > 4) {
                if (!cond.get(4).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", cond.get(4).name);
                }
            }
        }else if((Button) findViewById(R.id.pres0) == btn){
            intent.putExtra("key_type","Prescription");
            if(numPres > 0) {
                if (!pres.get(0).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", pres.get(0).name);
                    intent.putExtra("key_time", pres.get(0).time);
                    intent.putExtra("key_reminder", pres.get(0).reminder);
                }
            }
        }else if((Button) findViewById(R.id.pres1) == btn){
            intent.putExtra("key_type","Prescription");
            if(numPres > 1) {
                if (!pres.get(1).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", pres.get(1).name);
                    intent.putExtra("key_time", pres.get(1).time);
                    intent.putExtra("key_reminder", pres.get(1).reminder);
                }
            }
        }else if((Button) findViewById(R.id.pres2) == btn){
            intent.putExtra("key_type","Prescription");
            if(numPres > 2) {
                if (!pres.get(2).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", pres.get(2).name);
                    intent.putExtra("key_time", pres.get(2).time);
                    intent.putExtra("key_reminder", pres.get(2).reminder);
                }
            }
        }else if((Button) findViewById(R.id.pres3) == btn){
            intent.putExtra("key_type","Prescription");
            if(numPres > 3) {
                if (!pres.get(3).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", pres.get(3).name);
                    intent.putExtra("key_time", pres.get(3).time);
                    intent.putExtra("key_reminder", pres.get(3).reminder);
                }
            }
        }else if((Button) findViewById(R.id.pres4) == btn){
            intent.putExtra("key_type","Prescription");
            if(numPres > 4) {
                if (!pres.get(4).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", pres.get(4).name);
                    intent.putExtra("key_time", pres.get(4).time);
                    intent.putExtra("key_reminder", pres.get(4).reminder);
                }
            }
        }else if((Button) findViewById(R.id.vacc0) == btn){
            intent.putExtra("key_type","Vaccine");
            if(numVacc > 0) {
                if (!vacc.get(0).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", vacc.get(0).name);
                    intent.putExtra("key_date", vacc.get(0).date);
                    intent.putExtra("key_reminder", vacc.get(0).reminder);
                }
            }
        }else if((Button) findViewById(R.id.vacc1) == btn){
            intent.putExtra("key_type","Vaccine");
            if(numVacc > 1) {
                if (!vacc.get(1).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", vacc.get(1).name);
                    intent.putExtra("key_date", vacc.get(1).date);
                    intent.putExtra("key_reminder", vacc.get(1).reminder);
                }
            }
        }else if((Button) findViewById(R.id.vacc2) == btn){
            intent.putExtra("key_type","Vaccine");
            if(numVacc > 2) {
                if (!vacc.get(2).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", vacc.get(2).name);
                    intent.putExtra("key_date", vacc.get(2).date);
                    intent.putExtra("key_reminder", vacc.get(2).reminder);
                }
            }
        }else if((Button) findViewById(R.id.vacc3) == btn){
            intent.putExtra("key_type","Vaccine");
            if(numVacc > 3) {
                if (!vacc.get(3).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", vacc.get(3).name);
                    intent.putExtra("key_date", vacc.get(3).date);
                    intent.putExtra("key_reminder", vacc.get(3).reminder);
                }
            }
        }else if((Button) findViewById(R.id.vacc4) == btn){
            intent.putExtra("key_type","Vaccine");
            if(numVacc > 4) {
                if (!vacc.get(4).name.equals(null)) {
                    intent.putExtra("key_edit", true);
                    intent.putExtra("key_name", vacc.get(4).name);
                    intent.putExtra("key_date", vacc.get(4).date);
                    intent.putExtra("key_reminder", vacc.get(4).reminder);
                }
            }
        }
        startActivity(intent);*/
    }

    // Just a test for now... //TODO get actual data from the database
    /*public class Condition {
        private String name;

        public Condition() {

        }

        public Condition(String name) {
            this.name = name;
        }

        public List<ProfileActivity.Condition> GetItems() {
          List<ProfileActivity.Condition> lstItems = new ArrayList<ProfileActivity.Condition>();

            lstItems.add(new ProfileActivity.Condition("Asthma"));
            lstItems.add(new ProfileActivity.Condition("High Blood Pressure"));
            lstItems.add(new ProfileActivity.Condition("Migraines"));
            lstItems.add(new ProfileActivity.Condition("Explosive Diarrhea"));
//            lstItems.add(new ProfileActivity.Condition("Human Allergies"));

            return lstItems;
        }
    }*/

    // Just a test for now... //TODO get actual data from the database
    private void fillConditions(){
        numcond =0;

       // Log.d(TAG ,"IAM HERE IN SIDE FILL");

        Button btn0 = (Button) findViewById(R.id.cond0);
        Button btn1 = (Button) findViewById(R.id.cond1);
        Button btn2 = (Button) findViewById(R.id.cond2);
        Button btn3 = (Button) findViewById(R.id.cond3);
        Button btn4 = (Button) findViewById(R.id.cond4);


        DatabaseReference myref = mydatabase.getReference("Conditions");
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot ds: snapshot.getChildren()){
                   String mykey = ds.getKey();
                   Log.d(TAG,"CONDMYKEY IS "+mykey);
                   if( numcond == 0){
                       Condmykey0 = mykey;
                       getcondition(mykey,btn0);
                       btn1.setVisibility(View.VISIBLE);


                   }
                   if( numcond == 1){
                       Condmykey1 = mykey;
                       getcondition(mykey,btn1);
                       btn2.setVisibility(View.VISIBLE);


                   }
                   if( numcond == 2){
                      Condmykey2 = mykey;
                       getcondition(mykey,btn2);
                       btn3.setVisibility(View.VISIBLE);


                   }
                   if( numcond == 3){
                      Condmykey3 = mykey;
                       getcondition(mykey,btn3);
                       btn4.setVisibility(View.VISIBLE);
                   }
                   if( numcond == 4){
                      Condmykey4 = mykey;
                       getcondition(mykey,btn4);

                   }
                   numcond =numcond+1;


               }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //ProfileActivity.Condition condition = new ProfileActivity.Condition();
        /*Conditions conditions = new Conditions();
        List<Conditions>  cond =new ArrayList<Conditions>();
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
       String currentuserID = myAuth.getCurrentUser().getUid();
        FirebaseDatabase mydatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref = mydatabase.getReference("Conditions");
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    for (DataSnapshot in : ds.getChildren()){
                        Conditions conditions = in.getValue(Conditions.class);
                        cond.add(conditions);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //List<ProfileActivity.Condition> cond = condition.GetItems();
        int numCond = cond.size();

        Button btn0 = (Button) findViewById(R.id.cond0);
        if(numCond > 0) {
            btn0.setText(cond.get(0).getCondition());
            btn0.setVisibility(View.VISIBLE);
            Button btn1 = (Button) findViewById(R.id.cond1);
            btn1.setVisibility(View.VISIBLE);
        }

        Button btn1 = (Button) findViewById(R.id.cond1);
        if(numCond > 1) {
            btn1.setText(cond.get(1).getCondition());
            btn1.setVisibility(View.VISIBLE);
            Button btn2 = (Button) findViewById(R.id.cond2);
            btn2.setVisibility(View.VISIBLE);
        }

        Button btn2 = (Button) findViewById(R.id.cond2);
        if(numCond > 2) {
            btn2.setText(cond.get(2).getCondition());
            btn2.setVisibility(View.VISIBLE);
            Button btn3 = (Button) findViewById(R.id.cond3);
            btn3.setVisibility(View.VISIBLE);
        }

        Button btn3 = (Button) findViewById(R.id.cond3);
        if(numCond > 3) {
            btn3.setText(cond.get(3).getCondition());
            btn3.setVisibility(View.VISIBLE);
            Button btn4 = (Button) findViewById(R.id.cond4);
            btn4.setVisibility(View.VISIBLE);
        }

        Button btn4 = (Button) findViewById(R.id.cond4);
        if(numCond > 4) {
            btn4.setText(cond.get(4).getCondition());
            btn4.setVisibility(View.VISIBLE);
        }*/
    }

    // Just a test for now... //TODO get actual data from the database
    /*public class Prescription {
        private String name;
        private String time;
        private Boolean reminder;

        public Prescription() {

        }

        public Prescription(String name, String time, Boolean reminder) {
            this.name = name;
            this.time = time;
            this.reminder = reminder;
        }

        public List<ProfileActivity.Prescription> GetItems() {
            List<ProfileActivity.Prescription> lstItems = new ArrayList<ProfileActivity.Prescription>();

            lstItems.add(new ProfileActivity.Prescription("Albuterol Inhaler",null,false));
            lstItems.add(new ProfileActivity.Prescription("Lisinopril",null,false));
            lstItems.add(new ProfileActivity.Prescription("Maxalt","9:00",false));
            lstItems.add(new ProfileActivity.Prescription("Topamax","22:00",true));
//            lstItems.add(new ProfileActivity.Prescription("Nasanex","9:00",true));

            return lstItems;
        }
    }*/

    // Just a test for now... //TODO get actual data from the database
    private void fillPrescriptions(){


        numpres =0;

        Button btn0 = (Button) findViewById(R.id.pres0);
        Button btn1 = (Button) findViewById(R.id.pres1);
        Button btn2 = (Button) findViewById(R.id.pres2);
        Button btn3 = (Button) findViewById(R.id.pract3);
        Button btn4 = (Button) findViewById(R.id.pract4);


        DatabaseReference myref = mydatabase.getReference("Prescriptions");
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String mykey = ds.getKey();
                    if( numpres == 0){
                        presmykey0= mykey;
                        getprescription(mykey,btn0);
                        btn1.setVisibility(View.VISIBLE);


                    }
                    if( numpres == 1){
                        presmykey1 = mykey;
                        getprescription(mykey,btn1);
                        btn2.setVisibility(View.VISIBLE);


                    }
                    if( numpres == 2){
                        presmykey2 = mykey;
                        getcondition(mykey,btn2);
                        btn3.setVisibility(View.VISIBLE);


                    }
                    if( numpres == 3){
                        presmykey3= mykey;
                        getcondition(mykey,btn3);
                        btn4.setVisibility(View.VISIBLE);
                    }
                    if( numpres == 4){
                        presmykey4= mykey;
                        getcondition(mykey,btn4);

                    }
                    numpres =numpres+1;


                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        /*ProfileActivity.Prescription prescription = new ProfileActivity.Prescription();
        List<ProfileActivity.Prescription> pres = prescription.GetItems();
        int numPres = pres.size();

        Button btn0 = (Button) findViewById(R.id.pres0);
        if(numPres > 0) {
            btn0.setText(pres.get(0).name);
            btn0.setVisibility(View.VISIBLE);
            Button btn1 = (Button) findViewById(R.id.pres1);
            btn1.setVisibility(View.VISIBLE);
        }

        Button btn1 = (Button) findViewById(R.id.pres1);
        if(numPres > 1) {
            btn1.setText(pres.get(1).name);
            btn1.setVisibility(View.VISIBLE);
            Button btn2 = (Button) findViewById(R.id.pres2);
            btn2.setVisibility(View.VISIBLE);
        }

        Button btn2 = (Button) findViewById(R.id.pres2);
        if(numPres > 2) {
            btn2.setText(pres.get(2).name);
            btn2.setVisibility(View.VISIBLE);
            Button btn3 = (Button) findViewById(R.id.pres3);
            btn3.setVisibility(View.VISIBLE);
        }

        Button btn3 = (Button) findViewById(R.id.pres3);
        if(numPres > 3) {
            btn3.setText(pres.get(3).name);
            btn3.setVisibility(View.VISIBLE);
            Button btn4 = (Button) findViewById(R.id.pres4);
            btn4.setVisibility(View.VISIBLE);
        }

        Button btn4 = (Button) findViewById(R.id.pres4);
        if(numPres > 4) {
            btn4.setText(pres.get(4).name);
            btn4.setVisibility(View.VISIBLE);
        }*/
    }

    // Just a test for now... //TODO get actual data from the database
    /*public class Vaccine {
        private String name;
        private String date;
        private Boolean reminder;

        public Vaccine() {

        }

        public Vaccine(String name, String date, Boolean reminder) {
            this.name = name;
            this.date = date;
            this.reminder = reminder;
        }

        public List<ProfileActivity.Vaccine> GetItems() {
            List<ProfileActivity.Vaccine> lstItems = new ArrayList<ProfileActivity.Vaccine>();

            lstItems.add(new ProfileActivity.Vaccine("Flu","10/10/21",true));
            lstItems.add(new ProfileActivity.Vaccine("Tenaus","07/07/27",true));
//            lstItems.add(new ProfileActivity.Vaccine("Meningococcal","07/07/27",true));
//            lstItems.add(new ProfileActivity.Vaccine("Shingles","09/01/50",true));
//            lstItems.add(new ProfileActivity.Vaccine("MMR","09/01/50",false));

            return lstItems;
        }
    }*/

    // Just a test for now... //TODO get actual data from the database
    private void fillVaccines(){

        Log.d(TAG ,"IAM HERE IN SIDE FILL VACCINE");
       numvac =0;

        Button btn0 = (Button) findViewById(R.id.vacc0);
        Button btn1 = (Button) findViewById(R.id.vacc1);
        Button btn2 = (Button) findViewById(R.id.vacc2);
        Button btn3 = (Button) findViewById(R.id.vacc3);
        Button btn4 = (Button) findViewById(R.id.vacc4);


        DatabaseReference myref = mydatabase.getReference("Vaccines");
        myref.child(currentuserID).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String mykey = ds.getKey();
                    Log.d(TAG,"VACMYKEY IS "+mykey);
                    if( numvac == 0){
                        vacmykey0 = mykey;
                        getVacines(mykey,btn0);
                        btn1.setVisibility(View.VISIBLE);


                    }
                    if( numvac == 1){
                        vacmykey1 = mykey;
                        getVacines(mykey,btn1);
                        btn2.setVisibility(View.VISIBLE);


                    }
                    if( numvac == 2){
                        vacmykey2 = mykey;
                        getVacines(mykey,btn2);
                        btn3.setVisibility(View.VISIBLE);


                    }
                    if( numvac == 3){
                        vacmykey3 = mykey;
                        getVacines(mykey,btn3);
                        btn4.setVisibility(View.VISIBLE);
                    }
                    if( numvac== 4){
                        vacmykey4 = mykey;
                        getcondition(mykey,btn4);

                    }
                    numvac =numvac+1;


                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });













       /* ProfileActivity.Vaccine vaccine = new ProfileActivity.Vaccine();
        List<ProfileActivity.Vaccine> vacc = vaccine.GetItems();
        int numVacc = vacc.size();

        Button btn0 = (Button) findViewById(R.id.vacc0);
        if(numVacc > 0) {
            btn0.setText(vacc.get(0).name);
            btn0.setVisibility(View.VISIBLE);
            Button btn1 = (Button) findViewById(R.id.vacc1);
            btn1.setVisibility(View.VISIBLE);
        }

        Button btn1 = (Button) findViewById(R.id.vacc1);
        if(numVacc > 1) {
            btn1.setText(vacc.get(1).name);
            btn1.setVisibility(View.VISIBLE);
            Button btn2 = (Button) findViewById(R.id.vacc2);
            btn2.setVisibility(View.VISIBLE);
        }

        Button btn2 = (Button) findViewById(R.id.vacc2);
        if(numVacc > 2) {
            btn2.setText(vacc.get(2).name);
            btn2.setVisibility(View.VISIBLE);
            Button btn3 = (Button) findViewById(R.id.vacc3);
            btn3.setVisibility(View.VISIBLE);
        }

        Button btn3 = (Button) findViewById(R.id.vacc3);
        if(numVacc > 3) {
            btn3.setText(vacc.get(3).name);
            btn3.setVisibility(View.VISIBLE);
            Button btn4 = (Button) findViewById(R.id.vacc4);
            btn4.setVisibility(View.VISIBLE);
        }

        Button btn4 = (Button) findViewById(R.id.vacc4);
        if(numVacc > 4) {
            btn4.setText(vacc.get(4).name);
            btn4.setVisibility(View.VISIBLE);
        }*/
    }
    public void getcondition(String mykey, Button button) {
        DatabaseReference myref2 = mydatabase.getReference("Conditions");
        myref2.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                Conditions conditions = snapshot.getValue(Conditions.class);

                Log.d(TAG, "THE NAME IS "+ conditions.getCondition());


               button.setText(conditions.getCondition().toString());

                button.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void getprescription(String mykey, Button button) {
        DatabaseReference myref3 = mydatabase.getReference("Prescriptions");
        myref3.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               Prescriptions pres = snapshot.getValue(Prescriptions.class);

                button.setText(pres.getPrescription());
                button.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void getVacines(String mykey, Button button) {
        DatabaseReference myref3 = mydatabase.getReference("Vaccines");
        myref3.child(currentuserID).child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 Vaccines vac = snapshot.getValue(Vaccines.class);

                button.setText(vac.getVaccine());
                button.setVisibility(View.VISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}