//IMPORTS
package com.example.Bahnhof_Ferienprojekt;

import java.util.ArrayList;

import com.example.Bahnhof_Ferienprojekt.models.Personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Bahnhof_Ferienprojekt.models.Passagier;
import com.example.Bahnhof_Ferienprojekt.models.Person;

//Decorator! --> legt fest, dass Klasse ein Controller ist!
@Controller
public class PersonController {
    //ArrayList-Deklaration
    ArrayList<Person> personen;
    ArrayList<Passagier> passagiere;
    ArrayList<Personal> personal;

    public PersonController(){
        setPersonen(new ArrayList<Person>());
        setPassagiere(new ArrayList<Passagier>());
        setPersonal(new ArrayList<Personal>());
        createDemoData();
        
    }

    //Demodaten
    private void createDemoData(){
        Passagier pr1 = new Passagier("Napoleon", "Fischer", 9);
        Passagier pr2 = new Passagier("Major", "Kunze", 7);
        Passagier pr3 = new Passagier("Guste", "Daimchen", 4);
        getPassagiere().add(pr1);
        getPassagiere().add(pr2);
        getPassagiere().add(pr3);
        getPersonen().add(pr1);
        getPersonen().add(pr2);
        getPersonen().add(pr3);
        Personal pl1 = new Personal("Diederich", "Heßling", 13);
        Personal pl2 = new Personal("Wolfgang", "Buck", 12);
        Personal pl3 = new Personal("Assessor", "Jadassohn", 10);
        getPersonal().add(pl1);
        getPersonal().add(pl2);
        getPersonal().add(pl3);
        getPersonen().add(pl1);
        getPersonen().add(pl2);
        getPersonen().add(pl3);
        
    }

    //PASSAGIERE
    @GetMapping("/passagiere")
    public String passagiere(@RequestParam(name="activePage", required = false, defaultValue = "passagiere") String activePage, Model model){
        model.addAttribute("activePage", "passagiere");
        model.addAttribute("passagiere", getPassagiere());
        return "index.html";
    }

    @RequestMapping("/delpassagier")
    public String delpassagier (@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "passagiere") String activePage, Model model){
        getPassagiere().remove(id);
        return "redirect:/passagiere";
    }

    @RequestMapping("/changepassagier")
    public String changepassagier(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changepassagier") String activePage, Model model){
        model.addAttribute("passagier", getPassagiere().get(id));
        model.addAttribute("passagierid", id);
        model.addAttribute("activePage", "passagierUpdate");
        return "index.html";
    }

    @RequestMapping("/updatepassagier")
    public String updatepassagier (@RequestParam(name="passagierId", required = true, defaultValue = "null") int passagierId, @RequestParam(name="passagierVorname", required = true, defaultValue = "null") String passagierVorname,@RequestParam(name="passagierNachname", required = true, defaultValue = "null") String passagierNachname, @RequestParam(name="passagierKundennummer", required = true, defaultValue = "null") int passagierKundennummer,  @RequestParam(name="activePage", required = false, defaultValue = "passagiere") String activePage, Model model){
        getPassagiere().get(passagierId).setVorname(passagierVorname);
        getPassagiere().get(passagierId).setNachname(passagierNachname);
        getPassagiere().get(passagierId).setKundennummer(passagierKundennummer);
        return "redirect:/passagiere";
    }

    @RequestMapping("/addpassagier")
    public String addpassagier(@RequestParam(name="passagierVorname", required = true, defaultValue = "null") String passagierVorname,@RequestParam(name="passagierNachname", required = true, defaultValue = "null") String passagierNachname, @RequestParam(name="passagierKundennummer", required = true, defaultValue = "null") int passagierKundennummer,  @RequestParam(name="activePage", required = false, defaultValue = "passagiere") String activePage, Model model){
        Passagier pr = new Passagier(passagierVorname, passagierNachname, passagierKundennummer);
        getPassagiere().add(pr);
        getPersonen().add(pr);
        return "redirect:/passagiere";
       
    }

    //PERSONAL
    @GetMapping("/personal")
    public String personal(@RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        model.addAttribute("activePage", "personal");
        model.addAttribute("personal", getPersonal());
        return "index.html";
    }

    @RequestMapping("/delpersonal")
    public String delpersonal (@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        getPersonal().remove(id);
        return "redirect:/personal";
    }

    @RequestMapping("/changepersonal")
    public String changepersonal(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changepersonal") String activePage, Model model){
        model.addAttribute("personal", getPersonal().get(id));
        model.addAttribute("personalid", id);
        model.addAttribute("activePage", "personalUpdate");
        return "index.html";
    }

    @RequestMapping("/updatepersonal")
    public String updatepersonal (@RequestParam(name="personalId", required = true, defaultValue = "null") int personalId, @RequestParam(name="personalVorname", required = true, defaultValue = "null") String personalVorname,
    @RequestParam(name="personalNachname", required = true, defaultValue = "null") String personalNachname, @RequestParam(name="personalPersonalnummer", required = true, defaultValue = "null") int personalPersonalnummer,  @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        getPersonal().get(personalId).setVorname(personalVorname);
        getPersonal().get(personalId).setNachname(personalNachname);
        getPersonal().get(personalId).setPersonalnummer(personalPersonalnummer);
        return "redirect:/personal";
    }

    @RequestMapping("/addpersonal")
    public String addpersonal(@RequestParam(name="personalVorname", required = true, defaultValue = "null") String personalVorname,@RequestParam(name="personalNachname", required = true, defaultValue = "null") String personalNachname, @RequestParam(name="personalPersonalnummer", required = true, defaultValue = "null") int personalPersonalnummer,  @RequestParam(name="activePage", required = false, defaultValue = "personal") String activePage, Model model){
        Personal pl = new Personal(personalVorname, personalNachname, personalPersonalnummer);
        getPersonal().add(pl);
        getPersonen().add(pl);
        return "redirect:/personal";
       
    }

    //Setter und Getter
    public void setPersonal(ArrayList<Personal> personal) {
        this.personal = personal;
    }
    public void setPassagiere(ArrayList<Passagier> passagiere) {
        this.passagiere = passagiere;
    }
    public void setPersonen(ArrayList<Person> personen) {
        this.personen = personen;
    }
    public ArrayList<Personal> getPersonal() {
        return personal;
    }
    public ArrayList<Passagier> getPassagiere() {
        return passagiere;
    }
    public ArrayList<Person> getPersonen() {
        return personen;
    }




    
}
