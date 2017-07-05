package de.beinlich.markus.pizzaservice.model;

import de.beinlich.markus.pizzaservice.dao.*;

public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String town;
    private String postcode;

    public Customer() {
    }
    
    public void store() {
        DaoCustomer daoCustomer = new DaoCustomer();
        this.customerId = daoCustomer.store(this);
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

        public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
}
