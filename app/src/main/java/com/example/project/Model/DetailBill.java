package com.example.project.Model;

public class DetailBill {
    private String codeBill;
    private String codeProduct;
    private String nameProduct;
    private int amount;
    private float price;
    private  float totalMoney;

    public DetailBill() {
    }

    public DetailBill(String codeBill, String codeProduct, String nameProduct, int amount, float price, float totalMoney) {
        this.codeBill = codeBill;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.price = price;
        this.totalMoney = totalMoney;
    }

    public String getCodeBill() {
        return codeBill;
    }

    public void setCodeBill(String codeBill) {
        this.codeBill = codeBill;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
