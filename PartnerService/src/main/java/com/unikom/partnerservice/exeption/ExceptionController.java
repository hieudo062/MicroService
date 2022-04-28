package com.unikom.partnerservice.exeption;

import com.unikom.partnerservice.dto.response.FailedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> nullPointerE(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "999", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> idNotFound(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "808", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleBindException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new FailedResponse(0, "777",
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

}
