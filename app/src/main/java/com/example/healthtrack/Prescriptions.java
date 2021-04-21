package com.example.healthtrack;

public class Prescriptions {
    private  String prescription;
    private  String Dose;
    private  String timefrequency;
    private String datefrequency;
    private String type;

    public Prescriptions() {

    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDose() {
        return Dose;
    }

    public void setDose(String dose) {
        Dose = dose;
    }


    public String getTimefrequency() {
        return timefrequency;
    }

    public void setTimefrequency(String timefrequency) {
        this.timefrequency = timefrequency;
    }

    public String getDatefrequency() {
        return datefrequency;
    }

    public void setDatefrequency(String datefrequency) {
        this.datefrequency = datefrequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
