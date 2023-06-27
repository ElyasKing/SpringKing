package com.example.tdspring.service.impl;

import ch.qos.logback.core.net.server.Client;
import com.example.tdspring.model.ClientBancaire;
import com.example.tdspring.model.ConditionsGenerales;
import com.example.tdspring.repository.ClientBancaireRepository;
import com.example.tdspring.repository.ConditionsGeneralesRepository;
import com.example.tdspring.service.ClientBancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientBancaireServiceImpl implements ClientBancaireService {

    private ClientBancaireRepository clientBancaireRepository;

    @Autowired

    public ClientBancaireServiceImpl(ClientBancaireRepository clientBancaireRepository) {

        this.clientBancaireRepository = clientBancaireRepository;

    }

    @Override

    public List<ClientBancaire> getAllClientBancaire()

    {

        return clientBancaireRepository.findAll();

    }

    @Override

    public ClientBancaire persistClientBancaire(ClientBancaire clientBancaire)

    {

        return clientBancaireRepository.save(clientBancaire);

    }

    @Override

    public ClientBancaire getClientBancaireById(Long id)

    {

        return clientBancaireRepository.findById(id).orElseThrow();

    }

    @Override

    public ClientBancaire updateClientBancaire(ClientBancaire clientBancaire)

    {

        return clientBancaireRepository.save(clientBancaire);

    }

    @Override

    public void deleteClientBancaireById(Long id)

    {

        clientBancaireRepository.findById(id).orElseThrow();

        clientBancaireRepository.deleteById(id);

    }

    @Override
    public ClientBancaire getCompte(Long id)
    {
        return clientBancaireRepository.getCompte(id);
    }

}
