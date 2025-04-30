package com.arleyfllh.population.service;

import com.arleyfllh.population.model.ProvincePopulation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProvincePopulationService {
    List<ProvincePopulation> getAll();

    List<ProvincePopulation> findByProvince(String province);

    ProvincePopulation findById(Long id);

    List<ProvincePopulation> findByCode(Long code);

    List<ProvincePopulation> sortByPopulation();

    Long totalPopulation();

    Page<ProvincePopulation> findByPagination(int page, int size);
}
