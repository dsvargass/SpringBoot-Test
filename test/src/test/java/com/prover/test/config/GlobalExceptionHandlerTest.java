package com.prover.test.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void shouldHandleValidationErrors() {
        // Mock de FieldError
        FieldError fieldError = new FieldError("objectName", "name", "must not be blank");

        // Mock de BindException com um erro de campo
        BindException bindException = new BindException(new Object(), "objectName");
        bindException.addError(fieldError);

        // Criar exceção
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException.getBindingResult());

        // Instanciar handler
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<Map<String, String>> response = handler.handleValidationErrors(ex);

        // Verificações
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("name"));
        assertEquals("must not be blank", response.getBody().get("name"));
    }
}
