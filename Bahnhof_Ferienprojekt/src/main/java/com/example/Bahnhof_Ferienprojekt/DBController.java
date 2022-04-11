package com.example.Bahnhof_Ferienprojekt;
//Imports
import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.Bahnhof;

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
        String sqlSelectAllBahnhoefe = "SELECT * FROM bahnhoefe";

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
                bahnhoefe.add(new Bahnhof(id, name, standort, anzahl_gleise));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhoefe;
    }

    // Füge neuen Bahnhof hinzu
    public void addNewBahnhof(String name, String standort, int anzahl_gleise) {
        try{
            String sqlSelectAllPersons = "INSERT INTO bahnhoefe(name,standort,anzahl_gleise) VALUES('"+name+"','"+standort+"', '"+anzahl_gleise+"');";
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

    // Hole spezifische Todo
    public Bahnhof getBahnhof(int id){
        Bahnhof bahnhof = null;
        try{
            String sqlSelectAllPersons = "SELECT * FROM bahnhoefe WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int bahnhofId = (int) rs.getLong("id");
                String name = rs.getString("name");
                String standort = rs.getString("standort");
                int anzahl_gleise = (int) rs.getLong("anzahl_gleise");
                bahnhof = new Bahnhof(bahnhofId, name, standort, anzahl_gleise);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhof;
    }

    // Hole spezifischen Bahnhof und aktualisiere diese ab
    public Bahnhof updateBahnhof(int id, String name, String standort, int anzahl_gleise){
        Bahnhof bahnhof = null;
        try{
            String sqlSelectAllPersons = "UPDATE bahnhoefe SET name='"+name+"', standort='"+standort+"', anzahl_gleise='"+anzahl_gleise+"' WHERE id="+String.valueOf(id);
            Connection conn = DriverManager.getConnection(getConnectionUrl(), getUsername(), getPasswort()); 
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }

        return bahnhof;
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
