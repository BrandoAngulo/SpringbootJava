package com.qualitysales.posinventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
@Builder
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "idproveedor")
    private int idSupplier;
    @ManyToOne
    @JoinColumn(name = "idcategoria", nullable = false)
    private Category idCategory;
    @Column(name = "precio")
    private BigDecimal price;
    @Column(name = "cantidad")
    private int stock;

}
