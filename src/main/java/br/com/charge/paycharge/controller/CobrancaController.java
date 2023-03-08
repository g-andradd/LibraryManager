package br.com.charge.paycharge.controller;

import br.com.charge.paycharge.dto.CobrancaDto;
import br.com.charge.paycharge.dto.CodigosDasCobrancasDto;
import br.com.charge.paycharge.dto.ResumoTotalDto;
import br.com.charge.paycharge.service.CobrancaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CobrancaController {

    private final CobrancaService cobrancaService;

    public CobrancaController(CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    //todo Fazer o token

    @GetMapping("/cobrancas")
    public List<CobrancaDto> listar(@RequestParam String data) {
        return cobrancaService.listarTodos(1L, data);
    }

    @GetMapping("/resumo/total")
    public ResumoTotalDto buscarResumoTotal(@RequestParam String data) {
        return cobrancaService.buscarResumoTotal(1L, data);
    }

    @GetMapping("/cobrancas/codigo")
    public List<CodigosDasCobrancasDto> listarCodigos(@RequestParam String data) {
        return cobrancaService.buscarCodigosDasCobrancas(1L, data);
    }

    @PutMapping("/cobrancas/cancelar/{codigo}")
    public ResponseEntity<Void> cancelar(@PathVariable Long codigo){
        cobrancaService.cancelarCobranca("Teste", codigo);
        return ResponseEntity.ok().build();
    }

}
