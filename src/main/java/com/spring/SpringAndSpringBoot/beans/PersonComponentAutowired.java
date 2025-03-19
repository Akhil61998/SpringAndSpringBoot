package com.spring.SpringAndSpringBoot.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonComponentAutowired {

    private String name="luicy";



    /* @Autowired annotation marks on the filed, constructor , setter method is used to auto wire the beans that is
     * injecting the beans at run time by spring dependency injection mechanism*/
//@Autowired is used to inject dependency automatically
    @Autowired
    private VehicleComponent vehicleComponent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleComponent getVehicleComponent() {
        return vehicleComponent;
    }

    public void setVehicleComponent(VehicleComponent vehicleComponent) {
        this.vehicleComponent = vehicleComponent;
    }
}
