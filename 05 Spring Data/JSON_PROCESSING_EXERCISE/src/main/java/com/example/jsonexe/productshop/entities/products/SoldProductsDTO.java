package com.example.jsonexe.productshop.entities.products;

import java.math.BigDecimal;

public class SoldProductsDTO {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String getBuyerLastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getGetBuyerLastName() {
        return getBuyerLastName;
    }

    public void setGetBuyerLastName(String getBuyerLastName) {
        this.getBuyerLastName = getBuyerLastName;
    }
}
