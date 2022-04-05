package softuni.exam.models.entity;

import javax.persistence.Enumerated;


public enum ApartmentType {


    TWO("two_rooms"),
    THREE("three_rooms"),
    FOUR("four_rooms");

    String value;

    ApartmentType(String value) {
        this.value = value;
    }
}
