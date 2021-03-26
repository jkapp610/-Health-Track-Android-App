package com.example.healthtrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentsActivity extends AppCompatActivity {

    ScrollView appointmentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        appointmentsView = findViewById(R.id.appointmentsView);
        FillList();
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Just a test for now... //TODO get actual data from the database
    public void FillList() {
        try {

            Appointment items = new Appointment();
            List<Map<String, String>> prolist = new ArrayList<Map<String, String>>();
            String[] from = {"ItemRef", "Item", "Supplier", "LatestStock", "SalePrice", "BinNumber", "Balance"};
            int[] views = {R.id.txtPractioner, R.id.txtDate};

            List<Appointment> lsr = items.GetItems();

            for (int i = 0; i < lsr.size(); i++) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("Practitioner", lsr.get(i).Item);
                datanum.put("Date", lsr.get(i).Item);

                prolist.add(datanum);
            }
            // TODO create "lst_items.xml" to make the formating template for the list
            final SimpleAdapter simpleAdapter = new SimpleAdapter(AppointmentsActivity.this, prolist, R.layout.lst_items, from, views);
            appointmentsView.setAdapter(simpleAdapter);
        } catch (Exception ex) {
            Toast.makeText(AppointmentsActivity.this, ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    public class Appointment {
        private String practitoner;
        private String date;

        public Appointment() {

        }

        public Appointment(String practitoner, String date) {
            this.practitoner = practitoner;
            this.date = date;
        }

        public List<Appointment> GetItems() {
            List<Appointment> lstItems = new ArrayList<Appointment>();

            lstItems.add(new Appointment("Dr. Hilton","9:30AM Feb. 28th 2021");
            lstItems.add(new Appointment("Dr. Sparks","11:00AM Aug. 10th 2021");
            lstItems.add(new Appointment("Dr. Allison","10:00AM May. 4th 2021");

            return lstItems;
        }
    }
}

