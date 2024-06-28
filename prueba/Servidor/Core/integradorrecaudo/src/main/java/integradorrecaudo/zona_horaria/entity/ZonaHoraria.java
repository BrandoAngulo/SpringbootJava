package integradorrecaudo.zona_horaria.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "zona_horaria")

public class ZonaHoraria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String utc;
    @Column(length = 10)
    private String descripcion;
    @Column(length = 50)
    private String pais;
    @Column(length = 50)
    private String ciudad;
    @Column(length = 10)
    private Integer orden;
    private boolean activo;
}
