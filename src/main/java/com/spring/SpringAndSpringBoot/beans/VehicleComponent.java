package com.spring.SpringAndSpringBoot.beans;


import org.springframework.stereotype.Component;

@Component
public class VehicleComponent {

    private String name="Toyota";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printHello(){
        System.out.println("Print hello from Component vehicle Bean ");
    }
}
