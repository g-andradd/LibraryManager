package br.com.charge.paycharge.mapper;

import br.com.charge.paycharge.model.UploadFile;
import br.com.charge.paycharge.model.RegistrosUploadFile;

import java.time.Instant;
import java.util.Date;

public class RegistrosUploadFileMapper {
    public RegistrosUploadFile transformar(UploadFile uf) {
        return RegistrosUploadFile.builder()
                .codigoEmpresa(uf.getCodigoEmpresa())
                .nome(uf.getNome())
                .local(uf.getLocal())
                .tipoArquivo(uf.getTipoArquivo())
                .situacaoArquivo(uf.getSituacaoArquivo())
                .dataHoraIncusao(uf.getDataHoraIncusao())
                .dataHoraAlteracao(Date.from(Instant.now()))
                .dataHoraProcessamento(null)
                .usuarioWeb(uf.getUsuarioWeb())
                .usuarioInclusao(uf.getUsuarioInclusao())
                .usuarioAlteracao(uf.getUsuarioAlteracao())
                .dataHoraAlteracao(uf.getDataHoraAlteracao())
                .build().informacoesFile(uf.getInformacoesFile());
    }
}
