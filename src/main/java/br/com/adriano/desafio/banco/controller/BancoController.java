package br.com.adriano.desafio.banco.controller;

import br.com.adriano.desafio.banco.dto.BancoDTO;
import br.com.adriano.desafio.banco.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping("/{id}")
    public @ResponseBody BancoDTO getBanco(@PathVariable Long id) {
        return bancoService.getBanco(id);
    }

    @PostMapping("/")
    public @ResponseBody BancoDTO getBanco(@RequestBody BancoDTO dto) {
        return bancoService.saveOrUpdate(dto);
    }

    @GetMapping("/{page}/{limit}")
    public @ResponseBody Page<BancoDTO> getBanco(@PathVariable Integer page, @PathVariable Integer limit) {
        return bancoService.getPaged(page, limit);
    }
}
