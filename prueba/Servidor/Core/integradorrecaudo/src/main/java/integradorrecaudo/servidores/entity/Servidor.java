package integradorrecaudo.servidores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "servidores")
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 20)
    private String ip;
    @Column(length = 10)
    private Integer puerto;
    @Column(length = 50)
    private String usuario;
    @Column(length = 50)
    private String password;
    @Column(length = 10)
    private String tipo;


}
