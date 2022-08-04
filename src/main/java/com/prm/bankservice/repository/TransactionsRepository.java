package com.prm.bankservice.repository;

import com.prm.bankservice.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
}
 