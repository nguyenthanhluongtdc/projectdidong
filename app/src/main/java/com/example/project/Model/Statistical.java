package com.example.project.Model;

public class Statistical {
    private String codeBill;
    private int number;

    public Statistical(String codeBill, int number) {
        this.codeBill = codeBill;
        this.number = number;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Statistical() {
    }
}
