package com.arleyfllh.population.service;

import com.arleyfllh.population.exception.ResourceNotFoundException;
import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.model.ProvincePopulationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProvincePopulationService {
    List<ProvincePopulation> getAll();

    Page<ProvincePopulation> getAllByPagination(Pageable pageable);

    Page<ProvincePopulation> search(String province, Long code, Pageable pageable);

    ProvincePopulation findById(Long id);

    List<ProvincePopulation> sortByPopulation(String sort);

    Long totalPopulation();

    ProvincePopulation create(ProvincePopulation request);

    void delete(Long id) throws ResourceNotFoundException;

    ProvincePopulation update(Long id, ProvincePopulationDto dto);
}
