package io.github.rafaelaperruci.moviecataloginfo_api.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionClassHandler {

    @ExceptionHandler(EntityNotFoundException.class)  //indica a classe específica para qual exceção o método será lançado
    public ResponseEntity handle404(){                //automaticamente será invocada quando determinado recurso não existir
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400(MethodArgumentNotValidException ex){ //capturando a exceção lançada

        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataBeanValidationErrors::new).toList());
    }

    //inner DTO para padronizar o response dos erros de validação
    private record DataBeanValidationErrors(String field, String message){

        public DataBeanValidationErrors(FieldError err){
            this(err.getField(), err.getDefaultMessage());
        }
    }
}
