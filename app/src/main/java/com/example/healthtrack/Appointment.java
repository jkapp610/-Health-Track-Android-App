package com.example.healthtrack;

public class Appointment {
    private String doctor;
    private  String date;
    private  String time;
   //private  String reminder;

    public Appointment() {

    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //public String getReminder() {
        //return reminder;
   // }

    //public void setReminder(String reminder) {
        //this.reminder = reminder;
   // }
}
