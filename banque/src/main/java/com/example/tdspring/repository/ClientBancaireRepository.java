package com.example.tdspring.repository;

import ch.qos.logback.core.net.server.Client;
import com.example.tdspring.model.ClientBancaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ClientBancaireRepository extends JpaRepository<ClientBancaire, Long>
{
    @Query("SELECT cb FROM ClientBancaire cb WHERE cb.id = :id")
    ClientBancaire getCompte(@Param("id") Long id);

}
