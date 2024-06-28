package integradorrecaudo.procesos.repository;

import integradorrecaudo.procesos.entity.Procesos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesosRepository extends JpaRepository<Procesos, Integer> {
}
