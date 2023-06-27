package com.example.tdspring.service.impl;

import com.example.tdspring.model.Operation;
import com.example.tdspring.repository.OperationRepository;
import com.example.tdspring.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private OperationRepository operationRepository;

    @Autowired
    public OperationServiceImpl(OperationRepository operationRepository) {

        this.operationRepository = operationRepository;

    }

    @Override

    public List<Operation> getAllOperation() {

        return operationRepository.findAll();

    }

    @Override

    public Operation persistOperation(Operation operation) {

        return operationRepository.save(operation);

    }

    @Override

    public Operation getOperationById(Long id) {

        return operationRepository.findById(id).orElseThrow();

    }

    @Override

    public Operation updateOperation(Operation operation) {

        return operationRepository.save(operation);

    }

    @Override
    public void deleteOperationById(Long id) {

        Operation op = operationRepository.findById(id).orElseThrow();
        operationRepository.delete(op);

    }

    @Override
    public List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long produitBancaireId) {
        return operationRepository.findAllByProduitBancaireIdOrderByDateOperationDesc(produitBancaireId);
    }

    @Override
    public List<Operation> findAllByProduitBancaireNomCompteOrderByDateOperationDesc(String produitBancaireNomCompte) {
        return operationRepository.findAllByProduitBancaireNomCompte(produitBancaireNomCompte);
    }

}
