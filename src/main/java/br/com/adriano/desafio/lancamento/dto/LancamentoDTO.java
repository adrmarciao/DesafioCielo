package br.com.adriano.desafio.lancamento.dto;

import br.com.adriano.desafio.banco.dto.BancoDTO;
import br.com.adriano.desafio.lancamento.entidade.Lancamento;
import br.com.adriano.desafio.lancamento.entidade.LancamentoStatus;
import br.com.adriano.desafio.remessa.dto.RemessaDTO;
import lombok.Data;

@Data
public class LancamentoDTO {

    private Long id;
    private Long numeroRemessaBanco;
    private LancamentoStatus nomeSituacaoRemessa;
    private BancoDTO dadosBancarios;
    private RemessaDTO remessa;
    private Long dateLancamentoContaCorrenteCliente;

    public LancamentoDTO() {
    }

    public LancamentoDTO(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.numeroRemessaBanco = lancamento.getNumeroRemessaBanco();
        this.nomeSituacaoRemessa = lancamento.getNomeSituacaoRemessa();
        this.dadosBancarios = new BancoDTO(lancamento.getBanco());
        this.remessa = new RemessaDTO(lancamento.getRemessa());
        this.dateLancamentoContaCorrenteCliente = lancamento.getDateLancamentoContaCorrenteCliente();
    }

    public static Lancamento valueOf(LancamentoDTO dto) {
        final Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setNumeroRemessaBanco(dto.getNumeroRemessaBanco());
        lancamento.setNomeSituacaoRemessa(dto.getNomeSituacaoRemessa());
        lancamento.setBanco(BancoDTO.valueOf(dto.getDadosBancarios()));
        lancamento.setRemessa(RemessaDTO.valueOf(dto.getRemessa()));
        lancamento.setDateLancamentoContaCorrenteCliente(dto.getDateLancamentoContaCorrenteCliente());
        return lancamento;
    }
}
