package com.example.project.Model;

public class Customer {
    private String codeCustomer;
    private String nameCustomer;
    private String addRess;
    private String numberPhone;

    public Customer(String codeCustomer, String nameCustomer, String addRess, String numberPhone) {
        this.codeCustomer = codeCustomer;
        this.nameCustomer = nameCustomer;
        this.addRess = addRess;
        this.numberPhone = numberPhone;
    }

    public Customer() {
    }

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddRess() {
        return addRess;
    }

    public void setAddRess(String addRess) {
        this.addRess = addRess;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
