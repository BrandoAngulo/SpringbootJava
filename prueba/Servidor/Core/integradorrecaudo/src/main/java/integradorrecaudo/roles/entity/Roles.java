package integradorrecaudo.roles.entity;

import integradorrecaudo.permisos.entity.Permisos;
import integradorrecaudo.submodulos.entity.Submodulos;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"submodulos"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, unique = true)
    private String rol;

    private boolean activo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "roles_permisos",
            joinColumns = @JoinColumn(
                    name = "rol_id"), inverseJoinColumns = @JoinColumn(name = "permiso_id"))

    Set<Permisos> permisos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "roles_submodulos",
            joinColumns = @JoinColumn(
                    name = "rol_id"), inverseJoinColumns = @JoinColumn(name = "submodulos_id"))
    Set<Submodulos> submodulos;

    public Roles(Integer id) {
        this.id = id;
    }

}
