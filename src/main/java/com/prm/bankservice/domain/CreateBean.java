package com.prm.bankservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBean {
    @Bean
    public CustomerInfo getCustomerInfo(){
        return  new CustomerInfo();
    }
    @Bean
    public Transactions getTransaction(){
        return new Transactions();
    }
    @Bean
    public CurrentCustomer getCurrentCustomer(){ return  new CurrentCustomer();}
}