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
public class SupplierDTO {
    private Integer id;
    @NotBlank(message = "el nombre de supplier no debe ser vacia")
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String nit;
    //List<Product> productList = new ArrayList<>();
}
