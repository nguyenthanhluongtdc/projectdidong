package com.example.project.Model;

public class Product {
    private byte [] imgProduct;
    private String codeProduct;
    private String nameProduct;
    private String origin;
    private float price;

    public Product() {

    }

    public Product( String codeProduct,byte [] imgProduct, String nameProduct, String origin, float price) {
        this.imgProduct = imgProduct;
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.origin = origin;
        this.price = price;
    }

    public byte [] getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(byte [] imgProduct) {
        this.imgProduct = imgProduct;
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
