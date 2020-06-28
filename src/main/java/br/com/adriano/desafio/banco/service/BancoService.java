package br.com.adriano.desafio.banco.service;

import br.com.adriano.desafio.banco.dto.BancoDTO;
import br.com.adriano.desafio.banco.exception.BancoNaoEncontradoException;
import br.com.adriano.desafio.banco.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BancoService {

    @Autowired
    private BancoRepository repository;

    public BancoDTO getBanco(Long id) {
        return this.repository.findById(id).map(BancoDTO::new).orElseThrow(() -> new BancoNaoEncontradoException(id));
    }

    public BancoDTO saveUpdate(BancoDTO dto) {
        return new BancoDTO(repository.save(BancoDTO.valueOf(dto)));
    }

    public Page<BancoDTO> getPaged(int page, int limit) {
        return this.repository.findAll(PageRequest.of(page, limit)).map(BancoDTO::new);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
