package com.spring.SpringAndSpringBoot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    //create constructor which excepts String as input
    public ResourceNotFoundException(String resourceName,String filedName,String filedValue){
        super(String.format("%s Not Found with given  input data %s: '%s'",resourceName,filedName,filedValue));/*// since we are Extending another class i.e RuntimeException we need to make sure to invoke the
        parent constructor also and
        passing the value in super class that is RuntimeException*/
    }
}
