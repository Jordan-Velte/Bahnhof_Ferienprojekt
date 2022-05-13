//IMPORTS
package com.example.Bahnhof_Ferienprojekt;

import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.Bahnhof;
import com.example.Bahnhof_Ferienprojekt.models.Passagier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Decorator! --> legt fest, dass Klasse ein Controller ist!
@Controller
public class BahnhoefeController {
    //ArrayList-Deklaration
    ArrayList<Bahnhof> bahnhoefe;

    public BahnhoefeController(){
        setBahnhoefe(new ArrayList<Bahnhof>());
        //createDemoData();
        loadBahnhoefeFromDB();
    }

    //Demodaten
    
    /*
    private void createDemoData(){
        getBahnhoefe().add(new Bahnhof(0, "Hamburg Hauptbahnhof", "Hamburg", 5));
        getBahnhoefe().add(new Bahnhof(1, "Berlin Hauptbahnhof", "Berlin", 4));
        getBahnhoefe().add(new Bahnhof(2, "Hannover Regionalbahnhof", "Hannover", 2));
    }
    */
    

    // Lädt aktuelle Bahnhöfe aus der Datenbank und wirft bei Bedarf eine SQL-Exception
    private void loadBahnhoefeFromDB(){
        DBController db = new DBController();
        setBahnhoefe(db.getAllBahnhoefe());
    }

    //Standorte erstellen und zurückgeben
    private ArrayList<String> getStandorte(){
        ArrayList<String> standorte = new ArrayList<>();
        standorte.add("Hamburg");
        standorte.add("Berlin");
        standorte.add("Hannover");
        standorte.add("Frankfurt am Main");
        standorte.add("München");
        standorte.add("Stuttgart");
        standorte.add("Nürnberg");
        return standorte;
    }

    //Anzahl Gleise erstellen und zurückgeben
    private ArrayList<Integer> getAnzahl_Gleise(){
        ArrayList<Integer> anzahl_gleise = new ArrayList<>();
        anzahl_gleise.add(1);
        anzahl_gleise.add(2);
        anzahl_gleise.add(3);
        anzahl_gleise.add(4);
        anzahl_gleise.add(5);
        anzahl_gleise.add(6);
        anzahl_gleise.add(7);
        anzahl_gleise.add(8);
        anzahl_gleise.add(9);
        anzahl_gleise.add(10);
        anzahl_gleise.add(11);
        anzahl_gleise.add(12);
        anzahl_gleise.add(13);
        anzahl_gleise.add(14);
        anzahl_gleise.add(15);
        anzahl_gleise.add(16);
        anzahl_gleise.add(17);
        anzahl_gleise.add(18);
        anzahl_gleise.add(19);
        anzahl_gleise.add(20);
        return anzahl_gleise;
    }

    //Holt alle Passagiere aus der DB
    private ArrayList<Passagier> getPassagiere(){
        ArrayList<Passagier> passagiere = new ArrayList<>();
        DBController db = new DBController();
        passagiere = db.getAllPassagiere();
        return passagiere;
    }



    @GetMapping("/bahnhoefe")
    public String bahnhoefe(@RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        loadBahnhoefeFromDB();
        model.addAttribute("activePage", "bahnhoefe");
        model.addAttribute("bahnhoefe", getBahnhoefe());

        // Standort für einen Bahnhof holen
        model.addAttribute("standorte", getStandorte());
        // Anzahl Gleise für einen Bahnhof holen
        model.addAttribute("anzahl_gleise", getAnzahl_Gleise());
        //Passagier für einen Bahnhof holen
        model.addAttribute("passagiere", getPassagiere());
        return "index.html";
    }

    @RequestMapping("/delbahnhof")
    public String delbahnhof(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        DBController db = new DBController();
        db.delBahnhof(id);
        return "redirect:/bahnhoefe";
    }

    @RequestMapping("/changebahnhof")
    public String changebahnhof(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changebahnhof") String activePage, Model model){
        // Bahnhof zur Bearbeitung laden
        DBController db = new DBController();
        model.addAttribute("bahnhof", db.getBahnhof(id));
        model.addAttribute("bahnhofid", id);
        
        //Möglichen Standort hier hinzufügen
        model.addAttribute("standorte", getStandorte());
        //Mögliche Anzahl Gleise hier hinzufügen
        model.addAttribute("anzahl_gleise", getAnzahl_Gleise());

        //Mögliche Passagiere hier hinzufügen:
        model.addAttribute("passagiere", getPassagiere());

        model.addAttribute("activePage", "bahnhofUpdate");
        return "index.html";
    }

    @RequestMapping("/updatebahnhof")
    public String updatebahnhof(@RequestParam(name="bahnhofId", required = true, defaultValue = "null") int bahnhofId, @RequestParam(name="bahnhofName", required = true, defaultValue = "null") String bahnhofName, @RequestParam(name="bahnhofStandort", required = true, defaultValue = "null") String bahnhofStandort, @RequestParam(name="bahnhofAnzahl_Gleise", required = true, defaultValue = "null") int bahnhofAnzahl_Gleise, @RequestParam(name="bahnhofPassagierId", required = true, defaultValue = "null") int bahnhofPassagierId, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        DBController db = new DBController();
        db.updateBahnhof(bahnhofId, bahnhofName, bahnhofStandort, bahnhofAnzahl_Gleise, bahnhofPassagierId);
        return "redirect:/bahnhoefe";
    }

    @RequestMapping("/addbahnhof")
    public String addbahnhof(@RequestParam(name="bahnhofName", required = true, defaultValue = "null") String bahnhofName,@RequestParam(name="bahnhofStandort", required = true, defaultValue = "null") String bahnhofStandort, @RequestParam(name="bahnhofAnzahl_Gleise", required = true, defaultValue = "null") int bahnhofAnzahl_Gleise, @RequestParam(name="bahnhofPassagierId", required = true, defaultValue = "null") int bahnhofPassagierId, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        DBController db = new DBController();
        db.addNewBahnhof(bahnhofName, bahnhofStandort, bahnhofAnzahl_Gleise, bahnhofPassagierId);
        return "redirect:/bahnhoefe";
    }

    //Setter und Getter
    public void setBahnhoefe(ArrayList<Bahnhof> bahnhoefe) {
        this.bahnhoefe = bahnhoefe;
    }
    public ArrayList<Bahnhof> getBahnhoefe() {
        return bahnhoefe;
    }


}
