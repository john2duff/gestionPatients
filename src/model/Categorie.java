package model;

import java.util.ArrayList;

public class Categorie {

    public String nom;
    public ArrayList<Categorie> categories;
    public ArrayList<Information> informations;

    public Categorie(String nom){
        this.nom = nom;
        this.categories.clear();
        this.informations.clear();
    }

}
