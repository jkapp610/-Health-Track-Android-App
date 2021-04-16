package com.example.healthtrack;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

public class DatabaseHelper<T> {
    private FirebaseDatabase mydatabase;
    private DatabaseReference myref;
    private T mydata;
    private  GenericTypeIndicator<T> mygenaricdata;

    public DatabaseHelper() {
        this.mydatabase =FirebaseDatabase.getInstance();

    }
    public void Create(String mykey,T newobj,String Tablename){
        myref = mydatabase.getReference(Tablename);
        myref.child(mykey).setValue(newobj);
    }
    public void getData(String mykey,String Tablename){

        myref = mydatabase.getReference(Tablename);
        myref.child(mykey).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               mydata = (T) snapshot.getValue();



                //mydata= (T) snapshot.getValue();
               // Toast.makeText(DatabaseHelper.class,"this is the database healper+",Toast.LENGTH_LONG).show();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }
    public T returnmydata(){

        return mydata;

    }
}
