package br.com.adriano.desafio.migration.dto;

import lombok.Data;

@Data
public class ControleLancamentoDTO {
    private LancamentoContaCorrenteClienteDTO lancamentoContaCorrenteCliente;
    private String dataEfetivaLancamento;
    private String dataLancamentoContaCorrenteCliente;
    private Long numeroEvento;
    private String descricaoGrupoPagamento;
    private String codigoIdentificadorUnico;
    private String nomeBanco;
    private Long quantidadeLancamentoRemessa;
    private String numeroRaizCNPJ;
    private String numeroSufixoCNPJ;
    private Long valorLancamentoRemessa;
    private Long dateLancamentoContaCorrenteCliente;
    private Long dateEfetivaLancamento;
}
