package com.example.healthtrack;

public class Vaccines {
    private  String vaccine;
    private  String Dose;
    private  String datefrequency;
    private  String timerequency;

    private String type;

    public Vaccines() {

    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getDose() {
        return Dose;
    }

    public void setDose(String dose) {
        Dose = dose;
    }

    public String getDatefrequency() {
        return datefrequency;
    }

    public void setDatefrequency(String datefrequency) {
        this.datefrequency = datefrequency;
    }

    public String getTimerequency() {
        return timerequency;
    }

    public void setTimerequency(String timerequency) {
        this.timerequency = timerequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
