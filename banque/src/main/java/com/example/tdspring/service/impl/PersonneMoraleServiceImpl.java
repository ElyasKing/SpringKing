package com.example.tdspring.service.impl;

import com.example.tdspring.model.PersonneMorale;
import com.example.tdspring.repository.PersonneRepository;
import com.example.tdspring.service.PersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneMoraleServiceImpl implements PersonneMoraleService {

    private PersonneRepository<PersonneMorale> personneMoraleRepository;


    @Autowired

    public PersonneMoraleServiceImpl(PersonneRepository<PersonneMorale> personneMoraleRepository) {

        this.personneMoraleRepository = personneMoraleRepository;

    }


    @Override
    public List<PersonneMorale> getAllPersonneMorale() {
        return personneMoraleRepository.findAll();
    }

    @Override
    public PersonneMorale persistPersonneMorale(PersonneMorale personneMorale) {
        return personneMoraleRepository.save(personneMorale);
    }

    @Override
    public PersonneMorale getPersonneMoraleById(Long id) {
        return personneMoraleRepository.findById(id).orElseThrow();
    }

    @Override
    public PersonneMorale updatePersonneMorale(PersonneMorale personneMorale) {
        return personneMoraleRepository.save(personneMorale);
    }

    @Override
    public void deletePersonneMoraleById(Long id) {
        personneMoraleRepository.deleteById(id);


    }
}
