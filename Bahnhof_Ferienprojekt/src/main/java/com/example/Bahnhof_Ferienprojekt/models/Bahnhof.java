package com.example.Bahnhof_Ferienprojekt.models;

public class Bahnhof {

    //Annahme: Bahnhof braucht Passagier (eigentlich falsch)
    //VARIABLENDEKLARATION
    int id;
    String name;
    String standort;
    int anzahl_gleise;
    String passagier;
    int passagierId;

    
    //CONSTRUCTOR
    public Bahnhof(int id, String name, String standort, int anzahl_gleise, String passagier, int passagierId){
        setId(id);
        setName(name);
        setStandort(standort);
        setAnzahl_Gleise(anzahl_gleise);
        setPassagier(passagier);
        setPassagierId(passagierId);
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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setPassagier(String passagier) {
        this.passagier = passagier;
    }
    public String getPassagier() {
        return passagier;
    }
    public void setPassagierId(int passagierId) {
        this.passagierId = passagierId;
    }
    public int getPassagierId() {
        return passagierId;
    }

    

}
