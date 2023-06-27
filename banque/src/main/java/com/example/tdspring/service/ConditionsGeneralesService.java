package com.example.tdspring.service;

import com.example.tdspring.model.ConditionsGenerales;

import java.util.List;

public interface ConditionsGeneralesService {

    public List<ConditionsGenerales> getAllConditionsGenerales();

    public ConditionsGenerales persistConditionsGenerales(ConditionsGenerales conditionsGenerales);

    public ConditionsGenerales getConditionsGeneralesById(Long id);

    public ConditionsGenerales updateConditionsGenerales(ConditionsGenerales conditionsGenerales);

    public void deleteConditionsGeneralesById(Long id);

}
