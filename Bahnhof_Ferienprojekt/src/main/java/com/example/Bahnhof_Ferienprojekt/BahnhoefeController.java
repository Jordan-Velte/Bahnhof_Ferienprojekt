//IMPORTS
package com.example.Bahnhof_Ferienprojekt;

import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.Bahnhof;

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
        createDemoData();
    }

    //Demodaten
    private void createDemoData(){
        getBahnhoefe().add(new Bahnhof("Hamburg Hauptbahnhof", "Hamburg", 5));
        getBahnhoefe().add(new Bahnhof("Berlin Hauptbahnhof", "Berlin", 4));
        getBahnhoefe().add(new Bahnhof("Hannover Regionalbahnhof", "Hannover", 2));
    }

    @GetMapping("/bahnhoefe")
    public String bahnhoefe(@RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        model.addAttribute("activePage", "bahnhoefe");
        model.addAttribute("bahnhoefe", getBahnhoefe());
        return "index.html";
    }

    @RequestMapping("/delbahnhof")
    public String delbahnhof(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        getBahnhoefe().remove(id);
        return "redirect:/bahnhoefe";
    }

    @RequestMapping("/changebahnhof")
    public String changebahnhof(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changebahnhof") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("bahnhof", getBahnhoefe().get(id));
        model.addAttribute("bahnhofid", id);
        model.addAttribute("activePage", "bahnhofUpdate");
        return "index.html";
    }

    @RequestMapping("/updatebahnhof")
    public String updatebahnhof(@RequestParam(name="bahnhofId", required = true, defaultValue = "null") int bahnhofId, @RequestParam(name="bahnhofName", required = true, defaultValue = "null") String bahnhofName, @RequestParam(name="bahnhofStandort", required = true, defaultValue = "null") String bahnhofStandort, @RequestParam(name="bahnhofAnzahl_Gleise", required = true, defaultValue = "null") int bahnhofAnzahl_Gleise, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        getBahnhoefe().get(bahnhofId).setName(bahnhofName);
        getBahnhoefe().get(bahnhofId).setStandort(bahnhofStandort);
        getBahnhoefe().get(bahnhofId).setAnzahl_Gleise(bahnhofAnzahl_Gleise);
        return "redirect:/bahnhoefe";
    }

    @RequestMapping("/addbahnhof")
    public String addbahnhof(@RequestParam(name="bahnhofName", required = true, defaultValue = "null") String bahnhofName,@RequestParam(name="bahnhofStandort", required = true, defaultValue = "null") String bahnhofStandort, @RequestParam(name="bahnhofAnzahl_Gleise", required = true, defaultValue = "null") int bahnhofAnzahl_Gleise, @RequestParam(name="activePage", required = false, defaultValue = "bahnhoefe") String activePage, Model model){
        getBahnhoefe().add(new Bahnhof(bahnhofName, bahnhofStandort, bahnhofAnzahl_Gleise));
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
