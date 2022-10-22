package softuni.exam.models.entities;

import softuni.exam.models.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Plane extends BaseEntity {

    //@Size(min = 5)
    @Column(name = "register_number", nullable = false, unique = true)
    private String registerNumber;

    // @Positive
    private int capacity;

    // @Size(min = 2)
    @Column(nullable = false)
    private String airline;

    public Plane() {}

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
