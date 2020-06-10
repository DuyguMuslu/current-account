package com.custacc.dto;


public class CustomerDto {
    private Long customerID;
    private String name;
    private String surname;

    public CustomerDto() {
    }

    public CustomerDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public CustomerDto(Long customerID, String name, String surname) {
        this.customerID = customerID;
        this.name = name;
        this.surname = surname;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
