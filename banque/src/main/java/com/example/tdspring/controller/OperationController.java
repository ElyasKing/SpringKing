package com.example.tdspring.controller;

import com.example.tdspring.model.Operation;
import com.example.tdspring.model.Personne;
import com.example.tdspring.model.ProduitBancaire;
import com.example.tdspring.service.OperationService;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OperationController {
    private OperationService operationService;
    private ProduitBancaireService produitBancaireService;
    private ProduitBancaire produitBancaire;
    private List<ProduitBancaire> listeProduitBancaire;
    private List<Operation> listeOperation;
    private Operation operation;

    @Autowired
    public OperationController(OperationService operationService, ProduitBancaireService produitBancaireService) {

        this.operationService = operationService;
        this.produitBancaireService = produitBancaireService;
        this.listeProduitBancaire = this.listeProduitBancaire = produitBancaireService.getAllProduitBancaire();
        this.produitBancaire = null;
        this.listeOperation = null;
        this.operation = null;

    }

    @GetMapping("/operation")
    public String loadPageOperation(Model model) {
        return "LesOperations/operation";
    }

    @ModelAttribute("listeProduitBancaire")
    public List<ProduitBancaire> getListeProduitsBancaire() {
        return listeProduitBancaire;
    }

    @ModelAttribute("listeOperation")
    public List<Operation> getListeOperation() {
        return listeOperation;
    }

    @ModelAttribute("produitBancaire")
    public ProduitBancaire getProduitBancaire() {
        return this.produitBancaire;
    }

    @ModelAttribute("operation")
    public Operation getOperation() {

        return this.operation;

    }

    @PostMapping("/operation/loadOperation")
    public String loadOperation(@ModelAttribute("produitBancaireId") Long produitBancaire) {
        this.produitBancaire = produitBancaireService.getProduitBancaireById(produitBancaire);
        //this.produitBancaire = produitBancaireService.getProduitBancaireByNomCompte(nomCompteProduitBancaire);
        this.listeOperation = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaire);
        //this.listeOperation = operationService.findAllByProduitBancaireNomCompteOrderByDateOperationDesc(nomCompteProduitBancaire);

        return "redirect:/operation";
    }

    @GetMapping(value = {"/operation/edit", "/operation/edit/{id}"})
    public String editOperation(@PathVariable(required = false) Long id, Model model) {

        if (id == null) {

// Création d'un nouveau produit

            this.operation = new Operation();

            this.operation.setProduitBancaire(this.produitBancaire);

        } else {

// Modification d'un produit existant

            this.operation = operationService.getOperationById(id);

        }

// Envoi des données au modèle pour la construction de la page

// edit_operation.html

        model.addAttribute("operation", this.operation);

// Demande de génération de la page suivante

        return "LesOperations/edit_operation";

    }

    @PostMapping("/operation/save/")
    public String saveOperation(@ModelAttribute("operation") Operation operation, Model model) {

        operation.setProduitBancaire(this.operation.getProduitBancaire());

        this.operation = operation;

        operationService.updateOperation(this.operation);

        // Mise à jour de la liste des opérations

        this.listeOperation = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(this.produitBancaire.getId());

        return "redirect:/operation";

    }

    @GetMapping("/operation/delete/{id}")
    public String deleteOperations(@PathVariable Long id) {
        operationService.deleteOperationById(id);
        //return this.loadOperation(this.produitBancaire.getNomCompte());
        return this.loadOperation(this.produitBancaire.getId());
    }

    @GetMapping("/operation/details/{id}")
    public String detailsOperationForm(@PathVariable Long id, Model model) {

        this.operation = operationService.getOperationById(id);
        model.addAttribute("operation", this.operation);
        return "LesOperations/details_operation";

    }

    @RequestMapping(value = "/operation/listeNumeroCompteAutocomplete")
    @ResponseBody
    public List<String> listeNumeroCompteAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "") String numeroComptePartiel) {
        listeProduitBancaire = produitBancaireService.getProduitBancaireByNumeroCompteSartsWithIgnoreCase(numeroComptePartiel);

        List<String> listeNumeroCompte = new ArrayList<>();
        for (ProduitBancaire pb : listeProduitBancaire) {
            if (pb.getNomCompte().startsWith(numeroComptePartiel)) {
                String entree = "";
                entree += pb.getNomCompte() + "(";
                for (Personne p : pb.getClientBancaire().getPersonnes()) {
                    entree += p.nomComplet() + " - ";
                }

                entree += ")";
                listeNumeroCompte.add(entree);
            }
        }

        return listeNumeroCompte;
    }

    @PostMapping("/operation/loadOperationAutocomplete")
    public String loadOperationAutocomplete(@ModelAttribute("numeroCompteAutocomplete") String numeroCompteAutocomplete, Model model) {
        if (numeroCompteAutocomplete.contains("(")) {
            String numeroCompte = numeroCompteAutocomplete.substring(0, numeroCompteAutocomplete.indexOf("("));
            this.produitBancaire = produitBancaireService.getProduitBancaireByNomCompte(numeroCompte);
            if (this.produitBancaire != null) {
                this.listeOperation = operationService.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaire.getId());
            } else {
                System.out.println("Numéro de compte inexistant " + numeroCompte);
            }
        }

        return "redirect:/operation";

    }
}
