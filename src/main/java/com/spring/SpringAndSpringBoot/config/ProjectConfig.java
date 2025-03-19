package com.spring.SpringAndSpringBoot.config;


import com.spring.SpringAndSpringBoot.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration /*this will let my ioc container scan All the content of the this class because
 this is and indication to the sprig framework that the developer has done some changes which will result into
  creating beans */
public class ProjectConfig {


    @Bean
    Vehicle vehicle(){
        var veh=new Vehicle();
        veh.setName("Audi8");
        return veh;
    }
}
