package ru.avv.unikoomapp.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NoResultException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleFileUploadException(HttpServletRequest request, NoResultException ex) {
        ErrorResponse response = new ErrorResponse("Empty Result", "No entity found for query.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNumberFormatException(HttpServletRequest request, NumberFormatException ex) {
        ErrorResponse response = new ErrorResponse("Number Format Error", "Number Format Error " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleInvalidFormatException(HttpServletRequest request, InvalidFormatException ex) {
        ErrorResponse response = new ErrorResponse("Invalid Format", "Invalid Format " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
        ErrorResponse response = new ErrorResponse("Illegal Argument", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, NullPointerException ex) {
        ErrorResponse response = new ErrorResponse("Something is Null", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        ErrorResponse response = new ErrorResponse("Something that doesn't look like valid JSON.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, DataIntegrityViolationException ex) {
        ErrorResponse response = new ErrorResponse("Inconsistent data.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, MaxUploadSizeExceededException ex) {
        ErrorResponse response = new ErrorResponse("File size too big.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MissingPathVariableException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, MissingPathVariableException ex) {
        ErrorResponse response = new ErrorResponse("Missing Path Variable.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, FileNotFoundException ex) {
        ErrorResponse response = new ErrorResponse("File Not Found.", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
