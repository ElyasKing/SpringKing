package com.example.tdspring.service;


import com.example.tdspring.model.Operation;
import com.example.tdspring.model.ProduitBancaire;

import java.util.List;

public interface ProduitBancaireService {

    public List<ProduitBancaire> getAllProduitBancaire();

    public ProduitBancaire persistProduitBancaire(ProduitBancaire produitBancaire);

    public ProduitBancaire getProduitBancaireById(Long id);

    public ProduitBancaire updateProduitBancaire(ProduitBancaire produitBancaire);

    public void deleteProduitBancaireById(Long id);

    List<Operation> getLast5Operation(Long id);

    ProduitBancaire virement(Long idEnvoyeur, Long idReceveur, Operation infosVirement);

    List<ProduitBancaire> getProduitBancaireByNumeroCompteSartsWithIgnoreCase(String numeroComptePartiel);

    ProduitBancaire getProduitBancaireByNomCompte(String nomCompte);
}
