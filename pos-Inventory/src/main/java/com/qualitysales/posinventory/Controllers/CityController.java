package com.qualitysales.posinventory.Controllers;

import com.qualitysales.posinventory.Controllers.DTO.CityDTO;
import com.qualitysales.posinventory.model.City;
import com.qualitysales.posinventory.service.impl.CityServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posinventory/city")
public class CityController {

    private final CityServiceImpl cityService;

    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<CityDTO>> findAll() {
        return ResponseEntity.ok(cityService.findCities());
    }

    @GetMapping("/find-by/{id}")
    public ResponseEntity<CityDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(cityService.findCity(id));
    }

    @PostMapping("/save")
    public ResponseEntity<CityDTO> save(@RequestBody City city) {
        return ResponseEntity.ok().body(cityService.saveCity(city));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CityDTO> update(@PathVariable @Valid int id,@RequestBody @Valid City city) {
        return ResponseEntity.ok().body(cityService.updateCity(id, city));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok().body("Deleted");
    }

}
