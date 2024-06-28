package integradorrecaudo.clientes.entity;

import integradorrecaudo.servidores.entity.Servidor;
import integradorrecaudo.zona_horaria.entity.ZonaHoraria;
import integradorrecaudo.versiones.entity.Versiones;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String nombreAdmin;
    @Column(length = 20)

    private String celular;
    @Column(length = 100, unique = true)

    private String email;
    @Column(length = 20, unique = true)
    private String documento;
    @Column(length = 50)
    private String documentoFacturacion;
    @Column(length = 50)
    private String tipoFactura;
    @Column(length = 20)
    private String tipoMoneda;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(length = 50)
    private Integer tipoCliente;
    @Column(length = 20)
    private String nombreJava;
    @Column(length = 10)
    private Integer puertoWeb;
    @Column(length = 10)
    private Integer puertoMovil;
    @Column(length = 50)
    private String nombreWeb;
    private boolean activo;
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private Servidor servidor;
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private ZonaHoraria zonaHoraria;
    @JoinColumn(referencedColumnName = "id")
    @ManyToOne
    private Versiones version;

    @PrePersist
    private void asignarValores() {
        activo = true;
        fechaCreacion = new Date();
    }
}
