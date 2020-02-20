package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Patient implements Serializable{

    public enum Sexe {HOMME, FEMME;};

    private String prenom;
    private String nom;
    private Sexe sexe;
    public Date dateNaissance;
    private ArrayList<Dossier> dossiers;

    public Patient(String nom, String prenom, Sexe sexe, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.nom = nom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        dossiers.clear();
    }

    @Override
    public String toString(){
        return nom + " " + prenom;
    }

    @Override
    public boolean equals(Object o){
        if (o != null){
            return (this.prenom.equals(((Patient)o).prenom) &&
                    this.nom.equals(((Patient)o).nom) &&
                    this.dateNaissance.equals(((Patient)o).dateNaissance));
        }else{
            return false;
        }
    }

}
