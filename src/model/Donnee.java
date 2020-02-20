package model;

import java.util.Date;

public class Donnee {

    public enum TypeDonnee {TEXTE_LONG, TEXTE_COURT, DATE, INTEGER, FLOAT, BOOLEAN};

    public TypeDonnee type;
    public String texte;
    public Date date;
    public Integer nombreEntier;
    public Float nombreFloat;
    public Boolean bool;

    public Donnee(TypeDonnee type){
        this.type = type;
    }
}
