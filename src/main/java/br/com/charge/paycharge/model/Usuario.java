package br.com.charge.paycharge.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SORPAG_USUARIOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String senha;
    private String nomeUsuario;
    @OneToOne
    private Empresa empresa;

}
