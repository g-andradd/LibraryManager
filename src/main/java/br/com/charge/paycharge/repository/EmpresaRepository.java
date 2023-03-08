package br.com.charge.paycharge.repository;

import br.com.charge.paycharge.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
