package com.springexercise.hybernateexample.entities;

import javax.persistence.*;

public class Sale {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private Integer id;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    //costructs
    public Sale(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
    public Sale() {
    }

    //getters
    public Integer getQuantity() {
        return quantity;
    }
    public Product getProduct() {
        return product;
    }

    //setters
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
