package com.custacc.dto;


public class AccountDto {
    private Long accountID;
    private Long balance;
    private CustomerDto customerDto;


    public AccountDto() {
    }

    public AccountDto(Long balance, CustomerDto customerDto) {
        this.balance = balance;
        this.customerDto = customerDto;
    }

    public AccountDto(Long accountID, Long balance, CustomerDto customerDto) {
        this.accountID = accountID;
        this.balance = balance;
        this.customerDto = customerDto;
    }

    public AccountDto(Long balance) {
        this.balance = balance;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto CustomerDto) {
        this.customerDto = customerDto;
    }
}
