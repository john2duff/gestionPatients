package model;

import java.util.ArrayList;

public class DataBasePatient {

    public String nomDataBase;
    public ArrayList<Patient> listPatient;

    public DataBasePatient(String nomDataBase) {
        this.nomDataBase = nomDataBase;
        this.listPatient.clear();
    }
}
