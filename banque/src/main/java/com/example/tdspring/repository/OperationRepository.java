package com.example.tdspring.repository;

import com.example.tdspring.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long>
{


    List<Operation> findAllByProduitBancaireIdOrderByDateOperationDesc(Long produitBancaireId);
    List<Operation> findAllByProduitBancaireNomCompte(@Param("nomCompte") String nomCompte);
}
