package com.example.tdspring.service;

import com.example.tdspring.model.TypePersonneMorale;

import java.util.List;

public interface TypePersonneMoraleService {

    public List<TypePersonneMorale> getAllTypePersonneMorale();

    public TypePersonneMorale persistTypePersonneMorale(TypePersonneMorale typePersonneMorale);

    public TypePersonneMorale getTypePersonneMoraleById(Long id);

    public TypePersonneMorale updateTypePersonneMorale(TypePersonneMorale typePersonneMorale);

    public void deleteTypePersonneMoraleById(Long id);

}
