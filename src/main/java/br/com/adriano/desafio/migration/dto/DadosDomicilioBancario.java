package br.com.adriano.desafio.migration.dto;

import lombok.Data;

@Data
public class DadosDomicilioBancario {
    private Long codigoBanco;
    private Long numeroAgencia;
    private Long numeroContaCorrente;
}
