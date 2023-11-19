package com.resellerapp.model.service;

import com.resellerapp.model.entity.ConditionNameEnum;
import com.resellerapp.model.entity.User;

import java.math.BigDecimal;

public class OfferServiceModel {
    private Long id;
    private String description;
    private BigDecimal price;
    private ConditionNameEnum condition;

    private User seller;
    public OfferServiceModel() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionNameEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionNameEnum condition) {
        this.condition = condition;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
