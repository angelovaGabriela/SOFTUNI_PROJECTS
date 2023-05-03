package com.plannerapp.service;

import com.plannerapp.model.entity.Priority;
import com.plannerapp.model.entity.PriorityNameEnum;

public interface PriorityService {

    void initPriorities();

    Priority findByPriorityNameEnum(PriorityNameEnum priority);
}
