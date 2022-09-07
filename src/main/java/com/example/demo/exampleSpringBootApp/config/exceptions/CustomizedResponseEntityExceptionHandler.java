package com.example.demo.exampleSpringBootApp.config.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//defining exception handling for all the exceptions
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /* Controlamos todas las excepciones de forma generica */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        body.put("exception", ex.getClass().getSimpleName());

        body.put("message", ex.getMessage());
        return handleExceptionInternal(ex,body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", new Date());
            body.put("status", status);
            body.put("exception", ex.getClass().getSimpleName());

            body.put("message", ex.getMessage());
            return handleExceptionInternal(ex, body, headers, status, request);
        }
//        if (cause instanceof JsonParseException) {
//            return onParseException((JsonParseException) cause);
//        } else if (cause instanceof JsonMappingException) {
//            return onMappingException((JsonMappingException) cause);
//        } else {
//             ...
//        }
        return handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("exception", ex.getClass().getSimpleName());

        List<FieldError> errors = ex.getBindingResult()
                .getFieldErrors();

        body.put("errors", errors);

        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request)
    {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("exception", ex.getClass().getSimpleName());

        Map<String, Object> violations = new LinkedHashMap<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            violations.put(
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            );
        }

        body.put("violations", violations);

        return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request)
    {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("exception", ex.getClass().getSimpleName());

        Map<String, Object> violations = new LinkedHashMap<>();
        violations.put(
                ex.getName(),
                ex.getName() + " invalido: " + ex.getValue());

        body.put("violations", violations);

        return this.handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request)
    {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("exception", ex.getClass().getSimpleName());

        body.put("message", ex.getMessage());

        return handleExceptionInternal(ex,body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request)
    {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.NOT_FOUND);
        body.put("exception", ex.getClass().getSimpleName());

        body.put("message", ex.getMessage());

        return handleExceptionInternal(ex,body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

//    @ExceptionHandler({InvalidFormatException.class,})
//    public final ResponseEntity<Object> handleInvalidFormatException(Exception ex, WebRequest request)
//    {
//        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//        return handleExceptionInternal(ex,exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }


//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public final ResponseEntity<Object> handleEntityHttpMessageNotReadableException(Exception ex, WebRequest request)
//    {
//        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//        return handleExceptionInternal(ex,exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }



//    @ExceptionHandler(UserNotFoundException.class)
//    // override method of ResponseEntityExceptionHandler class
//    public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request)
//    {
//        // creating exception response structure
//        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
//        // returning exception structure and Not Found status
//        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
//    }



}
