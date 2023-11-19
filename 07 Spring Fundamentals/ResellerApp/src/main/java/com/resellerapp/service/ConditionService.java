package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.ConditionNameEnum;

public interface ConditionService {
    void initCondition();

    Condition findByConditionName(ConditionNameEnum condition);

}
