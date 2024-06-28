package integradorrecaudo.versiones.repository;

import integradorrecaudo.versiones.entity.Versiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionesRepository extends JpaRepository<Versiones, Integer> {
}
