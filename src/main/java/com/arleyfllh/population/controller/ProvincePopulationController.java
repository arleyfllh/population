package com.arleyfllh.population.controller;

import com.arleyfllh.population.ApiResponse;
import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.service.ProvincePopulationService;
import com.arleyfllh.population.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvincePopulationController {

    @Autowired
    private ProvincePopulationService provincePopulationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProvincePopulation>>> getAll() {
        List<ProvincePopulation> result = provincePopulationService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/find")
    public ResponseEntity<ApiResponse<List<ProvincePopulation>>> findByProvince(@RequestParam String province) {
        List<ProvincePopulation> result = provincePopulationService.findByProvince(province);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProvincePopulation>> findById(@PathVariable Long id) {
        ProvincePopulation result = provincePopulationService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ApiResponse<List<ProvincePopulation>>> findByCode(@PathVariable Long code) {
        List<ProvincePopulation> result = provincePopulationService.findByCode(code);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/population/sorted")
    public ResponseEntity<ApiResponse<List<ProvincePopulation>>> sortByPopulation() {
        List<ProvincePopulation> result = provincePopulationService.sortByPopulation();
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/population/total")
    public ResponseEntity<ApiResponse<Long>> totalPopulation() {
        Long result = provincePopulationService.totalPopulation();
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }

    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Page<ProvincePopulation>>> findByPagination(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Page<ProvincePopulation> result = provincePopulationService.findByPagination(page, size);
        return ResponseEntity.ok(new ApiResponse<>(true, Constants.ApiResponses.FETCHED_SUCCESS, result));
    }
}
