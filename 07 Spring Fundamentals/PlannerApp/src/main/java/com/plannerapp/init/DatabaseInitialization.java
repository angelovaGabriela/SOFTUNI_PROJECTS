package com.plannerapp.init;

import com.plannerapp.service.PriorityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitialization implements CommandLineRunner {

    private final PriorityService priorityService;

    public DatabaseInitialization(PriorityService priorityService) {
        this.priorityService = priorityService;
    }


    @Override
    public void run(String... args) throws Exception {

        priorityService.initPriorities();

    }
}
