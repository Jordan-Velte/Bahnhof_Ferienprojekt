package com.example.Bahnhof_Ferienprojekt.models;

public class Bahnhof {

    //VARIABLENDEKLARATION
    String name;
    String standort;
    int anzahl_gleise;
    //Da es mehrere Gleiskombination gibt: ArrayList für Gleis-Instanzen eines Bahnhofs!
    
    //CONSTRUCTOR
    public Bahnhof(String name, String standort, int anzahl_gleise){
        setName(name);
        setStandort(standort);
        setAnzahl_Gleise(anzahl_gleise);
    }

    // Setter und Getter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStandort() {
        return standort;
    }
    public void setStandort(String standort) {
        this.standort = standort;
    }
    public int getAnzahl_Gleise() {
        return anzahl_gleise;
    }
    public void setAnzahl_Gleise(int anzahl_gleise) {
        this.anzahl_gleise = anzahl_gleise;
    }

    

}