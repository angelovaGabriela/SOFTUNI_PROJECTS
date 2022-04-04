package com.example.demo;

import com.example.demo.exceptions.UserNotLoggedInException;
import com.example.demo.exceptions.ValidationException;
import com.example.demo.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        String result;
        try {
           result = executorService.execute(command);
        } catch (ValidationException | UserNotLoggedInException ex) { // RunTime exception - потребителя ми е подал невалидна информация
            result = ex.getMessage();
        }

        System.out.println(result);
    }
}
