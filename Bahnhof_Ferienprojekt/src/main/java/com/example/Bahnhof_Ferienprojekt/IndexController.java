//IMPORTS
package com.example.Bahnhof_Ferienprojekt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Decorator! --> legt fest, dass Klasse ein Controller ist!
@Controller
public class IndexController {
    //Mapping, damit dieser Controller reagiert wenn wir auf diese Webseite gehen! Slash steht für Wurzelverzeichnis dieser Homepage!
    //Mappen die URL (Slash) an die Methode index(), welche uns die index.html (aus templates: view!) zurückgibt --> Wurzelpunkt der Seite
    @GetMapping("/")
    //Model model = dependecy injection
    //Request = Wert der Methode übergeben wird
    //Variable activePage ist der Wert --> entweder dass was über request reinkommt, oder home!
    //Parameter activePage wird übergeben, dort steht home drin
    public String index(@RequestParam(name="activePage", required = false, defaultValue = "home") String activePage, Model model){
        //home wird über Methode in Variable activePage gespeichert
        model.addAttribute("activePage", activePage);
        return "index.html";
    }
}
