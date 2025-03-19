package com.spring.SpringAndSpringBoot;

import com.spring.SpringAndSpringBoot.beans.PersonComponentAutowired;
import com.spring.SpringAndSpringBoot.beans.Vehicle;
import com.spring.SpringAndSpringBoot.beans.VehicleComponent;
import com.spring.SpringAndSpringBoot.config.ComponentScanConfig;
import com.spring.SpringAndSpringBoot.config.ProjectConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*SPRING CORE */

/*Ioc- Inversion of control -says that never ever create objects by ur own instead define or specify
 * the ways to create objects to an external entity
 * that is reversing the control of object creation from developer to and external entity*/

/*Dependency Injection - is used for dependency management , it helps to inject required dependency from
 * external instead creating , DI is patter through which IOC is achieved */

	/*Bean - It is POJO Class or a java class that is created , assembled and managed by spring IOC container
		is called Bean */

	/*IoC Container - it is container used for creating objects/ Spring bean , assemble the required dependency,
		configuration and manage the entire lifecycle of the Spring Bean/Objects */

	/*Context - it is a memory location of the web application in which all the objects are added that
		 we want our framework to manage else spring will not recognise those objects

		 we have two interfaces  of context 1) BeanFactory
		 									2)Application Context*/

/*SpEl*/

/*Note: To create bean and add it context we need to add Spring context Dependency in pom Xml*/

//another way of creating bean
/*@Component on top of pojo class is the stereotype annotation which is used to create and add beans into Spring context with less
* code  */
//@ComponentScan - helps or directs spring about the stereotype annotation

/* @Autowired annotation marks on the filed, constructor , setter method is used to auto wire the beans that is
* injecting the beans at run time by spring dependency injection mechanism*/
//@Autowired is used to inject dependency automatically


/*How to handle Csrf properly instead of disabling it? no answer yet  */

/*----------------------------------------------Spring boot ----------------------------------------------------------*/

/*@RestController// @Controller +@ResponseBody where @ResponseBody says response should be in json format not in
    spring mvc format i.e html or any other ui format*/
/*ResponseEntity<> - allows developer to send response body, statusand headers on the http response */


@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Api Document information ",
				description = "Accounts microservice REST Api Documentation",
				version = "v1",
				contact = @Contact(
						name = "Akhil",
						email = "akhilkhyadad1357@gmail.com"
				)
							))//open api swagger is used for documentation
public class SpringAndSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAndSpringBootApplication.class, args);

		Vehicle vehicle= new Vehicle();
		vehicle.setName("Honda City");
		System.out.println("Vehicle name from non Spring-Context is: "+vehicle.getName());


		/*Context - it is a memory location of the web application in which all the objects are added that
		 we want our framework to manage else spring will not recognise those objects  */
		//As Application context is interface we need to implementation classes one of those are  AnnotationConfigApplicationContext
		//here object of the class projectConfig will be assigned to  context reference variable
		/* this is the line responsible to initilize the spring ioc container or spring context
		* during the initilization we are telling spring framework please consider all the configuration
		* inside projetconfig class */
		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

		Vehicle veh=context.getBean(Vehicle.class);

		System.out.println("Vehicle name from  Spring-Context is: "+veh.getName());


		//another way of creating bean
		/*@Component on top of pojo class is the stereotype annotation which is used to create and add beans into Spring context with less
		 * code  */
//@ComponentScan - helps or directs spring about the stereotype annotation
		var componentContext= new  AnnotationConfigApplicationContext(ComponentScanConfig.class);

		PersonComponentAutowired person= componentContext.getBean(PersonComponentAutowired.class);
		VehicleComponent vehicleComponent= componentContext.getBean(VehicleComponent.class);

		System.out.println("component Vehicle name from  Spring -Context is: "+vehicleComponent.getName());

		System.out.println("component person name from  Spring -Context is: "+person.getName());

		System.out.println("Autowired Vehicle that person owns: "+person.getVehicleComponent().getName());
		vehicleComponent.printHello();

	}



}
