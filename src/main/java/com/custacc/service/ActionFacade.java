package com.custacc.service;

import com.custacc.dto.AccountDto;
import com.custacc.dto.CustomerDto;
import com.custacc.dto.TransactionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.*;

@Service
@Slf4j
public class ActionFacade {

    final CustomerService customerService;
    final AccountService accountService;
    final TransactionService transactionService;

    public ActionFacade(CustomerService customerService,
                        AccountService accountService,
                        TransactionService transactionService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Transactional
    public Map<String, Object> initializeData() {
        CustomerDto customerDto = customerService.createCustomer(new CustomerDto("Duygu", "Muslu"));
        AccountDto accountDto = accountService.createAccount(new AccountDto(100L, customerDto));
        transactionService.saveTransaction(new TransactionDto(accountDto.getBalance(), accountDto.getAccountID()));
        customerDto = customerService.createCustomer(new CustomerDto("Johan", "Kemper"));
        accountDto = accountService.createAccount(new AccountDto(150L, customerDto));
        transactionService.saveTransaction(new TransactionDto(accountDto.getBalance(), accountDto.getAccountID()));
        customerService.createCustomer(new CustomerDto("Lorenzo", "Murrocu"));
        Map<String, Object> model = new HashMap<>();
        model.put("userMap", customerService.retrieveAllCustomers());
        return model;
    }

    @Transactional
    public String createAccount(Long customerID, Long balance) throws Exception {
        CustomerDto customerDto = customerService.findByCustomerID(customerID);
        Optional.ofNullable(customerDto).orElseThrow(() -> new Exception("CustomerID not found!" + customerID));
        String response = "";
        try {
            AccountDto accountDto = new AccountDto(balance, customerDto);
            accountDto = accountService.createAccount(accountDto);
            if (accountDto.getBalance() > 0) {
                TransactionDto transactionDto = new TransactionDto(accountDto.getBalance(), accountDto.getAccountID());
                transactionService.saveTransaction(transactionDto);
            }
            response = "Account: " + accountDto.getAccountID() + "  created with " + accountDto.getBalance() + " of balance amount";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return response;
    }

    public LinkedHashMap<String, Object> getCustomerDetails(Long customerID) throws Exception {
        CustomerDto customerDto = customerService.findByCustomerID(customerID);
        Optional.ofNullable(customerDto).orElseThrow(() -> new Exception("CustomerID not found!" + customerID));
        return getCustomerTransactions(customerDto);
    }

    @Transactional
    public LinkedHashMap<String, Object> getCustomerTransactions(CustomerDto customerDto) {
        LinkedHashMap<String, Object> model = new LinkedHashMap<>();
        LinkedHashMap<String, Object> transactions = new LinkedHashMap<>();
        long totalBalance = 0;
        model.put("customerID", customerDto.getCustomerID());
        model.put("name", customerDto.getName());
        model.put("surname", customerDto.getSurname());
        List<AccountDto> accountList = accountService.getCustomerAccountList(customerDto);
        for (AccountDto accountDto : accountList) {
            totalBalance += accountDto.getBalance();
            TransactionDto transactionDto = transactionService.getAccountTransaction(accountDto);
            Optional.ofNullable(transactionDto).map(i -> model.put("AccountID:" + accountDto.getAccountID().toString(), i.getAmount()));
        }
        model.put("balance", totalBalance);
        return model;
    }


    public Model listCustomers(Model model) {
        List<CustomerDto> customers = customerService.retrieveAllCustomers();
        model.addAttribute("customers", customers);
        return model;
    }

    public Model listAccounts(Model model, Long customerID) {
        CustomerDto customerDto = customerService.findByCustomerID(customerID);
        Optional.ofNullable(customerDto).map(i -> model.addAttribute("accounts", accountService.getCustomerAccountList(i)));
        return model;
    }

    public Model getTransaction(Model model, Long accountId) {
        AccountDto accountDto = accountService.findByAccountID(accountId);
        Optional.ofNullable(accountDto).map(i -> model.addAttribute("transactions", transactionService.getAccountTransaction(i)));
        return model;
    }
}
