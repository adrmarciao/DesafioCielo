package br.com.adriano.desafio.migration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties({"dadosAnaliticoLancamentoFinanceiroCliente"})
public class LancamentoContaCorrenteClienteDTO {

    private Long numeroRemessaBanco;
    private String nomeSituacaoRemessa;
    private String nomeTipoOperacao;
    private DadosDomicilioBancario dadosDomicilioBancario;
}
