package br.com.adriano.desafio.remessa.controller;

import br.com.adriano.desafio.remessa.dto.RemessaDTO;
import br.com.adriano.desafio.remessa.service.RemessaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/remessa")
public class RemessaController {
    @Autowired
    private RemessaService service;

    @GetMapping("/{id}")
    public @ResponseBody
    RemessaDTO get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/")
    public @ResponseBody RemessaDTO post(@RequestBody RemessaDTO dto) {
        return service.saveOrUpdate(dto);
    }

    @GetMapping("/{page}/{limit}")
    public @ResponseBody
    Page<RemessaDTO> getPaged(@PathVariable Integer page, @PathVariable Integer limit) {
        return service.getPaged(page, limit);
    }

    @PutMapping("/")
    public @ResponseBody
    RemessaDTO put(@RequestBody RemessaDTO dto) {
        return service.saveOrUpdate(dto);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
