package com.savenkoff.study.task5.entities;

public class Product {
    private Long id;
    private String accNum;
    private Float balance;
    private TypeProduct typeProduct;
    private User ownerId;

    public Product(String accNum, Float balance, TypeProduct typeProduct, User ownerId) {
        this.accNum = accNum;
        this.balance = balance;
        this.typeProduct = typeProduct;
        this.ownerId = ownerId;
    }

    public Product(Long id, String accNum, Float balance, TypeProduct typeProduct, User ownerId) {
        this.id = id;
        this.accNum = accNum;
        this.balance = balance;
        this.typeProduct = typeProduct;
        this.ownerId = ownerId;
    }

    public User getOwnerId() {
        return ownerId;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accNum='" + accNum + '\'' +
                ", balance=" + balance +
                ", typeProduct=" + typeProduct +
                ", ownerId=" + ownerId +
                '}';
    }
}