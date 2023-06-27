package com.example.tdspring.service;

import com.example.tdspring.model.ClientBancaire;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientBancaireService {

    public List<ClientBancaire> getAllClientBancaire();

    public ClientBancaire persistClientBancaire(ClientBancaire clientBancaire);

    public ClientBancaire getClientBancaireById(Long id);

    public ClientBancaire updateClientBancaire(ClientBancaire clientBancaire);

    public void deleteClientBancaireById(Long id);

    public ClientBancaire getCompte(Long id);


}
