package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.model.Supplier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "the name field must not be empty")
    @NotNull(message = "the name field must not be null")
    private String name;
    private String description;
    private Supplier supplier;
    private Category category;
    private BigDecimal price;
    @NotNull
    private Integer stock;
}
