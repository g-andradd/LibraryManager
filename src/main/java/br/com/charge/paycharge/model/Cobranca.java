package br.com.charge.paycharge.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
public class Cobranca {

    private long codigo;
    private Date dataHoraCriacao;
    private SituacaoArquivo status;
    private Date dataHoraProcessamento;
    private String nomeArquivo;
    private int quantidadeRegistros;
    private BigDecimal valorTotal;

}
