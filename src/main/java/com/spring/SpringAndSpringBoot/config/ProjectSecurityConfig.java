//package com.spring.SpringAndSpringBoot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ProjectSecurityConfig {
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        //default security
////        http.authorizeHttpRequests().anyRequest().authenticated();
////        http.formLogin();
////        http.httpBasic();
//
//        // Permit All Requests inside the Web Application
////        http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
////                .formLogin(Customizer.withDefaults())
////                .httpBasic(Customizer.withDefaults());
//
//        // Deny All Requests inside the Web Application
////            http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
////                .formLogin(Customizer.withDefaults())
////                .httpBasic(Customizer.withDefaults());
//
//        //custom security
//
//        /*How to handle Csrf properly instead of disabling it */
//
////        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((request)->request
////                .requestMatchers("/home").authenticated()
////                        .requestMatchers("/hello").permitAll())
////                .formLogin(Customizer.withDefaults())
////                .httpBasic(Customizer.withDefaults());
//
//        http.csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((requests) -> requests.requestMatchers("/home").authenticated()
//                        .requestMatchers("/hello").permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//
//
//
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("12345")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("54321")
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//}
