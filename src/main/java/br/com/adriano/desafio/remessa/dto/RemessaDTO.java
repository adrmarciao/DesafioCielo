package br.com.adriano.desafio.remessa.dto;

import br.com.adriano.desafio.remessa.entidade.Remessa;
import lombok.Data;

@Data
public class RemessaDTO {
    private Long id;
    private Long numeroEvento;
    private String descricaoGrupoPagamento;
    private String codigoIdentificadorUnico;
    private String nomeBanco;
    private Long quantidadeLancamentoRemessa;
    private String numeroRaizCNPJ;
    private String numeroSufixoCNPJ;
    private Long valorLancamentoRemessa;
    private Long dateEfetivaLancamento;

    public RemessaDTO() {
    }

    public RemessaDTO(Remessa remessa) {
        this.id = remessa.getId();
        this.numeroEvento = remessa.getNumeroEvento();
        this.descricaoGrupoPagamento = remessa.getDescricaoGrupoPagamento();
        this.codigoIdentificadorUnico = remessa.getCodigoIdentificadorUnico();
        this.nomeBanco = remessa.getNomeBanco();
        this.quantidadeLancamentoRemessa = remessa.getQuantidadeLancamentoRemessa();
        this.numeroRaizCNPJ = remessa.getNumeroRaizCNPJ();
        this.numeroSufixoCNPJ = remessa.getNumeroSufixoCNPJ();
        this.valorLancamentoRemessa = remessa.getValorLancamentoRemessa();
        this.dateEfetivaLancamento = remessa.getDateEfetivaLancamento();
    }

    public static Remessa valueOf(RemessaDTO remessaDTO) {
        final Remessa remessa = new Remessa();
        remessa.setDateEfetivaLancamento(remessaDTO.getDateEfetivaLancamento());
        remessa.setValorLancamentoRemessa(remessaDTO.getValorLancamentoRemessa());
        remessa.setNumeroSufixoCNPJ(remessaDTO.getNumeroSufixoCNPJ());
        remessa.setNumeroRaizCNPJ(remessaDTO.getNumeroRaizCNPJ());
        remessa.setQuantidadeLancamentoRemessa(remessaDTO.getQuantidadeLancamentoRemessa());
        remessa.setNomeBanco(remessaDTO.getNomeBanco());
        remessa.setCodigoIdentificadorUnico(remessaDTO.getCodigoIdentificadorUnico());
        remessa.setDescricaoGrupoPagamento(remessaDTO.getDescricaoGrupoPagamento());
        remessa.setNumeroEvento(remessaDTO.getNumeroEvento());
        remessa.setId(remessaDTO.getId());
        return remessa;
    }
}
