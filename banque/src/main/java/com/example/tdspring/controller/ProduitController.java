package com.example.tdspring.controller;

import com.example.tdspring.model.Operation;
import com.example.tdspring.model.ProduitBancaire;
import com.example.tdspring.service.ClientBancaireService;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProduitController {

    private ClientBancaireService clientBancaireService;
    private ProduitBancaire produitBancaire;
    ProduitBancaireService produitBancaireService;

    @Autowired
    public ProduitController(ClientBancaireService clientBancaireService, ProduitBancaireService produitBancaireService) {

        this.clientBancaireService = clientBancaireService;
        this.produitBancaireService = produitBancaireService;
    }

    @GetMapping("/produits")
    public String listeProduitsBancaires(Model model) {
        model.addAttribute("listeProduits", produitBancaireService.getAllProduitBancaire());
        return "LesProduitsBancaires/produitBancaire";

    }

    @GetMapping("/produit/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        produitBancaireService.deleteProduitBancaireById(id);
        return "redirect:/produits";
    }

    @GetMapping(value = {"/produit/edit/{id}"})
    public String editProduit(@PathVariable(required = false) Long id, Model model) {
        produitBancaire = produitBancaireService.getProduitBancaireById(id);
        model.addAttribute("produitbancaire", produitBancaire);
        return "LesProduitsBancaires/edit";

    }

    @GetMapping(value = {"/produit/create"})
    public String createProduit(@PathVariable(required = false) Long id, Model model) {
        produitBancaire = new ProduitBancaire();
        model.addAttribute("produitbancaire", produitBancaire);
        return "LesProduitsBancaires/create";

    }

    @PostMapping("/produit/save/")
    public String saveOperation(@ModelAttribute("produitbancaire") ProduitBancaire produitBancaire, Model model) {
        this.produitBancaire = produitBancaire;
        produitBancaireService.updateProduitBancaire(this.produitBancaire);
        return "redirect:/produits";

    }


}
