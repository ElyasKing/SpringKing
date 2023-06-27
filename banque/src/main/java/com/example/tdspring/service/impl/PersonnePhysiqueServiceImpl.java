package com.example.tdspring.service.impl;

import com.example.tdspring.model.PersonnePhysique;
import com.example.tdspring.repository.PersonneRepository;
import com.example.tdspring.service.PersonnePhysiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {


    private PersonneRepository<PersonnePhysique> personnePhysiqueRepository;


    @Autowired

    public PersonnePhysiqueServiceImpl(PersonneRepository<PersonnePhysique> personnePhysiqueRepository) {

        this.personnePhysiqueRepository = personnePhysiqueRepository;

    }

    @Override
    public List<PersonnePhysique> getAllPersonnePhysique() {
        return personnePhysiqueRepository.findAll();
    }

    @Override
    public PersonnePhysique persistPersonnePhysique(PersonnePhysique PersonnePhysique) {
        return personnePhysiqueRepository.save(PersonnePhysique);
    }

    @Override
    public PersonnePhysique getPersonnePhysiqueById(Long id) {
        return personnePhysiqueRepository.findById(id).orElseThrow();
    }

    @Override
    public PersonnePhysique updatePersonnePhysique(PersonnePhysique PersonnePhysique) {
        return personnePhysiqueRepository.save(PersonnePhysique);
    }

    @Override
    public void deletePersonnePhysiqueById(Long id) {
        personnePhysiqueRepository.deleteById(id);


    }
}


