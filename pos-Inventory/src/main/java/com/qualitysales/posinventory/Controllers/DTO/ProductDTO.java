package com.qualitysales.posinventory.Controllers.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Nombre no debe estar en blanco")
    private String name;
    private String description;
    private Supplier supplier;
    private Category category;
    private BigDecimal price;
    private Integer stock;
}
