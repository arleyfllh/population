package com.arleyfllh.population.utils;

import com.arleyfllh.population.model.ProvincePopulation;
import com.arleyfllh.population.repository.ProvincePopulationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProvincePopulationRepository repository;

    public DataInitializer(ProvincePopulationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = TypeReference.class.getResourceAsStream("/indonesia-province-jml-penduduk.json");

        JsonNode root = mapper.readTree(is);
        JsonNode features = root.get("features");
        List<ProvincePopulation> dataList = new ArrayList<>();

        for (JsonNode feature : features) {
            JsonNode props = feature.get("properties");

            ProvincePopulation data = new ProvincePopulation();
            data.setCode(props.get("kode").asLong());
            data.setProvince(props.get("Propinsi").asText());
            data.setPopulation(props.get("Jumlah Penduduk").asLong());

            dataList.add(data);
        }

        repository.saveAll(dataList);
    }
}
