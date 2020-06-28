package br.com.adriano.desafio.lancamento.controller;

import br.com.adriano.desafio.banco.dto.BancoDTO;
import br.com.adriano.desafio.lancamento.dto.LancamentoDTO;
import br.com.adriano.desafio.lancamento.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lancamento")
public class LancamentoController {
    @Autowired
    private LancamentoService service;

    @GetMapping("/{id}")
    public @ResponseBody
    LancamentoDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/")
    public @ResponseBody LancamentoDTO post(@RequestBody LancamentoDTO dto) {
        return service.saveOrUpdate(dto);
    }

    @GetMapping("/{page}/{limit}")
    public @ResponseBody
    Page<LancamentoDTO> getPaged(@PathVariable Integer page, @PathVariable Integer limit) {
        return service.getPaged(page, limit);
    }
}
