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
    public @ResponseBody BancoDTO get(@PathVariable Long id) {
        return bancoService.getBanco(id);
    }

    @PostMapping("/")
    public @ResponseBody BancoDTO post(@RequestBody BancoDTO dto) {
        return bancoService.saveUpdate(dto);
    }

    @PutMapping("/")
    public @ResponseBody BancoDTO put(@RequestBody BancoDTO dto) {
        return bancoService.saveUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        bancoService.delete(id);
    }

    @GetMapping("/{page}/{limit}")
    public @ResponseBody Page<BancoDTO> getPaged(@PathVariable Integer page, @PathVariable Integer limit) {
        return bancoService.getPaged(page, limit);
    }
}
