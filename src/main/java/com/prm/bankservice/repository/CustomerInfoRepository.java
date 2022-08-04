package com.prm.bankservice.repository;

import com.prm.bankservice.domain.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends JpaRepository <CustomerInfo,Integer>{


    @Query( value = "SELECT * FROM customer_info ci WHERE ci.cust_crd_id = ?1",
            nativeQuery = true)
    public CustomerInfo findCustomerInfoByNative(int custCrdId);


    @Query (value="select * from customer_info  where cus_seq=?1", nativeQuery=true)
    public CustomerInfo findCustomerInfoById(int custId);


}


