package br.com.adriano.desafio.migration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = { "totalControleLancamento", "indice", "tamanhoPagina", "totalElements" })
public class LacamentoConteudoDTO {
    List<ControleLancamentoDTO> listaControleLancamento;
}
