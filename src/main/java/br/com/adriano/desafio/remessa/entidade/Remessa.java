package br.com.adriano.desafio.remessa.entidade;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_remessa")
@SequenceGenerator(name = "seq", sequenceName = "remessa_values_seq", allocationSize = 1)
public class Remessa {
    @Id
    @Column(name = "id_remessa")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;

    @Column(name = "numero_evento")
    private Long numeroEvento;

    @Column(name = "descricao_grupo_pagamento")
    private String descricaoGrupoPagamento;

    @Column(name = "codigo_identificador_unico")
    private String codigoIdentificadorUnico;

    @Column(name = "nome_banco")
    private String nomeBanco;

    @Column(name = "quantidade_lancamento_remessa")
    private Long quantidadeLancamentoRemessa;

    @Column(name = "numero_raiz_cnpj")
    private String numeroRaizCNPJ;

    @Column(name = "numero_sufixo_cnpj")
    private String numeroSufixoCNPJ;

    @Column(name = "valor_lancamento_remessa")
    private Long valorLancamentoRemessa;

    @Column(name = "date_efetiva_lancamento")
    private Long dateEfetivaLancamento;
}
