package com.example.xml_processing_exercise.productshop.entities.users;


import java.util.List;

public class ExportSellersWithCountsDTO {

    private int usersCount;

    private List<ExportUserWithSoldCountDTO> users;

    public ExportSellersWithCountsDTO(
            List<ExportUserWithSoldCountDTO> users) {

        this.users = users;

        this.usersCount = users.size();
    }
}
