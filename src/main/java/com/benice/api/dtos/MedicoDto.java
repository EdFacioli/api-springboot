package com.benice.api.dtos;

import com.benice.api.domain.Endereco;
import com.benice.api.domain.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicoDto {
    
    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotBlank(message = "Campo obrigatório") @Email
    private String email;
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    @NotBlank(message = "Campo obrigatório") @Pattern(regexp = "\\d{4,6}")
    private String crm;
    @NotNull(message = "Campo obrigatório")
    private Especialidade especialidade;
    @NotNull(message = "Campo obrigatório") @Valid 
    private Endereco endereco;  
}
