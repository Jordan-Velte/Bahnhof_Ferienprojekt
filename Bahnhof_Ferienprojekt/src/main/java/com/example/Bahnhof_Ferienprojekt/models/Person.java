package com.example.Bahnhof_Ferienprojekt.models;


//Oberklasse für Lokfuehrer, Passagier und Personal!
//Abstrakte Klasse --> kein Element kann von der Oberklasse erstellt werden (macht im Sachkontext keinen Sinn)!
public abstract class Person {
    //VARIABLENDEKLARATION
    int id; 
    String vorname;
    String nachname;

    //CONSTRUCTOR
    public Person(int id, String vorname, String nachname){
        setId(id);
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
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }




}