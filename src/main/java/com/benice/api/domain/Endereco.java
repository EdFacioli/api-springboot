package com.benice.api.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @NotBlank(message = "Campo obrigatório")
    private String logradouro;
    @NotBlank(message = "Campo obrigatório")
    private String bairro;
    @NotBlank(message = "Campo obrigatório")
    private String cidade;
    @NotBlank(message = "Campo obrigatório") @Pattern(regexp = "\\d{8}")
    private String cep;
    private String numero;
    private String complemento;
    @NotBlank(message = "Campo obrigatório")
    private String uf;
}
