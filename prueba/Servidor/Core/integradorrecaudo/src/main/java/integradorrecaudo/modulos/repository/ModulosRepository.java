package integradorrecaudo.modulos.repository;

import integradorrecaudo.modulos.entity.Modulos;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface ModulosRepository extends JpaRepository<Modulos, Integer> {

    @Query(value = "SELECT m.id AS id_modulo, m.titulo AS titulo_modulo, m.ruta AS ruta_modulo, " +
            "m.icono AS icono_modulo, sm.id AS id_submodulo, sm.titulo AS titulo_submodulo, " +
            "sm.ruta AS ruta_submodulo, sm.icono AS icono_submodulo " +
            "FROM roles r " +
            "INNER JOIN roles_submodulos rs ON r.id = rs.rol_id " +
            "INNER JOIN submodulos sm ON sm.id = rs.submodulos_id " +
            "INNER JOIN modulos m ON m.id = sm.modulo_id " +
            "WHERE r.rol IN (:roles) AND m.id IS NOT NULL GROUP BY " +
            "m.id, m.titulo, m.ruta, m.icono, " +
            "sm.id, sm.titulo, sm.ruta, sm.icono " +
            "ORDER BY m.id", nativeQuery = true)
    Set<Tuple> consultarModulosAsignados(@Param("roles") Set<String> roles);
}
