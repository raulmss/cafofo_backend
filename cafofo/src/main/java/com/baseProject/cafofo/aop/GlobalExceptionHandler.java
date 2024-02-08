package com.baseProject.cafofo.aop;

import com.baseProject.cafofo.exceptions.*;
import com.baseProject.cafofo.repo.OfferRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CafofoApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequestException(CafofoApplicationException e){
        return new ResponseEntity<>(new ErrorType(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OwnerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleOwnerException(OwnerException e){
        return new ResponseEntity<>(new ErrorType(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleCustomerException(CustomerException e){
        return new ResponseEntity<>(new ErrorType(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OfferException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleOfferException(OfferException e){
        return new ResponseEntity<>(new ErrorType(e.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlePropertyException(PropertyException e){
        return new ResponseEntity<>(new ErrorType(e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
