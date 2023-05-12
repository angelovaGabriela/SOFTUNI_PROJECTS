package com.plannerapp.service;

public interface HomeService {
    void assignTask(Long taskID, Long userID);


    void deleteTask(Long taskID, Long userID);
}
