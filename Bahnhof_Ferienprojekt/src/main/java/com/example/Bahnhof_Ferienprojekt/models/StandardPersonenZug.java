package com.example.Bahnhof_Ferienprojekt.models;

//Subklasse von Oberklasse Zug
public class StandardPersonenZug extends Zug{
    //VARIABLENDEKLARATION
    //Anzahl
    int maxpersonenladung;

    //CONSTRUCTOR
    public StandardPersonenZug(String modell, String betreiber, int durchschnittsgeschwindigkeit, int wagonanzahl, String zulassungsdatum, int maxpersonenladung){
        super(modell, betreiber, durchschnittsgeschwindigkeit, wagonanzahl, zulassungsdatum);
        setMaxpersonenladung(maxpersonenladung);
    }

    //Setter und Getter
    public void setMaxpersonenladung(int maxpersonenladung) {
        this.maxpersonenladung = maxpersonenladung;
    }
    public int getMaxpersonenladung() {
        return maxpersonenladung;
    }

}
