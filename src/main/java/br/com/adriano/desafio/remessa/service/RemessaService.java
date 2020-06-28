package br.com.adriano.desafio.remessa.service;

import br.com.adriano.desafio.remessa.dto.RemessaDTO;
import br.com.adriano.desafio.remessa.exception.RemessaNaoEncontrado;
import br.com.adriano.desafio.remessa.repository.RemessaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RemessaService {

    @Autowired
    private RemessaRepository repository;

    public RemessaDTO get(Long id) {
        return repository.findById(id).map(RemessaDTO::new).orElseThrow(RemessaNaoEncontrado::new);
    }

    public RemessaDTO saveOrUpdate(RemessaDTO dto) {
        return new RemessaDTO(repository.save(RemessaDTO.valueOf(dto)));
    }

    public Page<RemessaDTO> getPaged(Integer page, Integer limit) {
        return this.repository.findAll(PageRequest.of(page, limit)).map(RemessaDTO::new);
    }
}
