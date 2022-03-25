package com.example.Bahnhof_Ferienprojekt.models;


//Oberklasse für Lokfuehrer, Passagier und Personal!
//Abstrakte Klasse --> kein Element kann von der Oberklasse erstellt werden (macht im Sachkontext keinen Sinn)!
public abstract class Person {
    //VARIABLENDEKLARATION
    String vorname;
    String nachname;

    //CONSTRUCTOR
    public Person(String vorname, String nachname){
        setVorname(vorname);
        setNachname(nachname);
    }
    
    // Setter und Getter
    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }



}