package com.example.tdspring.service;

import com.example.tdspring.model.PersonnePhysique;

import java.util.List;

public interface PersonnePhysiqueService {

    public List<PersonnePhysique> getAllPersonnePhysique();

    public PersonnePhysique persistPersonnePhysique(PersonnePhysique personnePhysique);

    public PersonnePhysique getPersonnePhysiqueById(Long id);

    public PersonnePhysique updatePersonnePhysique(PersonnePhysique personnePhysique);

    public void deletePersonnePhysiqueById(Long id);

}
