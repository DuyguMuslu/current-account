package com.custacc.repository;

import com.custacc.model.Account;
import com.custacc.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountID(Long accountID);

    List<Account> findByCustomer(Customer customer);


}
