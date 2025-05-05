package com.arleyfllh.population.service.impl;

import com.arleyfllh.population.exception.ResourceNotFoundException;
import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.repository.ProvincePopulationRepository;
import com.arleyfllh.population.service.ProvincePopulationService;
import com.arleyfllh.population.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<ProvincePopulation> getAllByPagination(Pageable pageable) {
        return provincePopulationRepository.findAll(pageable);
    }

    @Override
    public Page<ProvincePopulation> search(String province, Long code, Pageable pageable) {
        if (province != null && code != null) {
            return provincePopulationRepository.findByProvinceContainingIgnoreCaseAndCode(province, code, pageable);
        } else if (province != null) {
            return provincePopulationRepository.findByProvinceContainingIgnoreCase(province, pageable);
        } else if (code != null) {
            return provincePopulationRepository.findByCode(code, pageable);
        }
        return provincePopulationRepository.findAll(pageable);
    }

    @Override
    public ProvincePopulation findById(Long id) {
        return provincePopulationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Province ID " + id + " not found"));
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
    public ProvincePopulation create(ProvincePopulation request) {
        return provincePopulationRepository.save(request);
    }
}
