package com.benice.api.helper.errors;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiErrors {

    private String field;
    private String defaultMessage;

    public ApiErrors(FieldError fieldError) {
        this.field = fieldError.getField();
        this.defaultMessage = fieldError.getDefaultMessage();
    }
    
}
