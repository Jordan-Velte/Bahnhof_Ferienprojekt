package com.example.Bahnhof_Ferienprojekt.models;



//Subklasse von Oberklasse Person
public class Personal extends Person{
    //VARIABLENDEKLARATION
    int personalnummer;

    //CONSTRUCTOR
    public Personal(int id, String vorname, String nachname, int personalnummer){
        super(id, vorname, nachname);
        setPersonalnummer(personalnummer);
    }

    // Setter und Getter
    public int getPersonalnummer() {
        return personalnummer;
        
    }

    public void setPersonalnummer(int personalnummer) {
        this.personalnummer = personalnummer;
    }


}
