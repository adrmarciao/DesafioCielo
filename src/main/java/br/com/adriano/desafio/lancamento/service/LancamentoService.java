package br.com.adriano.desafio.lancamento.service;

import br.com.adriano.desafio.banco.dto.BancoDTO;
import br.com.adriano.desafio.lancamento.dto.LancamentoDTO;
import br.com.adriano.desafio.lancamento.exception.LancamentoNaoEncontrado;
import br.com.adriano.desafio.lancamento.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    public LancamentoDTO get(Long id) {
        return repository.findById(id).map(LancamentoDTO::new).orElseThrow(LancamentoNaoEncontrado::new);
    }

    public LancamentoDTO saveOrUpdate(LancamentoDTO dto) {
        return new LancamentoDTO(repository.save(LancamentoDTO.valueOf(dto)));
    }

    public Page<LancamentoDTO> getPaged(Integer page, Integer limit) {
        return this.repository.findAll(PageRequest.of(page, limit)).map(LancamentoDTO::new);
    }
}
