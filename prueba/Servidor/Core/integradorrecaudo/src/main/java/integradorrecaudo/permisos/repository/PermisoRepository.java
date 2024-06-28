package integradorrecaudo.permisos.repository;

import integradorrecaudo.permisos.entity.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoRepository extends JpaRepository<Permisos, Integer> {
}
