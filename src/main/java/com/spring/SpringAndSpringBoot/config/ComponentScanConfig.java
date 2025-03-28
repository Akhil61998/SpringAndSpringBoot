package com.spring.SpringAndSpringBoot.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.SpringAndSpringBoot.beans")
public class ComponentScanConfig {
}
