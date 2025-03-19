package com.spring.SpringAndSpringBoot.repository;

import com.spring.SpringAndSpringBoot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    /*As jpa repository provide some query method only input and primary key so if you want to define query other than
    * primary key then follow the below way*/


    //Abstract methods
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
