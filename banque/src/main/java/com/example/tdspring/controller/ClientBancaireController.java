package com.example.tdspring.controller;

import com.example.tdspring.model.Personne;
import com.example.tdspring.model.ProduitBancaire;
import com.example.tdspring.service.ClientBancaireService;
import com.example.tdspring.service.PersonneMoraleService;
import com.example.tdspring.service.PersonnePhysiqueService;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientBancaireController {

    private ClientBancaireService clientBancaireService;
    private PersonnePhysiqueService personnePhysiqueService;
    private PersonneMoraleService personneMoraleService;

    private List<Personne> listePersonnes;

    @Autowired
    public ClientBancaireController(ClientBancaireService clientBancaireService, PersonnePhysiqueService personnePhysiqueService, PersonneMoraleService personneMoraleService) {

        this.clientBancaireService = clientBancaireService;
        this.personnePhysiqueService = personnePhysiqueService;

        this.personneMoraleService = personneMoraleService;

        listePersonnes = new ArrayList<>();
    }

    @GetMapping("/clients")
    public String listConditionsGenerales(Model model) {
        model.addAttribute("listeClients", clientBancaireService.getAllClientBancaire());
        return "LesClientsBancaires/clientBancaire";

    }

    @GetMapping("/clientBancaire/delete/{id}")
    public String deleteClientBancaire(@PathVariable Long id) {
        clientBancaireService.deleteClientBancaireById(id);
        return "redirect:/clients";
    }

    @ModelAttribute("listePersonne")
    public List<Personne> getPersonnes() {
        return listePersonnes;
    }

    @GetMapping(value = {"/client/create"})
    public String createClient(Model model) {
        loadAllPersonne();
        return "LesClientsBancaires/create";
    }

    private void loadAllPersonne() {
        var l1 = personnePhysiqueService.getAllPersonnePhysique();
        var l2 = personneMoraleService.getAllPersonneMorale();

        listePersonnes.addAll(l1);
        listePersonnes.addAll(l2);
    }

    @PostMapping("/clientBancaire/save/")
    public String saveClientBancaire(@ModelAttribute("personnes") ArrayList<Long> personneModels) {


        return "bleuh";
    }
}
