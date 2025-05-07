package com.arleyfllh.population.service.impl;

import com.arleyfllh.population.exception.ResourceNotFoundException;
import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.model.ProvincePopulationDto;
import com.arleyfllh.population.repository.ProvincePopulationRepository;
import com.arleyfllh.population.service.ProvincePopulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
        return provincePopulationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Province with ID " + id + " was not found"));
    }

    @Override
    public List<ProvincePopulation> sortByPopulation(String sort) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return provincePopulationRepository.findAll(Sort.by(direction, "population"));
    }

    @Override
    public Long totalPopulation() {
        return provincePopulationRepository.findAll().stream().mapToLong(ProvincePopulation::getPopulation).sum();
    }

    @Override
    public ProvincePopulation create(ProvincePopulation request) {
        return provincePopulationRepository.save(request);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        if (!provincePopulationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource with ID " + id + " does not exist");
        }

        provincePopulationRepository.deleteById(id);
    }

    @Override
    public ProvincePopulation update(Long id, ProvincePopulationDto dto) {
        ProvincePopulation entity = provincePopulationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found with ID " + id));

        if (dto.getCode() != null) {
            entity.setCode(dto.getCode());
        }
        if (dto.getProvince() != null) {
            entity.setProvince(dto.getProvince());
        }
        if (dto.getPopulation() != null) {
            entity.setPopulation(dto.getPopulation());
        }

        return provincePopulationRepository.save(entity);
    }
}
