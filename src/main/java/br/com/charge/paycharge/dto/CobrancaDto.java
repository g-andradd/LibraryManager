package br.com.charge.paycharge.dto;

import br.com.charge.paycharge.model.Cobranca;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
public class CobrancaDto {

    private final long codigo;
    private final Date dataHoraCriacao;
    private final String status;
    private final Date dataHoraProcessamento;
    private final String nomeArquivo;
    private final int quantidadeRegistros;
    private final BigDecimal valorTotal;

    public CobrancaDto(Cobranca cobranca) {
        this.codigo = cobranca.getCodigo();
        this.dataHoraCriacao = cobranca.getDataHoraCriacao();
        this.status = cobranca.getStatus().getValor();
        this.dataHoraProcessamento = cobranca.getDataHoraProcessamento();
        this.nomeArquivo = cobranca.getNomeArquivo();
        this.quantidadeRegistros = cobranca.getQuantidadeRegistros();
        this.valorTotal = cobranca.getValorTotal();
    }

    public static List<CobrancaDto> converter(List<Cobranca> cobrancas) {
        return cobrancas.stream().map(CobrancaDto::new).toList();
    }
}
