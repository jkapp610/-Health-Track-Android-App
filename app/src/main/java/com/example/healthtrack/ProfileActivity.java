package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fillConditions();
        fillPrescriptions();
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickNew(View view){
        // Determine which type of medical record button was pressed, so we can set up the next
        // screen with the proper input fields (and later send to the correct spot in the database).
        // NOTE: there is probably a better way of doing this, but this will have to work for now...
        Button btn = (Button) view;
        Intent intent = new Intent(this, NewProfileActivity.class);
        if((Button) findViewById(R.id.cond0) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond1) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond2) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond3) == btn) intent.putExtra("key_type","Condition");
        else if((Button) findViewById(R.id.cond4) == btn) intent.putExtra("key_type","Condition");

        else if((Button) findViewById(R.id.pres0) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres1) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres2) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres3) == btn) intent.putExtra("key_type","Prescription");
        else if((Button) findViewById(R.id.pres4) == btn) intent.putExtra("key_type","Prescription");

        else if((Button) findViewById(R.id.vacc0) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc1) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc2) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc3) == btn) intent.putExtra("key_type","Vaccine");
        else if((Button) findViewById(R.id.vacc4) == btn) intent.putExtra("key_type","Vaccine");

        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
    public class Condition {
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

            return lstItems;
        }
    }

    // Just a test for now... //TODO get actual data from the database
    private void fillConditions(){
        ProfileActivity.Condition condition = new ProfileActivity.Condition();
        List<ProfileActivity.Condition> cond = condition.GetItems();
        int numCond = cond.size();

        Button btn0 = (Button) findViewById(R.id.cond0);
        if(numCond > 0) {
            btn0.setText(cond.get(0).name);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.GONE);

        Button btn1 = (Button) findViewById(R.id.cond1);
        if(numCond > 1) {
            btn1.setText(cond.get(1).name);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.GONE);

        Button btn2 = (Button) findViewById(R.id.cond2);
        if(numCond > 2) {
            btn2.setText(cond.get(2).name);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.GONE);

        Button btn3 = (Button) findViewById(R.id.cond3);
        if(numCond > 3) {
            btn3.setText(cond.get(3).name);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.GONE);

        Button btn4 = (Button) findViewById(R.id.cond4);
        if(numCond > 4) {
            btn4.setText(cond.get(4).name);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.GONE);
    }

    // Just a test for now... //TODO get actual data from the database
    public class Prescription {
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

            return lstItems;
        }
    }

    // Just a test for now... //TODO get actual data from the database
    private void fillPrescriptions(){
        ProfileActivity.Prescription prescription = new ProfileActivity.Prescription();
        List<ProfileActivity.Prescription> pres = prescription.GetItems();
        int numPres = pres.size();

        Button btn0 = (Button) findViewById(R.id.pres0);
        if(numPres > 0) {
            btn0.setText(pres.get(0).name);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.GONE);

        Button btn1 = (Button) findViewById(R.id.pres1);
        if(numPres > 1) {
            btn1.setText(pres.get(1).name);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.GONE);

        Button btn2 = (Button) findViewById(R.id.pres2);
        if(numPres > 2) {
            btn2.setText(pres.get(2).name);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.GONE);

        Button btn3 = (Button) findViewById(R.id.pres3);
        if(numPres > 3) {
            btn3.setText(pres.get(3).name);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.GONE);

        Button btn4 = (Button) findViewById(R.id.pres4);
        if(numPres > 4) {
            btn4.setText(pres.get(4).name);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.GONE);
    }

}