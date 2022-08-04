package com.prm.bankservice.repository;
import com.prm.bankservice.domain.CurrentCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CurrentCustomerRepository  extends  JpaRepository <CurrentCustomer,Integer>{
    @Query( value = "SELECT * FROM current_customer  ci ",
            nativeQuery = true)
    public CurrentCustomer getCurrentCustomer();



}
