package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Product;
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
    private int id;
    private String name;
    private List<Product> productList = new ArrayList<>();
}
