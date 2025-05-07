package com.arleyfllh.population.repository;

import com.arleyfllh.population.model.ProvincePopulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincePopulationRepository extends JpaRepository<ProvincePopulation, Long> {
    Page<ProvincePopulation> findByProvinceContainingIgnoreCase(String province, Pageable pageable);

    Page<ProvincePopulation> findByCode(Long code, Pageable pageable);

    Page<ProvincePopulation> findByProvinceContainingIgnoreCaseAndCode(String province, Long code, Pageable pageable);
}
