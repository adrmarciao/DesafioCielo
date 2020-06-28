package br.com.adriano.desafio.lancamento.entidade;

import br.com.adriano.desafio.banco.entidade.Banco;
import br.com.adriano.desafio.remessa.entidade.Remessa;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_lacamento")
@SequenceGenerator(name = "seq", sequenceName = "lacamento_values_seq", allocationSize = 1)
public class Lancamento {

    @Id
    @Column(name = "id_lancamento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @Column(name = "numero_remessa_banco")
    private Long numeroRemessaBanco;

    @Column(name = "nome_situacao_remessa")
    private LancamentoStatus nomeSituacaoRemessa;

    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    private Banco banco;

    @ManyToOne
    @JoinColumn(name = "id_remessa", referencedColumnName = "id_remessa")
    private Remessa remessa;

    @Column(name = "date_lancamento_conta_corrente_cliente")
    private Long dateLancamentoContaCorrenteCliente;
}
