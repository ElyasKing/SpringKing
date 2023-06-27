package com.example.tdspring.service.impl;

import com.example.tdspring.model.TypePersonneMorale;
import com.example.tdspring.repository.TypePersonneMoraleRepository;
import com.example.tdspring.service.TypePersonneMoraleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePersonneMoraleServiceImpl implements TypePersonneMoraleService {

    private TypePersonneMoraleRepository typePersonneMoraleRepository;

    @Autowired

    public TypePersonneMoraleServiceImpl(TypePersonneMoraleRepository typePersonneMoraleRepository) {

        this.typePersonneMoraleRepository = typePersonneMoraleRepository;

    }

    @Override

    public List<TypePersonneMorale> getAllTypePersonneMorale() {

        return typePersonneMoraleRepository.findAll();

    }

    @Override

    public TypePersonneMorale persistTypePersonneMorale(TypePersonneMorale typePersonneMorale) {

        return typePersonneMoraleRepository.save(typePersonneMorale);

    }

    @Override

    public TypePersonneMorale getTypePersonneMoraleById(Long id) {

        return typePersonneMoraleRepository.findById(id).orElseThrow();

    }

    @Override

    public TypePersonneMorale updateTypePersonneMorale(TypePersonneMorale typePersonneMorale) {

        return typePersonneMoraleRepository.save(typePersonneMorale);

    }

    @Override

    public void deleteTypePersonneMoraleById(Long id) {

        typePersonneMoraleRepository.findById(id).orElseThrow();

        typePersonneMoraleRepository.deleteById(id);

    }

}
