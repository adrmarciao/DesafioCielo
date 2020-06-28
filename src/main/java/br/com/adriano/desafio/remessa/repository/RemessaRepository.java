package br.com.adriano.desafio.remessa.repository;

import br.com.adriano.desafio.remessa.entidade.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RemessaRepository extends JpaRepository<Remessa, Long>, JpaSpecificationExecutor<Remessa>  {
}
