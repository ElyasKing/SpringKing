package com.example.tdspring.restAPI;

import com.example.tdspring.model.ConditionsGenerales;
import com.example.tdspring.model.Operation;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/banque_rest/produitbancaire")
public class ProduitBancaireRestController {
    private ProduitBancaireService produitBancaireService;

    @Autowired
    public ProduitBancaireRestController(ProduitBancaireService produitBancaireService)
    {
        this.produitBancaireService = produitBancaireService;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Operation>> getLast5Operations(@PathVariable("id") long id)
    {
        List<Operation> dernieresOperations = produitBancaireService.getLast5Operation(id);

        return new ResponseEntity<List<Operation>>(dernieresOperations, HttpStatus.CREATED);
    }
}
