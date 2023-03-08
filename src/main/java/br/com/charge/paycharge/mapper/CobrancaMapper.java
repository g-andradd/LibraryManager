package br.com.charge.paycharge.mapper;

import br.com.charge.paycharge.model.Cobranca;
import br.com.charge.paycharge.model.UploadFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CobrancaMapper {

    public Cobranca transformar(UploadFile uf) {
        try {
            return criarCobranca(uf);
        } catch (Exception e) {
            throw new RuntimeException("Erro no mapper: transformar()");
        }

    }

    public List<Cobranca> transformarLista(List<UploadFile> ufList) {
        List<Cobranca> cobrancas = new ArrayList<>();
        for(UploadFile uf : ufList) {
            cobrancas.add(criarCobranca(uf));
        }
        return cobrancas;
    }

    private static Cobranca criarCobranca(UploadFile uf) {
        return Cobranca.builder()
                .codigo(uf.getId())
                .dataHoraCriacao(uf.getDataHoraIncusao())
                .status(uf.getSituacaoArquivo())
                .quantidadeRegistros(Integer.parseInt(uf.getInformacoesFile()[0]))
                .valorTotal(new BigDecimal(uf.getInformacoesFile()[1]))
                .dataHoraProcessamento(uf.getDataHoraProcessamento())
                .nomeArquivo(uf.getNome())
                .build();
    }

}
