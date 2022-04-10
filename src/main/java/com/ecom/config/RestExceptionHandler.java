package com.ecom.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecom.dto.ErrorDto;
import com.ecom.exception.ResourceAlreadyExistException;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.exception.UserAlreadyExistException;
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

    @ExceptionHandler(PersistenceException.class)
    private ResponseEntity<ErrorDto> handlePersistenceException(PersistenceException ex){
    	HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
    	
    	ErrorDto error=new ErrorDto(status, "Server Error Occur", ex.getMessage());
        
        if(ex instanceof EntityNotFoundException) {
        	error= new ErrorDto(status, "Entity not found", ex.getMessage());
        }

        return new ResponseEntity<>(error, status);
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
    
    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);

	}
    
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}   
    
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
    
    @ExceptionHandler(ResourceAlreadyExistException.class)
    private ResponseEntity<ErrorDto> handleResourceAlreadyExistException(ResourceAlreadyExistException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.CONFLICT, "Resource Conflict", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.CONFLICT);

	}
    
    @ExceptionHandler(UserAlreadyExistException.class)
    private ResponseEntity<ErrorDto> handleUserAlreadyExistException(UserAlreadyExistException ex) {
    	  ErrorDto error = new ErrorDto(HttpStatus.CONFLICT, "User Already Exist", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
    
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ErrorDto error = new ErrorDto(status, "Method Not Supported", ex.getMessage());

        return new ResponseEntity<>(error, status);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	
    	ErrorDto error = new ErrorDto(status, "Content-Type Not Supported", ex.getMessage());

        return new ResponseEntity<>(error, status);
    }
    
    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<ErrorDto> handleNullPointerException(NullPointerException ex) {
    	  
    	  ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorDto> handleIllegalArgumentException(IllegalArgumentException ex) {
    	  
    	  ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST, "Argument not Correct", ex.getMessage());

          return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
