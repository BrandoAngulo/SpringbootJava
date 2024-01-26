package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Product;
import jakarta.validation.constraints.NotBlank;
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
public class SupplierDTO {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String nit;
    List<Product> productList = new ArrayList<>();
}
