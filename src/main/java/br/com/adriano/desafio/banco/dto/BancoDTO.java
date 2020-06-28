package br.com.adriano.desafio.banco.dto;

import br.com.adriano.desafio.banco.entidade.Banco;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BancoDTO {
    private Long id;
    private Long codigoBanco;
    private Long agencia;
    private String contaCorrente;

    public BancoDTO() {
    }

    public BancoDTO(Banco banco) {
        this.id = banco.getId();
        this.codigoBanco = banco.getCodigoBanco();
        this.agencia = banco.getNumeroAgencia();
        this.contaCorrente = banco.getNumeroContaCorrente();
    }

    public static Banco valueOf(BancoDTO dto) {
        Banco banco = new Banco();
        banco.setCodigoBanco(dto.codigoBanco);
        banco.setId(dto.id);
        banco.setNumeroAgencia(dto.getAgencia());
        banco.setNumeroContaCorrente(dto.getContaCorrente());
        return banco;
    }
}
