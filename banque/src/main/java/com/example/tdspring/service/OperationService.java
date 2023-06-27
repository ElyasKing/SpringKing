package com.example.tdspring.service;


import com.example.tdspring.model.Operation;

import java.util.List;

public interface OperationService {

    public List<Operation> getAllOperation();

    public Operation persistOperation(Operation operation);

    public Operation getOperationById(Long id);

    public Operation updateOperation(Operation operation);

    public void deleteOperationById(Long id);

    List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long produitBancaireId);
    List<Operation> findAllByProduitBancaireNomCompteOrderByDateOperationDesc(String produitBancaireId);
}
