package com.qualitysales.posinventory.Controllers.DTO;

import com.qualitysales.posinventory.model.Category;
import com.qualitysales.posinventory.model.Supplier;
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
    private int id;
    private String name;
    private String description;
    List<Supplier> supplierList = new ArrayList<>();
    List<Category> categoryList= new ArrayList();
    private BigDecimal price;
    private int stock;

}
