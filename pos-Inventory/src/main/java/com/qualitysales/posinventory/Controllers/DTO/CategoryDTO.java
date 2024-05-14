package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO {
    private Integer id;
    @NotBlank(message = "la descripcion de categoria no debe ser vacia")
    private String descripcion;
}
