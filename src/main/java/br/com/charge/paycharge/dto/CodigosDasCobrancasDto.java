package br.com.charge.paycharge.dto;

import br.com.charge.paycharge.projection.CodigosDasCobrancas;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CodigosDasCobrancasDto {

    private final long codigo;
    private final String nomeArquivo;

    public CodigosDasCobrancasDto(CodigosDasCobrancas codigos) {
        this.codigo = codigos.getCodigo();
        this.nomeArquivo = codigos.getNomeArquivo();
    }

    public static List<CodigosDasCobrancasDto> converter(List<CodigosDasCobrancas> codigosDasCobrancas) {
        return codigosDasCobrancas.stream().map(CodigosDasCobrancasDto::new).collect(Collectors.toList());
    }
}
