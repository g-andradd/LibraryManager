package br.com.charge.paycharge.service;

import br.com.charge.paycharge.dto.CobrancaDto;
import br.com.charge.paycharge.dto.CodigosDasCobrancasDto;
import br.com.charge.paycharge.dto.ResumoTotalDto;
import br.com.charge.paycharge.mapper.CobrancaMapper;
import br.com.charge.paycharge.mapper.RegistrosUploadFileMapper;
import br.com.charge.paycharge.model.*;
import br.com.charge.paycharge.projection.CodigosDasCobrancas;
import br.com.charge.paycharge.projection.ResumoTotalCobrancas;
import br.com.charge.paycharge.repository.CobrancaRepository;
import br.com.charge.paycharge.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class CobrancaService {

    private final CobrancaRepository cobrancaRepository;
    private final EmpresaRepository empresaRepository;

    public CobrancaService(CobrancaRepository cobrancaRepository, EmpresaRepository empresaRepository) {
        this.cobrancaRepository = cobrancaRepository;
        this.empresaRepository = empresaRepository;
    }

    public List<CobrancaDto> listarTodos(Long idEmpresa, String data) {
        List<UploadFile> ufList = cobrancaRepository
                .findAllByData(validarEmpresa(idEmpresa).getId(), Utils.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Nenhuma cobraça encontrada"));

        List<Cobranca> cobrancas = new CobrancaMapper().transformarLista(ufList);

        return CobrancaDto.converter(cobrancas);
    }

    public ResumoTotalDto buscarResumoTotal(Long idEmpresa, String data) {
        ResumoTotalCobrancas resumoTotal = cobrancaRepository
                .findResumoTotal(validarEmpresa(idEmpresa).getId(), Utils.inserirAnoMes(data))
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar o resumo total"));

        return ResumoTotalDto.converter(resumoTotal);

    }

    public List<CodigosDasCobrancasDto> buscarCodigosDasCobrancas(Long idEmpresa, String data) {
        List<CodigosDasCobrancas> codigosDasCobrancas = cobrancaRepository.findAllCodigosByData(idEmpresa, data)
                .orElseThrow(() -> new RuntimeException("Não foi possível buscar os codigos das cobrancas"));

        return CodigosDasCobrancasDto.converter(codigosDasCobrancas);
    }

    @Transactional
    public void cancelarCobranca(String nomeUsuario, Long codCobranca) {
        UploadFile uf = cobrancaRepository.findById(codCobranca)
                .orElseThrow(() -> new EntityNotFoundException("UploadFile não encontrado"));
        uf.setSituacaoArquivo(SituacaoArquivo.CANCELADO);
        uf.setDataHoraAlteracao(Date.from(Instant.now()));
        uf.setUsuarioAlteracao(nomeUsuario);
        cobrancaRepository.save(uf);
        RegistrosUploadFile ruf = new RegistrosUploadFileMapper().transformar(uf);
        new RegistrosUploadFileService().inserir(ruf);
        cobrancaRepository.delete(uf);
    }

    private Empresa validarEmpresa(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada ao buscar o resumo total"));
    }

}
