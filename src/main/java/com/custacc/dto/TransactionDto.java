package com.custacc.dto;


public class TransactionDto {

    private Long transactionID;
    private Long amount;
    private Long accountID;

    public TransactionDto() {
    }

    public TransactionDto(Long amount, Long accountID) {
        this.amount = amount;
        this.accountID = accountID;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }
}
