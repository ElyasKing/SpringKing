package com.example.tdspring.service;

import com.example.tdspring.model.PersonneMorale;

import java.util.List;

public interface PersonneMoraleService {

    public List<PersonneMorale> getAllPersonneMorale();

    public PersonneMorale persistPersonneMorale(PersonneMorale personneMorale);

    public PersonneMorale getPersonneMoraleById(Long id);

    public PersonneMorale updatePersonneMorale(PersonneMorale personneMorale);

    public void deletePersonneMoraleById(Long id);

}
