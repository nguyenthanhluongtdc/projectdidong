package com.example.project.Model;

public class Bill {
    private String codeBill;
    private String codeCustomer;
    private String DateBill;
    private float totalMoney;

    public Bill() {
    }

    public Bill(String codeBill, String codeCustomer, String dateBill, float totalMoney) {
        this.codeBill = codeBill;
        this.codeCustomer = codeCustomer;
        DateBill = dateBill;
        this.totalMoney = totalMoney;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }

    public String getCodeCustomer() {
        return codeCustomer;
    }

    public void setCodeCustomer(String codeCustomer) {
        this.codeCustomer = codeCustomer;
    }

    public String getDateBill() {
        return DateBill;
    }

    public void setDateBill(String dateBill) {
        DateBill = dateBill;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
