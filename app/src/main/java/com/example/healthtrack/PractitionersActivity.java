package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PractitionersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practitioners);

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

    public void onClickEdit(View view){
        //TODO: init values for the specific practitioner we clicked on, so we can pre-populate the fields in
        // the next screen. We're already grabbing the data & we know which button was pressed, so
        // this is the easiest way to get the existing practitioner info.
        Intent intent = new Intent(this, EditPractitionerActivity.class);
        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
    public class Practitioner {
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
    }

    // Just a test for now... //TODO get actual data from the database
    private void fillPractitioners(){
        PractitionersActivity.Practitioner appointment = new PractitionersActivity.Practitioner();
        List<PractitionersActivity.Practitioner> pract = appointment.GetItems();
        int numPract = pract.size();

        Button btn0 = (Button) findViewById(R.id.apt0);
        if(numPract > 0) {
            btn0.setText(pract.get(0).name + "\n" + pract.get(0).title + "\n" + pract.get(0).address);
            btn0.setVisibility(View.VISIBLE);
        }else btn0.setVisibility(View.INVISIBLE);

        Button btn1 = (Button) findViewById(R.id.apt1);
        if(numPract > 1) {
            btn1.setText(pract.get(1).name + "\n" + pract.get(1).title + "\n" + pract.get(1).address);
            btn1.setVisibility(View.VISIBLE);
        }else btn1.setVisibility(View.INVISIBLE);

        Button btn2 = (Button) findViewById(R.id.apt2);
        if(numPract > 2) {
            btn2.setText(pract.get(2).name + "\n" + pract.get(2).title + "\n" + pract.get(2).address);
            btn2.setVisibility(View.VISIBLE);
        }else btn2.setVisibility(View.INVISIBLE);

        Button btn3 = (Button) findViewById(R.id.apt3);
        if(numPract > 3) {
            btn3.setText(pract.get(3).name + "\n" + pract.get(3).title + "\n" + pract.get(3).address);
            btn3.setVisibility(View.VISIBLE);
        }else btn3.setVisibility(View.INVISIBLE);

        Button btn4 = (Button) findViewById(R.id.apt4);
        if(numPract > 4) {
            btn4.setText(pract.get(4).name + "\n" + pract.get(4).title + "\n" + pract.get(4).address);
            btn4.setVisibility(View.VISIBLE);
        }else btn4.setVisibility(View.INVISIBLE);
    }
}