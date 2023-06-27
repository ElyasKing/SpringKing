package com.example.tdspring.service.impl;

import com.example.tdspring.model.ConditionsGenerales;
import com.example.tdspring.repository.ConditionsGeneralesRepository;
import com.example.tdspring.service.ConditionsGeneralesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ConditionsGeneralesServicesImpl implements ConditionsGeneralesService {

// Dépendance : le Service dépend du Repository

    private ConditionsGeneralesRepository conditionsGeneralesRepository;

// Injection de dépendance par constructeur. Le répository est créé par le framework et passé en paramètre

// dans le constructeur => injection de dépendance

    @Autowired

    public ConditionsGeneralesServicesImpl(ConditionsGeneralesRepository conditionsGeneralesRepository) {

        this.conditionsGeneralesRepository = conditionsGeneralesRepository;

    }

    @Override

    public List<ConditionsGenerales> getAllConditionsGenerales()

    {

        return conditionsGeneralesRepository.findAll();

    }

    @Override

    public ConditionsGenerales persistConditionsGenerales(ConditionsGenerales conditionsGenerales)

    {

        return conditionsGeneralesRepository.save(conditionsGenerales);

    }

    @Override

    public ConditionsGenerales getConditionsGeneralesById(Long id)

    {

        return conditionsGeneralesRepository.findById(id).orElseThrow();

    }

    @Override

    public ConditionsGenerales updateConditionsGenerales(ConditionsGenerales conditionsGenerales)

    {

        return conditionsGeneralesRepository.save(conditionsGenerales);

    }

    @Override

    public void deleteConditionsGeneralesById(Long id)

    {
        conditionsGeneralesRepository.findById(id).orElseThrow();
        conditionsGeneralesRepository.deleteById(id);

    }

}
