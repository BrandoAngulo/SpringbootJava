package integradorrecaudo.submodulos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import integradorrecaudo.modulos.entity.Modulos;
import integradorrecaudo.roles.entity.Roles;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Submodulos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 50)
    String ruta;
    @Column(length = 50)
    String icono;
    @Column(length = 50)
    String titulo;
    @ManyToOne
    @JoinColumn(name = "modulo_id")
    @JsonBackReference
    Modulos modulo;
    @JsonBackReference
    @ManyToMany(mappedBy = "submodulos")
    List<Roles> roles;
}
