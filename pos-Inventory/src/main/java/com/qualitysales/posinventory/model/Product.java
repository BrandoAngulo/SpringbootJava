package com.qualitysales.posinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "producto")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "descripcion")
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_proveedor",nullable = false)
    @JsonIgnore
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    @JsonIgnore
    private Category category;
    @Column(name = "precio")
    private BigDecimal price;
    @Column(name = "cantidad")
    private int stock;

}
