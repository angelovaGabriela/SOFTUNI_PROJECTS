package softuni.exam.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture  extends BaseEntity {


    @Column(unique = true)
    private String name;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

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

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return name.equals(picture.name) && Objects.equals(dateAndTime, picture.dateAndTime) && Objects.equals(car, picture.car) && Objects.equals(offers, picture.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateAndTime, car, offers);
    }
}
