package br.com.adriano.desafio.banco.repository;

import br.com.adriano.desafio.banco.entidade.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>, JpaSpecificationExecutor<Banco> {
}
