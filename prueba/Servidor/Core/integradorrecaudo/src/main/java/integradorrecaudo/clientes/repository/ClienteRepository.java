package integradorrecaudo.clientes.repository;

import integradorrecaudo.clientes.dto.NombreBD;
import integradorrecaudo.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByDocumento(String documento);

    Optional<Cliente> findByEmail(String email);
    @Query("SELECT new integradorrecaudo.clientes.dto.NombreBD(c.nombreJava) FROM Cliente  c")
    Set<NombreBD> consultarBasesDatos();
}
