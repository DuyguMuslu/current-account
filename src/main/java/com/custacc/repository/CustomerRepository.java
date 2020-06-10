package com.custacc.repository;


import com.custacc.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findByCustomerID(Long customerID);

}
