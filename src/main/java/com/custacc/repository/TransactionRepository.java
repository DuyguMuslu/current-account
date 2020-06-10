package com.custacc.repository;

import com.custacc.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Transaction findByAccountID(Long accountID);

}