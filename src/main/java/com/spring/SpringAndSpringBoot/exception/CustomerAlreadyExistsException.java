package com.spring.SpringAndSpringBoot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException{

    //create constructor which excepts String as input
    public CustomerAlreadyExistsException(String messsage){
        super(messsage);/*// since we are Extending another class i.e RuntimeException we need to make sure to invoke the
        parent constructor also and
        passing the value in super class that is RuntimeException*/
    }
}
