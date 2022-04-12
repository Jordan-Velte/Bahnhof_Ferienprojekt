package com.example.Bahnhof_Ferienprojekt.models;

//Subklasse von Oberklasse Person
public class Passagier extends Person{
    //VARIABLENDEKLARATION
    int kundennummer;

    //CONSTRUCTOR
    public Passagier(int id, String vorname, String nachname, int kundennummer){
        super(id, vorname, nachname);
        setKundennummer(kundennummer);
    }

    // Setter und Getter
    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }



}

