package com.example.tdspring.repository;

import com.example.tdspring.model.ClientBancaire;
import com.example.tdspring.model.Operation;
import com.example.tdspring.model.Personne;
import com.example.tdspring.model.ProduitBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.ArrayList;

public interface ProduitBancaireRepository  extends JpaRepository<ProduitBancaire, Long>
{
    @Query("SELECT o FROM Operation o INNER JOIN ProduitBancaire p on p.id = o.produitBancaire.id WHERE p.id = :id AND p.conditionsGenerales.typeProduit = 'compte chèque' ORDER BY o.dateOperation desc LIMIT 5")
    ArrayList<Operation> getLast5Operations(@Param("id")Long id);

    List<ProduitBancaire> findByNomCompteStartingWith(@Param("numCompte") String numCompte);
    ProduitBancaire findFirstByNomCompteIgnoreCase(@Param("numCompte") String nomCompte);
}
