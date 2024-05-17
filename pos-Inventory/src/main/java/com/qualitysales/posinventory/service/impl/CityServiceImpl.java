package com.qualitysales.posinventory.service.impl;

import com.qualitysales.posinventory.Controllers.DTO.CityDTO;
import com.qualitysales.posinventory.mapper.CityMapper;
import com.qualitysales.posinventory.model.City;
import com.qualitysales.posinventory.repository.CityRepository;
import com.qualitysales.posinventory.service.CityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CityServiceImplements implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityDTO findCity(Integer id) {

        City cityId = cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        CityDTO cityDTO = CityMapper.MAPPER.toCityDTO(cityId);

        try {
            log.info("findCity ok: {}", cityDTO);
            return cityDTO;

        } catch (RuntimeException e) {
            log.info("findCity throw: {}", cityDTO);
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<CityDTO> findCities() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> cityDTOS = CityMapper.MAPPER.toCityDTOS(cities);
        try {
            log.info("findCities OK: {}", cities);
            return cityDTOS;

        } catch (RuntimeException e) {
            log.info("findCities throw {}", cities);
            throw new IllegalArgumentException(e);

        }
    }

    @Override
    public CityDTO saveCity(City city) {
        try {
            CityDTO cityDTO = CityMapper.MAPPER.toCityDTO(city);
            cityRepository.save(city);
            log.info("saveCity ok: {}", city);

            return cityDTO;
        } catch (RuntimeException e) {
            log.error("saveCity throw: {}", city);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public CityDTO updateCity(Integer id, City city) {
        City findId = cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        CityDTO cityDTO = CityMapper.MAPPER.toCityDTO(findId);
        if (findId.getId().equals(city.getId())) {
            throw new IllegalArgumentException("Id is present");
        }

        try {
            log.info("updateCity ok: {}", city);
            cityDTO.setCode(city.getCode());
            cityDTO.setName(city.getName());
            cityRepository.save(city);
            return cityDTO;
        } catch (RuntimeException e) {
            log.error("updateCity throw: {}", city);
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void deleteCity(Integer id) {
        City findId = cityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        try {
            log.info("deleteCity ok: {}", findId);
            cityRepository.delete(findId);
        } catch (Exception e) {
            log.error("deleteCity throw: {}", findId);
            throw new RuntimeException(e);
        }
    }
}
