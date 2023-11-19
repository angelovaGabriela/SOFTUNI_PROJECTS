package com.resellerapp.model.binding;

import com.resellerapp.model.entity.ConditionNameEnum;
import com.resellerapp.model.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferAddBindingModel {

    @NotNull
    @Size(min = 2, max = 50)
    private String description;
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    private ConditionNameEnum condition;


    private User seller;

    public OfferAddBindingModel() {}

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
}
