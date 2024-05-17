package com.qualitysales.posinventory.service;

import com.qualitysales.posinventory.Controllers.DTO.CityDTO;
import com.qualitysales.posinventory.model.City;

import java.util.List;

public interface CityService {
    CityDTO findCity(Integer id);

    List<CityDTO> findCities();

    CityDTO saveCity(City city);

    CityDTO updateCity(Integer id, City city);

    void deleteCity(Integer id);

}
