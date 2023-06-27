package com.example.tdspring.service.impl;

import com.example.tdspring.model.Operation;
import com.example.tdspring.model.ProduitBancaire;
import com.example.tdspring.repository.OperationRepository;
import com.example.tdspring.repository.ProduitBancaireRepository;
import com.example.tdspring.service.ProduitBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitBancaireServiceImpl implements ProduitBancaireService {

    private ProduitBancaireRepository produitBancaireRepository;
    private OperationRepository operationRepository;

    @Autowired

    public ProduitBancaireServiceImpl(ProduitBancaireRepository produitBancaireRepository, OperationRepository operationRepository) {

        this.produitBancaireRepository = produitBancaireRepository;

        this.operationRepository = operationRepository;
    }

    @Override

    public List<ProduitBancaire> getAllProduitBancaire() {

        return produitBancaireRepository.findAll();

    }

    @Override

    public ProduitBancaire persistProduitBancaire(ProduitBancaire produitBancaire) {

        return produitBancaireRepository.save(produitBancaire);

    }

    @Override

    public ProduitBancaire getProduitBancaireById(Long id) {

        return produitBancaireRepository.findById(id).orElseThrow();

    }

    @Override

    public ProduitBancaire updateProduitBancaire(ProduitBancaire produitBancaire) {

        return produitBancaireRepository.save(produitBancaire);

    }

    @Override

    public void deleteProduitBancaireById(Long id) {

        produitBancaireRepository.findById(id).orElseThrow();

        produitBancaireRepository.deleteById(id);

    }

    @Override
    public List<Operation> getLast5Operation(Long id) {

        return produitBancaireRepository.getLast5Operations(id);
    }

    @Override
    public ProduitBancaire virement(Long idEnvoyeur, Long idReceveur, Operation infosVirement) {

        Optional<ProduitBancaire> optPBEnvoyeur = produitBancaireRepository.findById(idEnvoyeur);
        Optional<ProduitBancaire> optPBReceveur = produitBancaireRepository.findById(idReceveur);

        if (optPBEnvoyeur.isEmpty() || optPBReceveur.isEmpty()) {
            return null;
        }

        ProduitBancaire pbEnvoyeur = optPBEnvoyeur.get();
        ProduitBancaire pbReceveur = optPBReceveur.get();


        if (pbEnvoyeur.getSolde_courant() < infosVirement.getMontant()) {
            return null;
        }

        pbReceveur.setSolde_courant(pbReceveur.getSolde_courant() + infosVirement.getMontant());
        pbEnvoyeur.setSolde_courant(pbEnvoyeur.getSolde_courant() - infosVirement.getMontant());

        Operation operationReceveur = new Operation(infosVirement.getDateOperation(), infosVirement.getMontant(), infosVirement.getType(), infosVirement.getLibelle(), pbReceveur);
        Operation operationEnvoyeur = new Operation(infosVirement.getDateOperation(), -infosVirement.getMontant(), infosVirement.getType(), infosVirement.getLibelle(), pbEnvoyeur);

        operationRepository.saveAll(List.of(operationEnvoyeur, operationReceveur));

        produitBancaireRepository.save(pbEnvoyeur);
        produitBancaireRepository.save(pbReceveur);


        return pbEnvoyeur;
    }

    @Override
    public List<ProduitBancaire> getProduitBancaireByNumeroCompteSartsWithIgnoreCase(String numeroComptePartiel) {
        return produitBancaireRepository.findByNomCompteStartingWith(numeroComptePartiel);
    }

    @Override
    public ProduitBancaire getProduitBancaireByNomCompte(String nomCompte) {
        return produitBancaireRepository.findFirstByNomCompteIgnoreCase(nomCompte);
    }
}
