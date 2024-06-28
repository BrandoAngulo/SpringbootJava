package integradorrecaudo.versiones.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "versiones")
public class Versiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    @JsonProperty("nombre_version")
    private String nombreVersion;

    @Column(length = 50)
    @JsonProperty("java_version")
    private String javaVersion;


    @Column(length = 50)
    @JsonProperty("php_version")
    private String phpVersion;


    @Column(length = 30)
    @JsonProperty("movil_version")
    private String movilVersion;


    @Column(length = 50)
    private String version;

    @Column(length = 50)
    private String fecha;

    @Column(length = 10)
    private Boolean activo;

}
