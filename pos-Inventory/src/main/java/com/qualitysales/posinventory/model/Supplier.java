package com.qualitysales.posinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
@Table(name = "proveedor")
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "telefono")
    private String phone;
    @Column
    private String nit;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    List<Product> productList = new ArrayList<>();

}
