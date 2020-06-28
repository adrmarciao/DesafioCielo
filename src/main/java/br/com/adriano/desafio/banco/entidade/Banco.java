package br.com.adriano.desafio.banco.entidade;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_banco")
@SequenceGenerator(name = "seq", sequenceName = "banco_values_seq", allocationSize = 1)
public class Banco {
    @Id
    @Column(name = "id_banco")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    @Column(name = "codigo_banco")
    private Long codigoBanco;
    @Column(name = "numero_agencia")
    private Long numeroAgencia;
    @Column(name = "numero_conta_corrente")
    private String numeroContaCorrente;

}
