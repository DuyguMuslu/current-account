package com.custacc.service;

import com.custacc.dto.CustomerDto;
import com.custacc.model.Customer;
import com.custacc.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    CustomerRepository repository;
    ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository repository,
                           ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        return convertToDto(repository.save(convertToEntity(customerDto)));
    }

    public List<CustomerDto> retrieveAllCustomers() {
        return repository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CustomerDto findByCustomerID(Long customerID) {
        CustomerDto customerDto=null;
        Optional<CustomerDto> customerDtoOptional = Optional.ofNullable(repository.findByCustomerID(customerID)).map(this::convertToDto);
        if(customerDtoOptional.isPresent()) customerDto=customerDtoOptional.get();
        return customerDto;
    }

    private CustomerDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

}
