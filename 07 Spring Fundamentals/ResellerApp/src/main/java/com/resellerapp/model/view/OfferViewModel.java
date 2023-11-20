package com.resellerapp.model.view;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.User;

import java.math.BigDecimal;

public class OfferViewModel {

    private Long id;

    private User seller;

    private User buyer;
    private String description;
    private Condition condition;
    private BigDecimal price;

    public OfferViewModel() {}

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}
