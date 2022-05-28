package com.example.jokhdar_project;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Student {
    //private String id;
    private String stdID;
    private String name;
    private String surname;
    private String fName;
    private String natID;
    private String dateOfBirth;
    private String gender;


    private Student(){

    }
    public Student(/*String id, */String stdID, String name, String surname, String fName, String natID, String dateOfBirth,String gender){
        this.stdID = stdID;
        this.name = name;
        this.surname = surname;
        this.fName = fName;
        this.natID = natID;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getNatID() {
        return natID;
    }

    public void setNatID(String natID) {
        this.natID = natID;
    }

    public String getDOB() {
        return dateOfBirth;
    }

    public void setDOB(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
