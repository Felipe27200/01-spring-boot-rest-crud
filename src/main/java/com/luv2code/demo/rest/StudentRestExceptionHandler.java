package com.luv2code.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
* +-------------------------------------+
* | GLOBAL EXCEPTION HANDLING COMPONENT |
* +-------------------------------------+
*
* This annotation make this class able to
* handle exception across the whole app.
* */
@ControllerAdvice
public class StudentRestExceptionHandler
{
    // Add an Exception Handler using @ExceptionHandler, this mark the method like that
    @ExceptionHandler
    /*
     * We have to define an object with the structure of the error response, in this case
     * a POJO student error with the needle fields.
     *
     * In the list parameters also we need catch the type of the error or the nature of it.
     * It is to know what type of exception this method can handle.
     * */
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc)
    {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // The first argument is the body of the response and
        // the second is the status of it.
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add other handler exception to catch every any kind of exception (catch all).
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException (Exception exc) // This handle any type of exception, due to object type
    {
        // Create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        // This status referer to the 404
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage()); // We can customize this code for showing a different error message
        error.setTimestamp(System.currentTimeMillis());

        // The first argument is the body of the response and
        // the second is the status of it.
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
