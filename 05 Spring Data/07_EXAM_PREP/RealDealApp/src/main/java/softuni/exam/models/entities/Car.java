package softuni.exam.models.entities;

import softuni.exam.models.entities.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String make;

    private String model;


    private int kilometers;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)
    private Set<Picture> pictures;


    public Car() {}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {


        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Car make - %s, model - %s%n", this.getMake(), this.getModel()));
        builder.append(String.format("Kilometers - %d%n", this.getKilometers()));
        builder.append(String.format("Registered on - %s%n", this.getRegisteredOn()));
        builder.append(String.format("Number of pictures - %d%n", this.getPictures().size()));

        return builder.toString();
    }
}
