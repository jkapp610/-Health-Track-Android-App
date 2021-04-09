package com.example.healthtrack;

public class Patient {
   private String FirstName;
    private String LastName;
    private String Birthday;
    private String Height;
    private String Weight;
   private String  Email;

    public Patient(){

    }

    public Patient(String firstName, String lastName, String birthday, String height, String weight, String email) {
        FirstName = firstName;
        LastName = lastName;
        Birthday = birthday;
        Height = height;
        Weight = weight;
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
