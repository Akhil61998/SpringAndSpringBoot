package com.spring.SpringAndSpringBoot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "customer") // use this whenever class name and table name db are different, we can use even if its same

@Getter
@Setter
@ToString
@AllArgsConstructor//lombok will generate constructor which accept all the arguments
@NoArgsConstructor// generate empty constructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    private String name ;

    private String Email;

    private String mobileNumber;
}
