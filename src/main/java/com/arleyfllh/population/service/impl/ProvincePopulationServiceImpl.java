package com.arleyfllh.population.service.impl;

import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.repository.ProvincePopulationRepository;
import com.arleyfllh.population.service.ProvincePopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincePopulationServiceImpl implements ProvincePopulationService {

    @Autowired
    private ProvincePopulationRepository provincePopulationRepository;

    @Override
    public List<ProvincePopulation> getAll() {
        return provincePopulationRepository.findAll();
    }

    @Override
    public List<ProvincePopulation> findByProvince(String province) {
        return provincePopulationRepository.findByProvinceContainingIgnoreCase(province);
    }

    @Override
    public ProvincePopulation findById(Long id) {
        return provincePopulationRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find province ID : " + id));
    }

    @Override
    public List<ProvincePopulation> findByCode(Long code) {
        return provincePopulationRepository.findByCode(code);
    }

    @Override
    public List<ProvincePopulation> sortByPopulation() {
        return provincePopulationRepository.findAll(Sort.by(Sort.Direction.DESC, "population"));
    }

    @Override
    public Long totalPopulation() {
        return provincePopulationRepository.findAll().stream().mapToLong(ProvincePopulation::getPopulation).sum();
    }

    @Override
    public Page<ProvincePopulation> findByPagination(int page, int size) {
        return provincePopulationRepository.findAll(PageRequest.of(page, size));
    }
}
