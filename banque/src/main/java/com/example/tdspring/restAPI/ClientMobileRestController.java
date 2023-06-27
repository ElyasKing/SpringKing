package com.example.tdspring.restAPI;

import com.example.tdspring.model.ClientBancaire;
import com.example.tdspring.model.Operation;
import com.example.tdspring.model.ProduitBancaire;
import com.example.tdspring.service.ClientBancaireService;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banque_rest/mobile")
public class ClientMobileRestController {

    private ProduitBancaireService produitBancaireService;
    private ClientBancaireService clientBancaireService;

    @Autowired
    public ClientMobileRestController(ProduitBancaireService produitBancaireService, ClientBancaireService clientBancaireService)
    {
        this.produitBancaireService = produitBancaireService;
        this.clientBancaireService = clientBancaireService;
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Operation>> getLast5Operations(@PathVariable("id") long id)
    {
        List<Operation> dernieresOperations = produitBancaireService.getLast5Operation(id);

        return new ResponseEntity<List<Operation>>(dernieresOperations, HttpStatus.CREATED);
    }

    @GetMapping("compte/{id}")
    public ResponseEntity<ClientBancaire> getCompte(@PathVariable("id") Long id)
    {
        ClientBancaire cb =clientBancaireService.getCompte(id);

        return new ResponseEntity<>(cb, HttpStatus.OK);
    }

    @PutMapping("virement/{idEnvoyeur}/{idReceveur}")
    public ResponseEntity<ProduitBancaire> virement(@PathVariable("idEnvoyeur") Long idEnvoyeur, @PathVariable("idReceveur") Long idReceveur, @RequestBody Operation infosVirement)
    {

        ProduitBancaire pb = produitBancaireService.virement(idEnvoyeur, idReceveur, infosVirement);
        if (pb == null)
        {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(pb, HttpStatus.OK);
    }
}
