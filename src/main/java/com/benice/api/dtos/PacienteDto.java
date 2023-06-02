package com.benice.api.dtos;

import com.benice.api.domain.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteDto {
    
    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotBlank(message = "Campo obrigatório") @Email
    private String email;
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    @NotBlank(message = "Campo obrigatório")
    private String cpf;
    @NotNull(message = "Campo obrigatório") @Valid
    private Endereco endereco;
}