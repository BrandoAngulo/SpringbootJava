package com.qualitysales.posinventory.Controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityDTO  {

    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
