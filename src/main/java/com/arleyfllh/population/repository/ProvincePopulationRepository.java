package com.arleyfllh.population.repository;

import com.arleyfllh.population.model.ProvincePopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvincePopulationRepository extends JpaRepository<ProvincePopulation, Long> {
    List<ProvincePopulation> findByProvinceContainingIgnoreCase(String province);

    List<ProvincePopulation> findByCode(Long code);
}
