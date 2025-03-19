package com.spring.SpringAndSpringBoot.exception;


import com.spring.SpringAndSpringBoot.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*Using this annotation we are telling spring boot that whenever exception happens in any of my controller please
* invoke method i am writing here */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) /*// by giving this my spring boot will know that this method has to be invoked whenever there
    is Runtime  exception and exception  is being thrown
    */
    public ResponseEntity<ErrorResponseDto> handleRGlobalException(Exception exception,
                                                                            WebRequest webRequest){

        /*We need to pass WebRequest we have mentioned api path filed So*/

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),// by giving false we will receive api path
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now());


        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class) /*// by giving this my spring boot will know that this method has to be invoked whenever there
    is and exception CustomerAlreadyExistsException is being thrown
    */
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                                 WebRequest webRequest){

        /*We need to pass WebRequest we have mentioned api path filed So*/

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),// by giving false we will receive api path
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now());


        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class) /*// by giving this my spring boot will know that this method has to be invoked whenever there
    is and exception CustomerAlreadyExistsException is being thrown
    */
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest){

        /*We need to pass WebRequest we have mentioned api path filed So*/

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),// by giving false we will receive api path
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now());


        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }
}
