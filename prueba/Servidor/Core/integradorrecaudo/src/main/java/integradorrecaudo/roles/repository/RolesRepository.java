package integradorrecaudo.roles.repository;

import integradorrecaudo.roles.entity.Roles;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByRol(String rol);

    @Query(value = "SELECT r FROM Roles r JOIN r.permisos m ON m.id = :id ")
    List<Roles> consultarPermisos(Integer id);

    /*
     * consulta si un rol esta asignado a un usuario
     */
    @Query("select r from Roles r where r.id in " +
            "(select r2.id from Usuario u join u.roles r2 where r2.id = :idRol)")
    List<Roles> consultarRolesAsignadosUsuariosPorIdRol(Integer idRol);

    @Query(value = "select r.id as id_rol, r.rol, r.activo,m.id as id_modulo, m.titulo as titulo_modulo, " +
            "sm.id as id_submodulo, sm.titulo as titulo_submodulo " +
            "from roles r " +
            "LEFT join roles_submodulos rs on r.id = rs.rol_id " +
            "LEFT join submodulos sm on sm.id = rs.submodulos_id " +
            "LEFT join modulos m on m.id = sm.modulo_id ", nativeQuery = true)
    List<Tuple> consultarSubmodulosAsignados();
}
