package com.example.Bahnhof_Ferienprojekt;
//Imports
import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.Bahnhof;
import com.example.Bahnhof_Ferienprojekt.models.Passagier;
import com.example.Bahnhof_Ferienprojekt.models.Personal;
import com.example.Bahnhof_Ferienprojekt.models.StandardPersonenZug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBController {
    //Variablendeklaration
    String connectionUrl;
    String username;
    String passwort;
    public DBController(){
        // ACHTUNG! Hier den Port entsprechend XAMPP austauschen, z.B. jdbc:mysql://localhost:3306/javadb
        // javadb ist der Name der Datenbank, kann auch bei euch anders sein!
        setConnectionUrl("jdbc:mysql://localhost:3306/javadb");
        setPasswort("root");
        setUsername("root");
    }

    //BAHNHÖFE
    // Holt alle Bahnhof aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Bahnhof> getAllBahnhoefe(){

        // Lokale Bahnhof-Arraylist erstellen
        ArrayList<Bahnhof> bahnhoefe = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllBahnhoefe = "SELECT * FROM `bahnhoefe` JOIN passagier on passagier.id=bahnhoefe.id_passagier";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllBahnhoefe); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String name = rs.getString("name");
                String standort = rs.getString("standort");
                int anzahl_gleise = (int) rs.getLong("anzahl_gleise");
                String passagier = rs.getString("passagiername");
                int passagierId = (int) rs.getLong("id_passagier");
                bahnhoefe.add(new Bahnhof(id, name, standort, anzahl_gleise, passagier, passagierId));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhoefe;
    }

    // Füge neuen Bahnhof hinzu
    public void addNewBahnhof(String name, String standort, int anzahl_gleise, int passagier) {
        try{
            String sqlSelectAllPersons = "INSERT INTO bahnhoefe(name,standort,anzahl_gleise,id_passagier) VALUES('"+name+"','"+standort+"', '"+anzahl_gleise+"', '"+passagier+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            //Rückfrage!
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche einen Bahnhof
    public void delBahnhof(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM bahnhoefe WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifischen Bahnhof
    public Bahnhof getBahnhof(int id){
        Bahnhof bahnhof = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM `bahnhoefe` JOIN passagier ON passagier.id=bahnhoefe.id_passagier WHERE bahnhoefe.id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int bahnhofId = (int) rs.getLong("id");
                String name = rs.getString("name");
                String standort = rs.getString("standort");
                int anzahl_gleise = (int) rs.getLong("anzahl_gleise");
                String passagier = rs.getString("passagiername");
                int passagierId = (int) rs.getLong("id_passagier");
                bahnhof = new Bahnhof(bahnhofId, name, standort, anzahl_gleise, passagier, passagierId);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhof;
    }

    // Hole spezifischen Bahnhof und aktualisiere diese ab
    public Bahnhof updateBahnhof(int id, String name, String standort, int anzahl_gleise, int passagierId){
        Bahnhof bahnhof = null;
        try{
            String sqlSelectAllPersons = "UPDATE bahnhoefe SET name='"+name+"', standort='"+standort+"', anzahl_gleise='"+anzahl_gleise+"', id_passagier='"+passagierId+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhof;
    }

    //STANDARDPERSONENZUEGE, DB: standardpersonenzuege
    // Holt alle Stanadardpersonenzüge aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<StandardPersonenZug> getAllStandardPersonenZuege(){

        // Lokale Standardpersonenzug-Arraylist erstellen
        ArrayList<StandardPersonenZug> standardpersonenzuege = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllStandardpersonenzuege = "SELECT * FROM standardpersonenzuege";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllStandardpersonenzuege); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String modell = rs.getString("modell");
                String betreiber = rs.getString("betreiber");
                int durchschnittsgeschwindigkeit = (int) rs.getLong("durchschnittsgeschwindigkeit");
                int wagonanzahl = (int) rs.getLong("wagonanzahl");
                String zulassungsdatum = rs.getString("zulassungsdatum");
                int maxpersonenladung = (int) rs.getLong("maxpersonenladung");

                standardpersonenzuege.add(new StandardPersonenZug(id, modell, betreiber, durchschnittsgeschwindigkeit, wagonanzahl, zulassungsdatum, maxpersonenladung));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return standardpersonenzuege;
    }
    // Füge neuen Standardpersonenzug hinzu
    public void addNewStandardpersonenzug(String modell, String betreiber, int durchschnittsgeschwindigkeit, int wagonanzahl, String zulassungsdatum, int maxpersonenladung) {
        try{
            String sqlSelectAllPersons = "INSERT INTO standardpersonenzuege(modell,betreiber,durchschnittsgeschwindigkeit, wagonanzahl, zulassungsdatum, maxpersonenladung) VALUES('"+modell+"','"+betreiber+"', '"+durchschnittsgeschwindigkeit+"', '"+wagonanzahl+"', '"+zulassungsdatum+"', '"+maxpersonenladung+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            //Rückfrage!
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche einen Standardpersonenzug
    public void delStandardpersonenzug(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM standardpersonenzuege WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifischen Standardpersonenzug
    public StandardPersonenZug getStandardpersonenzug(int id){
        StandardPersonenZug standardpersonenzug = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM standardpersonenzuege WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int standardpersonenzugId = (int) rs.getLong("id");
                String modell = rs.getString("modell");
                String betreiber = rs.getString("betreiber");
                int durchschnittsgeschwindigkeit = (int) rs.getLong("durchschnittsgeschwindigkeit");
                int wagonanzahl = (int) rs.getLong("wagonanzahl");
                String zulassungsdatum = rs.getString("zulassungsdatum");
                int maxpersonenladung = (int) rs.getLong("maxpersonenladung");
                standardpersonenzug = new StandardPersonenZug(standardpersonenzugId, modell, betreiber, durchschnittsgeschwindigkeit, wagonanzahl, zulassungsdatum, maxpersonenladung);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return standardpersonenzug;
    }

    // Hole spezifischen Standardpersonenzug und aktualisiere diesen ab
    public StandardPersonenZug updateStandardpersonenzug(int id, String modell, String betreiber, int durchschnittsgeschwindigkeit, int wagonanzahl, String zulassungsdatum, int maxpersonenladung){
        StandardPersonenZug standardpersonenzug = null;
        try{
            String sqlSelectAllPersons = "UPDATE standardpersonenzuege SET modell='"+modell+"', betreiber='"+betreiber+"', durchschnittsgeschwindigkeit='"+durchschnittsgeschwindigkeit+"', wagonanzahl='"+wagonanzahl+"', zulassungsdatum='"+zulassungsdatum+"', maxpersonenladung='"+maxpersonenladung+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return standardpersonenzug;
    }

    //PASSAGIERE, DB: passagiere
    // Holt alle Passagiere aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Passagier> getAllPassagiere(){

        // Lokale Passagier-Arraylist erstellen
        ArrayList<Passagier> passagiere = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllPassagiere = "SELECT * FROM passagiere";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPassagiere); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int kundennummer = (int) rs.getLong("kundennummer");
                passagiere.add(new Passagier(id, vorname, nachname, kundennummer));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return passagiere;
    }
    // Füge neuen Passagier hinzu
    public void addNewPassagier(String vorname, String nachname, int kundennummer) {
        try{
            String sqlSelectAllPersons = "INSERT INTO passagiere(vorname,nachname,kundennummer) VALUES('"+vorname+"','"+nachname+"', '"+kundennummer+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            //Rückfrage!
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche einen Passagier
    public void delPassagier(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM passagiere WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifischen Passagier
    public Passagier getPassagier(int id){
        Passagier passagier = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM passagiere WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int PassagierId = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int kundennummer = (int) rs.getLong("kundennummer");
                passagier = new Passagier(PassagierId, vorname, nachname, kundennummer);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return passagier;
    }

    // Hole spezifischen Passagier und aktualisiere diesen ab
    public Passagier updatePassagier(int id, String vorname, String nachname, int kundennummer){
        Passagier passagier = null;
        try{
            String sqlSelectAllPersons = "UPDATE passagiere SET vorname='"+vorname+"', nachname='"+nachname+"', kundennummer='"+kundennummer+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return passagier;
    }

    //PERSONAL, DB: personale
    // Holt alle Personal aus der Datenbank und gibt diese als ArrayList zurück!
    public ArrayList<Personal> getAllPersonale(){

        // Lokale Personal-Arraylist erstellen
        ArrayList<Personal> personale = new ArrayList<>();

        // Das ist DB-Query
        String sqlSelectAllPersonale = "SELECT * FROM personale";

        // Verbindung aufbauen mit USERNAME root und PASSWORT root
        try{
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersonale); 
            ResultSet rs = ps.executeQuery();
            // Solange es Datensätze in der von der DB angefragen Ressource gibt, werden diese durchgearbeitet und dann als eine ArrayList zurückgegeben
            while (rs.next()) {
                int id = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int personalnummer = (int) rs.getLong("personalnummer");
                personale.add(new Personal(id, vorname, nachname, personalnummer));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personale;
    }
    // Füge neuen Personal hinzu
    public void addNewPersonal(String vorname, String nachname, int personalnummer) {
        try{
            String sqlSelectAllPersons = "INSERT INTO personale(vorname,nacnname,personalnummer) VALUES('"+vorname+"','"+nachname+"', '"+personalnummer+"');";
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            //Rückfrage!
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Lösche einen Personal
    public void delPersonal(int id){
        try{

            String sqlSelectAllPersons = "DELETE FROM personale WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort());
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            // als Return von executeUpdate kommt 0 (FAIL) oder 1 (OK!) zurück
            int rs = ps.executeUpdate();
            System.out.println(rs);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    // Hole spezifischen Personal
    public Personal getPersonal(int id){
        Personal personal = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM personale WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int PersonalId = (int) rs.getLong("id");
                String vorname = rs.getString("vorname");
                String nachname = rs.getString("nachname");
                int personalnummer = (int) rs.getLong("personalnummer");
                personal = new Personal(PersonalId, vorname, nachname, personalnummer);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personal;
    }

    // Hole spezifischen Personal und aktualisiere diesen ab
    public Personal updatePersonal(int id, String vorname, String nachname, int personalnummer){
        Personal personal = null;
        try{
            String sqlSelectAllPersons = "UPDATE passagiere SET vorname='"+vorname+"', nachname='"+nachname+"', personalnummer='"+personalnummer+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return personal;
    }



    //Setter und Getter
    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getConnectionUrl() {
        return connectionUrl;
    }
    public String getPasswort() {
        return passwort;
    }
    public String getUsername() {
        return username;
    }

}
