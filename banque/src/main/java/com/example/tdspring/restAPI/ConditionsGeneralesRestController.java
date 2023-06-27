package com.example.tdspring.restAPI;

import com.example.tdspring.model.ConditionsGenerales;
import com.example.tdspring.service.ConditionsGeneralesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/banque_rest/ConditionsGenerales")
public class ConditionsGeneralesRestController {

    private ConditionsGeneralesService conditionsGeneralesService;

    @Autowired

    public ConditionsGeneralesRestController(ConditionsGeneralesService conditionsGeneralesService) {

        this.conditionsGeneralesService = conditionsGeneralesService;

    }

    // exemple URL : http://localhost:8080/banque_rest/ConditionsGenerales

    @GetMapping

    public ResponseEntity<List<ConditionsGenerales>> getAllConditionsGenerales() {

        List<ConditionsGenerales> listecg = conditionsGeneralesService.getAllConditionsGenerales();

        return new ResponseEntity<List<ConditionsGenerales>>(listecg, HttpStatus.CREATED);

    }

// exemple URL : http://localhost:8080/banque_rest/ConditionsGenerales/2

    @GetMapping("{id}")
    public ResponseEntity<ConditionsGenerales> getConditionsGeneralesById(@PathVariable("id") long idConditionsGenerales) {

        ConditionsGenerales cg = conditionsGeneralesService.getConditionsGeneralesById(idConditionsGenerales);

        return new ResponseEntity<ConditionsGenerales>(cg, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<ConditionsGenerales> addConditionsGenerales(@RequestBody ConditionsGenerales conditionsGenerales) {

        ConditionsGenerales cg = conditionsGeneralesService.persistConditionsGenerales(conditionsGenerales);

        return new ResponseEntity<ConditionsGenerales>(cg, HttpStatus.CREATED);

    }

    // exemple URL : http://localhost:8080/banque_rest/ConditionsGenerales/update

    @PutMapping("/update")

    public ResponseEntity<ConditionsGenerales> updateConditionsGenerales(@RequestBody ConditionsGenerales conditionsGenerales) {

        ConditionsGenerales cg = conditionsGeneralesService.updateConditionsGenerales(conditionsGenerales);

        return new ResponseEntity<ConditionsGenerales>(cg, HttpStatus.OK);

    }

    // exemple URL : http://localhost:8080/banque_rest/ConditionsGenerales/delete/2

    @DeleteMapping("/delete/{id}")

    public ResponseEntity<String> deleteConditionsGenerales(@PathVariable("id") long idConditionsGenerales)

    {

        conditionsGeneralesService.deleteConditionsGeneralesById(idConditionsGenerales);

        return new ResponseEntity<String>("Suppression OK",HttpStatus.OK);

    }

}
