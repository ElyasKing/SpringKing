package com.example.tdspring.controller;

import com.example.tdspring.model.ConditionsGenerales;
import com.example.tdspring.service.ConditionsGeneralesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConditionsGeneralesController {

    private ConditionsGeneralesService conditionsGeneralesService;

    @Autowired

    public ConditionsGeneralesController(ConditionsGeneralesService conditionsGeneralesService) {

        this.conditionsGeneralesService = conditionsGeneralesService;

    }

    @GetMapping("/conditionsGenerales")

    public String listConditionsGenerales(Model model) {

        model.addAttribute("listeConditionsGenerales", conditionsGeneralesService.getAllConditionsGenerales());

        return "LesConditionsGenerales/conditionsGenerales";

    }

    // Ajout et modification de conditions générales

// On définit donc deux routes. Une route sans paramètre pour la création de conditions générales

// Une route qui passe l'id de conditions générales à modifier

// On en profite pour indiquer que le paramètre Long id peut être null (dans le cas de la création)

    @GetMapping(value = {"/conditionsGenerales/edit", "/conditionsGenerales/edit/{id}"})

    public String editConditionsGeneralesForm(@PathVariable(required = false) Long id, Model model) {

        ConditionsGenerales cg;

        if (id == null) {

// Si on n'a pas récupéré d'attribut id de la page HTML c'est que le client souhaite

// créer de nouvelles conditions générales

            cg = new ConditionsGenerales();

        } else {

// On récupère l'id de la condition générale qui est passée dans l'URL

// On poste l'objet conditionsGenerales pour cet id à la page edit_conditionsGenerales

            cg = conditionsGeneralesService.getConditionsGeneralesById(id);

        }

// On ajoute cg au modèle de la page

// On pourra accéder à une variable de nom conditionsGenerales depuis le template HTML

        model.addAttribute("conditionsGenerales", cg);

// on appelle de moteur de template pour la construction de la page suivante

// qui va afficher les champs à créer/modifier pour les conditions générales

// Nous indiquons ici le chemin vers le template à charger

        return "LesConditionsGenerales/edit_conditionsGenerales";

    }

// Route appelée depuis la page edit_conditionsGenerales.html

// une route sans paramètre

    @PostMapping("/conditionsGenerales/save/")

    public String editConditionsGenerales(@ModelAttribute("conditionsGenerales") ConditionsGenerales conditionsGenerales, Model model) {

        conditionsGeneralesService.updateConditionsGenerales(conditionsGenerales);

        return "redirect:/conditionsGenerales";
    }

    @GetMapping("/conditionsGenerales/delete/{id}")
    public String deleteConditionsGenerales(@PathVariable Long id)
    {
        conditionsGeneralesService.deleteConditionsGeneralesById(id);
        return "redirect:/conditionsGenerales";
    }
}
