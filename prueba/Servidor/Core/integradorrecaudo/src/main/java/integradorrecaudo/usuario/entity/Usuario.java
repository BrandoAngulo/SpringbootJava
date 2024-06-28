/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradorrecaudo.usuario.entity;

import jakarta.persistence.*;
import lombok.*;
import integradorrecaudo.roles.entity.Roles;
import lombok.experimental.FieldDefaults;

import java.util.Set;


/**
 * @author DesarrolloRobert
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false, unique = true, length = 25)
    String nombreUsuario;

    String contrasena;

    @Column(nullable = false, length = 50)
    String nombres;

    @Column(nullable = false, length = 50)
    String apellidos;

    @Column(nullable = false, unique = true, length = 100)
    String correo;

    @Column(unique = true, nullable = false, length = 30)
    String documento;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "usuario_roles",
            joinColumns = @JoinColumn(
                    name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    Set<Roles> roles;
    boolean activo;
}
