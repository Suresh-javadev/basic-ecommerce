package com.ecom.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecom.dto.ErrorDto;
import com.ecom.exception.UserNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map <String, Set<String>> errorsMap =  fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())
                )
        );    
    	
    	ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation Error",errorsMap.toString());

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorDto> handleEntityNotFound(EntityNotFoundException ex){
        ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
    		HttpStatus status, WebRequest request) {
    	ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error Occur", ex.getMessage());
    	
    	return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<ErrorDto> handleAccessDeniedException(AccessDeniedException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.FORBIDDEN, "No Access", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);

	}
    
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}    
    
}