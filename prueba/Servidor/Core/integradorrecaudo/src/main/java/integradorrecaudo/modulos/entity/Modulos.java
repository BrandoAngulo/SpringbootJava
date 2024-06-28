package integradorrecaudo.modulos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import integradorrecaudo.submodulos.entity.Submodulos;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Modulos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 50)
    String ruta;
    @Column(length = 50)
    String icono;
    @Column(length = 50)
    String titulo;
    @JsonManagedReference
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Submodulos> submodulos;

}
