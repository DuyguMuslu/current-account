package com.custacc.service;

import com.custacc.dto.AccountDto;
import com.custacc.dto.CustomerDto;
import com.custacc.model.Account;
import com.custacc.model.Customer;
import com.custacc.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    AccountRepository repository;
    ModelMapper modelMapper;


    @Autowired
    public AccountService(AccountRepository repository,
                          ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public AccountDto createAccount(AccountDto accountDto) {
        return convertToDto(repository.save(convertToEntity(accountDto)));
    }

    public List<AccountDto> getCustomerAccountList(CustomerDto customerDto) {
        return repository.findByCustomer(modelMapper.map(customerDto, Customer.class)).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AccountDto findByAccountID(Long accountID) {
        return convertToDto(repository.findByAccountID(accountID));
    }

    private AccountDto convertToDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private Account convertToEntity(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

}
