package com.TelevisionRemote.SmartTelevisionRemote.exceptions.userExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> exception(UserAlreadyExistsException exception) {
        return new ResponseEntity<>("User already exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotExistsException.class)
    public ResponseEntity<Object> exception(UserNotExistsException exception) {
        return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
    }
}
