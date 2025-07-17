package com.departmentService.ExceptionHandler;

import com.departmentService.CustomExceptions.AlreadyExistsOrNotException;
import com.departmentService.CustomExceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsOrNotException.class)
    public ResponseEntity<String> handleDepartmentException(AlreadyExistsOrNotException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedRequest(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericException(RuntimeException ex) {
        StringBuilder message = new StringBuilder( "Something went wrong: " + ex.getMessage() );

        Throwable cause = ex.getCause();
        if ( cause != null ) {
            message.append( " | Cause: " + cause.getMessage() );
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.toString());
    }

}
