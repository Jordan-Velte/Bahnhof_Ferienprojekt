package com.example.Bahnhof_Ferienprojekt.models;


//IMPORTS
//Import für Datumwerte

//Oberklasse
//Abstrakte Klasse --> kein Element kann von der Oberklasse erstellt werden (macht im Sachkontext keinen Sinn)!
public abstract class Zug {
    //VARIABLENDEKLARATION
    int id;
    String modell;
    String betreiber;
    int durchschnittsgeschwindigkeit;
    int wagonanzahl;
    String zulassungsdatum;

    //CONSTRUCTOR
    public Zug(int id, String modell, String betreiber, int durchschnittsgeschwindigkeit, int wagonanzahl, String zulassungsdatum){
        setId(id);
        setBetreiber(betreiber);
        setModell(modell);
        setDurchschnittsgeschwindigkeit(durchschnittsgeschwindigkeit);
        setWagonanzahl(wagonanzahl);
        setZulassungsdatum(zulassungsdatum);
    }

    //Setter & Getter
    public void setBetreiber(String betreiber) {
        this.betreiber = betreiber;
    }
    public void setDurchschnittsgeschwindigkeit(int durchschnittsgeschwindigkeit) {
        this.durchschnittsgeschwindigkeit = durchschnittsgeschwindigkeit;
    }
    public void setModell(String modell) {
        this.modell = modell;
    }
    public void setWagonanzahl(int wagonanzahl) {
        this.wagonanzahl = wagonanzahl;
    }
    public void setZulassungsdatum(String zulassungsdatum) {
        this.zulassungsdatum = zulassungsdatum;
    }
    public String getBetreiber() {
        return betreiber;
    }
    public int getDurchschnittsgeschwindigkeit() {
        return durchschnittsgeschwindigkeit;
    }
    public String getModell() {
        return modell;
     }
    public int getWagonanzahl() {
        return wagonanzahl;
    }
    public String getZulassungsdatum() {
        return zulassungsdatum;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

}

