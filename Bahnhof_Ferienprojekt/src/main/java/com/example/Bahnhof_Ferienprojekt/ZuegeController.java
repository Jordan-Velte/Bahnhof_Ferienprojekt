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
    ArrayList<String> modelle;
    


    public ZuegeController(){
        setStandardpersonenzuege(new ArrayList<StandardPersonenZug>());
        setZuege(new ArrayList<Zug>());
        createDemoData();
        createModelle();
    }

    //Demodaten
    private void createDemoData(){
        StandardPersonenZug spz1 = new StandardPersonenZug("Intercity 1", "Deutsche Bahn", 200, 8, "26.09.1971", 703);
        StandardPersonenZug spz2 = new StandardPersonenZug("Alstom Coradia Continental", "Deutsche Bahn", 160, 4, "01.12.2008", 450);
        getStandardpersonenzuege().add(spz1);
        getStandardpersonenzuege().add(spz2);
        getZuege().add(spz1);
        getZuege().add(spz2);
    }

    //Demodaten Modelle
    private void createModelle(){
        getModelle().add("Intercity 1");
        getModelle().add("Alstom Coradia Continental");
    }

    @GetMapping("/standardpersonenzuege")
    public String standardpersonenzuege(@RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        model.addAttribute("activePage", "standardpersonenzuege");
        model.addAttribute("standardpersonenzuege", getStandardpersonenzuege());
        model.addAttribute("modelle", getModelle());
        return "index.html";
    }

    @RequestMapping("/delstandardpersonenzug")
    public String delstandardpersonenzug (@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        getStandardpersonenzuege().remove(id);
        return "redirect:/standardpersonenzuege";
    }

    @RequestMapping("/changestandardpersonenzug")
    public String changestandardpersonenzug(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changestandardpersonenzug") String activePage, Model model){
        model.addAttribute("standardpersonenzug", getStandardpersonenzuege().get(id));
        model.addAttribute("standardpersonenzugid", id);
        model.addAttribute("activePage", "standardpersonenzugUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatestandardpersonenzug")
    public String updatestandardpersonenzug(@RequestParam(name="standardpersonenzugId", required = true, defaultValue = "null") int standardpersonenzugId, @RequestParam(name="standardpersonenzugModell", required = true, defaultValue = "null") String standardpersonenzugModell,@RequestParam(name="standardpersonenzugBetreiber", required = true, defaultValue = "null") String standardpersonenzugBetreiber, @RequestParam(name="standardpersonenzugDurchschnittsgeschwindigkeit", required = true, defaultValue = "null") int standardpersonenzugDurchschnittsgeschwindigkeit, @RequestParam(name="standardpersonenzugWagonanzahl", required = true, defaultValue = "null") int standardpersonenzugWagonanzahl, @RequestParam(name="standardpersonenzugZulassungsdatum", required = true, defaultValue = "null") String standardpersonenzugZulassungsdatum, @RequestParam(name="standardpersonenzugMaxpersonenladung", required = true, defaultValue = "null") int standardpersonenzugMaxpersonenladung, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        getStandardpersonenzuege().get(standardpersonenzugId).setModell(standardpersonenzugModell);
        getStandardpersonenzuege().get(standardpersonenzugId).setBetreiber(standardpersonenzugBetreiber);
        getStandardpersonenzuege().get(standardpersonenzugId).setDurchschnittsgeschwindigkeit(standardpersonenzugDurchschnittsgeschwindigkeit);
        getStandardpersonenzuege().get(standardpersonenzugId).setWagonanzahl(standardpersonenzugWagonanzahl);
        getStandardpersonenzuege().get(standardpersonenzugId).setZulassungsdatum(standardpersonenzugZulassungsdatum);
        getStandardpersonenzuege().get(standardpersonenzugId).setMaxpersonenladung(standardpersonenzugMaxpersonenladung);
        return "redirect:/standardpersonenzuege";
    }
    
    @RequestMapping("/addstandardpersonenzug")
    public String addstandardpersonenzug(@RequestParam(name="standardpersonenzugModell", required = true, defaultValue = "null") String standardpersonenzugModell,@RequestParam(name="standardpersonenzugBetreiber", required = true, defaultValue = "null") String standardpersonenzugBetreiber, @RequestParam(name="standardpersonenzugDurchschnittsgeschwindigkeit", required = true, defaultValue = "null") int standardpersonenzugDurchschnittsgeschwindigkeit, @RequestParam(name="standardpersonenzugWagonanzahl", required = true, defaultValue = "null") int standardpersonenzugWagonanzahl, @RequestParam(name="standardpersonenzugZulassungsdatum", required = true, defaultValue = "null") String standardpersonenzugZulassungsdatum, @RequestParam(name="standardpersonenzugMaxpersonenladung", required = true, defaultValue = "null") int standardpersonenzugMaxpersonenladung, @RequestParam(name="activePage", required = false, defaultValue = "standardpersonenzuege") String activePage, Model model){
        StandardPersonenZug spz = new StandardPersonenZug(standardpersonenzugModell, standardpersonenzugBetreiber, standardpersonenzugDurchschnittsgeschwindigkeit, standardpersonenzugWagonanzahl, standardpersonenzugZulassungsdatum, standardpersonenzugMaxpersonenladung);
        getStandardpersonenzuege().add(spz);
        getZuege().add(spz);
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
    public void setModelle(ArrayList<String> modelle) {
        this.modelle = modelle;
    }
    public ArrayList<String> getModelle() {
        return modelle;
    }



}
