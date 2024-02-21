package br.com.vidaldev.mystocks.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlerEntityNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlerArgumentNotValid(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ArgumentInvalidDto::new));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handlerErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handlerErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication fail");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handlerErrorAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " +ex.getLocalizedMessage());
    }

    private record ArgumentInvalidDto(String field, String msg){
        public ArgumentInvalidDto(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
