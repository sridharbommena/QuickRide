package com.quickride.exception;

import com.quickride.dto.ExceptionResponse;
import com.quickride.external.exception.DriverNotFoundException;
import com.quickride.external.exception.ServiceUnavailableException;
import com.quickride.external.exception.TripNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ExceptionResponse> driverNotFoundException(DriverNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setError("Driver not found");
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ExceptionResponse> tripNotFoundException(TripNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setError("Trip not found");
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ExceptionResponse> serviceUnavailableException(ServiceUnavailableException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setTimestamp(LocalDateTime.now());
        exceptionResponse.setError("Driver Service unavailable");
        exceptionResponse.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        exceptionResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
