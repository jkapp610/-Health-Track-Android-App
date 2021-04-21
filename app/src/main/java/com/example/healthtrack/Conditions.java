package com.example.healthtrack;

public class Conditions {

   private String condition;
   private String datediagnosed;
   private String Treatment;



    public Conditions() {

    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDatediagnosed() {
        return datediagnosed;
    }

    public void setDatediagnosed(String datediagnosed) {
        this.datediagnosed = datediagnosed;
    }

    public String getTreatment() {
        return Treatment;
    }

    public void setTreatment(String treatment) {
        Treatment = treatment;
    }
}
