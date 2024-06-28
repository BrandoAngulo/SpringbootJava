package integradorrecaudo.servidores.repository;

import integradorrecaudo.servidores.entity.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Integer> {
}
