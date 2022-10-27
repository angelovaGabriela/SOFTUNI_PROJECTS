package softuni.exam.models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture  extends BaseEntity {

    // @Size(min = 3, max = 19)
    @Column(unique = true)
    private String name;

    @Column(name = "date_and_time")
    private LocalTime dateAndTime;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToMany(mappedBy = "pictures")
    private Set<Offer> offers = new HashSet<>();

    public Picture() {}


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
