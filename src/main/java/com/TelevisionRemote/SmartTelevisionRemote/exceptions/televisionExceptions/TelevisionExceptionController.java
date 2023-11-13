package com.TelevisionRemote.SmartTelevisionRemote.exceptions.televisionExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TelevisionExceptionController {

    @ExceptionHandler(value = TelevisionNotFoundException.class)
    public ResponseEntity<Object> exception(TelevisionNotFoundException exception) {
        return new ResponseEntity<>("Television not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TelevisionNameExistsException.class)
    public ResponseEntity<Object> exception(TelevisionNameExistsException exception) {
        return new ResponseEntity<>("Television already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TelevisionIpExistsException.class)
    public ResponseEntity<Object> exception(TelevisionIpExistsException exception) {
        return new ResponseEntity<>("Television ip already exists", HttpStatus.BAD_REQUEST);
    }
}
