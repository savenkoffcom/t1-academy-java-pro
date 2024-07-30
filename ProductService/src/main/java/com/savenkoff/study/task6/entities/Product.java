package com.savenkoff.study.task6.entities;

public class Product {
    private Long id;
    private String accNum;
    private Float balance;
    private TypeProduct typeProduct;
    private User owner;

    public Product(String accNum, Float balance, TypeProduct typeProduct, User owner) {
        this.accNum = accNum;
        this.balance = balance;
        this.typeProduct = typeProduct;
        this.owner = owner;
    }

    public Product(Long id, String accNum, Float balance, TypeProduct typeProduct, User owner) {
        this.id = id;
        this.accNum = accNum;
        this.balance = balance;
        this.typeProduct = typeProduct;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public String getAccNum() {
        return accNum;
    }

    public Float getBalance() {
        return balance;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public Long getId() {
        return id;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accNum='" + accNum + '\'' +
                ", balance=" + balance +
                ", typeProduct=" + typeProduct +
                ", owner=" + owner +
                '}';
    }
}