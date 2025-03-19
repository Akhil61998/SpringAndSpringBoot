package com.spring.SpringAndSpringBoot.dto;


import lombok.Data;

@Data
public class CustomerDto {

    private String name ;

    private String Email;

    private String mobileNumber;

    private AccountsDto accountsDto;
}
