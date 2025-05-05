package com.arleyfllh.population.controller;

import com.arleyfllh.population.utils.ApiResponse;
import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.service.ProvincePopulationService;
import com.arleyfllh.population.utils.Constants;
import com.arleyfllh.population.utils.PageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvincePopulationController {

    @Autowired
    private ProvincePopulationService provincePopulationService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll(
            @RequestParam(defaultValue = "true", required = false) boolean pagination,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        if (pagination) {
            Page<ProvincePopulation> result = provincePopulationService.getAllByPagination(pageable);
            return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, new PageResponse<>(result)));
        } else {
            List<ProvincePopulation> result = provincePopulationService.getAll();
            return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<ProvincePopulation>>> search(
            @RequestParam(required = false) String province,
            @RequestParam(required = false) Long code,
            @PageableDefault(sort = "population", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProvincePopulation> result = provincePopulationService.search(province, code, pageable);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, new PageResponse<>(result)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProvincePopulation>> findById(@PathVariable Long id) {
        ProvincePopulation result = provincePopulationService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/population/sorted")
    public ResponseEntity<ApiResponse<List<ProvincePopulation>>> getSortedByPopulation() {
        List<ProvincePopulation> result = provincePopulationService.sortByPopulation();
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/population/total")
    public ResponseEntity<ApiResponse<Long>> getTotalPopulation() {
        Long result = provincePopulationService.totalPopulation();
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProvincePopulation>> create(@Valid @RequestBody ProvincePopulation request) {
        ProvincePopulation result = provincePopulationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, Constants.ApiResponses.CREATED, result));
    }
}
