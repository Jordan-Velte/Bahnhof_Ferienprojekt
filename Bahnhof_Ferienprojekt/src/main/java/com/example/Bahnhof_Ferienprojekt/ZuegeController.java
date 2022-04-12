//IMPORTS
package com.example.Bahnhof_Ferienprojekt;

import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.StandardPersonenZug;
import com.example.Bahnhof_Ferienprojekt.models.Zug;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Decorator! --> legt fest, dass Klasse ein Controller ist!
@Controller
public class ZuegeController {
    //ArrayList-Deklaration
    ArrayList<Zug> zuege;
    ArrayList<StandardPersonenZug> standardpersonenzuege;
    


    public ZuegeController(){
        setStandardpersonenzuege(new ArrayList<StandardPersonenZug>());
        setZuege(new ArrayList<Zug>());
        loadStandardpersonenzuegeFromDB();
        //createDemoData();
    }

    // Lädt aktuelle Bahnhöfe aus der Datenbank und wirft bei Bedarf eine SQL-Exception
    private void loadStandardpersonenzuegeFromDB(){
        DBController db = new DBController();
        setStandardpersonenzuege(db.getAllStandardPersonenZuege());
    }


    //Demodaten
    /*
    private void createDemoData(){
        StandardPersonenZug spz1 = new StandardPersonenZug("Intercity 1", "Deutsche Bahn", 200, 8, "1971-09-26", 703);
        StandardPersonenZug spz2 = new StandardPersonenZug("Alstom Coradia Continental", "Deutsche Bahn", 160, 4, "2008-12-01", 450);
        getStandardpersonenzuege().add(spz1);
        getStandardpersonenzuege().add(spz2);
        getZuege().add(spz1);
        getZuege().add(spz2);
    }
    */

    // Betreiber erstellen und zurückgeben
    private ArrayList<String> getBetreiber(){
        ArrayList<String> betreiber = new ArrayList<>();

        betreiber.add("Deutsche Bahn");
        betreiber.add("Ausländischer Betreiber");

        return betreiber;
    }

    // Wagonanzahl erstellen und zurückgeben
    private ArrayList<Integer> getWagonanzahl(){
        ArrayList<Integer> wagonanzahl = new ArrayList<>();

        wagonanzahl.add(1);
        wagonanzahl.add(2);
        wagonanzahl.add(3);
        wagonanzahl.add(4);
        wagonanzahl.add(5);
        wagonanzahl.add(6);
        wagonanzahl.add(7);
        wagonanzahl.add(8);
        wagonanzahl.add(9);
        wagonanzahl.add(10);
        wagonanzahl.add(11);
        wagonanzahl.add(12);
        wagonanzahl.add(13);
        wagonanzahl.add(14);
        wagonanzahl.add(15);
        wagonanzahl.add(16);
        wagonanzahl.add(17);
        wagonanzahl.add(18);
        wagonanzahl.add(19);
        wagonanzahl.add(20);

        return wagonanzahl;
    }



    @GetMapping("/standardpersonenzuege")
    public String standardpersonenzuege(@RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        loadStandardpersonenzuegeFromDB();
        model.addAttribute("activePage", "standardpersonenzuege");
        model.addAttribute("standardpersonenzuege", getStandardpersonenzuege());
        //Betreiber für einen Standardpersonenzug holen
        model.addAttribute("betreiber", getBetreiber());
        //wagonanzahl für einen Standardpersonenzug holen
        model.addAttribute("wagonanzahl", getWagonanzahl());
        return "index.html";
    }

    @RequestMapping("/delstandardpersonenzug")
    public String delstandardpersonenzug (@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        DBController db = new DBController();
        db.delStandardpersonenzug(id);
        return "redirect:/standardpersonenzuege";
    }

    @RequestMapping("/changestandardpersonenzug")
    public String changestandardpersonenzug(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changestandardpersonenzug") String activePage, Model model){
        DBController db = new DBController();
        model.addAttribute("standardpersonenzug", db.getStandardpersonenzug(id));
        model.addAttribute("standardpersonenzugid", id);
        
        // Möglichen Betreiber hier hinzufügen
        model.addAttribute("betreiber", getBetreiber());
        model.addAttribute("wagonanzahl", getWagonanzahl());
        // Möglichen Wagonanzahl hier hinzufügen
        model.addAttribute("activePage", "standardpersonenzugUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatestandardpersonenzug")
    public String updatestandardpersonenzug(@RequestParam(name="standardpersonenzugId", required = true, defaultValue = "null") int standardpersonenzugId, @RequestParam(name="standardpersonenzugModell", required = true, defaultValue = "null") String standardpersonenzugModell,@RequestParam(name="standardpersonenzugBetreiber", required = true, defaultValue = "null") String standardpersonenzugBetreiber, @RequestParam(name="standardpersonenzugDurchschnittsgeschwindigkeit", required = true, defaultValue = "null") int standardpersonenzugDurchschnittsgeschwindigkeit, @RequestParam(name="standardpersonenzugWagonanzahl", required = true, defaultValue = "null") int standardpersonenzugWagonanzahl, @RequestParam(name="standardpersonenzugZulassungsdatum", required = true, defaultValue = "null") String standardpersonenzugZulassungsdatum, @RequestParam(name="standardpersonenzugMaxpersonenladung", required = true, defaultValue = "null") int standardpersonenzugMaxpersonenladung, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        DBController db = new DBController();
        db.updateStandardpersonenzug(standardpersonenzugId, standardpersonenzugModell, standardpersonenzugBetreiber, standardpersonenzugDurchschnittsgeschwindigkeit, standardpersonenzugWagonanzahl, standardpersonenzugZulassungsdatum, standardpersonenzugMaxpersonenladung);
        return "redirect:/standardpersonenzuege";
    }
    
    @RequestMapping("/addstandardpersonenzug")
    public String addstandardpersonenzug(@RequestParam(name="standardpersonenzugModell", required = true, defaultValue = "null") String standardpersonenzugModell,@RequestParam(name="standardpersonenzugBetreiber", required = true, defaultValue = "null") String standardpersonenzugBetreiber, @RequestParam(name="standardpersonenzugDurchschnittsgeschwindigkeit", required = true, defaultValue = "null") int standardpersonenzugDurchschnittsgeschwindigkeit, @RequestParam(name="standardpersonenzugWagonanzahl", required = true, defaultValue = "null") int standardpersonenzugWagonanzahl, @RequestParam(name="standardpersonenzugZulassungsdatum", required = true, defaultValue = "null") String standardpersonenzugZulassungsdatum, @RequestParam(name="standardpersonenzugMaxpersonenladung", required = true, defaultValue = "null") int standardpersonenzugMaxpersonenladung, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        DBController db = new DBController();
        db.addNewStandardpersonenzug(standardpersonenzugModell, standardpersonenzugModell, standardpersonenzugDurchschnittsgeschwindigkeit, standardpersonenzugWagonanzahl, standardpersonenzugZulassungsdatum, standardpersonenzugMaxpersonenladung);
        return "redirect:/standardpersonenzuege";
       
    }

    //Setter und Getter
    public void setStandardpersonenzuege(ArrayList<StandardPersonenZug> standardpersonenzuege) {
        this.standardpersonenzuege = standardpersonenzuege;
    }
    public void setZuege(ArrayList<Zug> zuege) {
        this.zuege = zuege;
    }
    public ArrayList<StandardPersonenZug> getStandardpersonenzuege() {
        return standardpersonenzuege;
    }
    public ArrayList<Zug> getZuege() {
        return zuege;
    }



}
