package med.voll.api.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<Cliente, Long> {

    Page<Cliente> findByAtivoTrue(Pageable pageable);//Isso aqui, faz a query pelo nome, ou seja procura pelo ativo true;
}
