package softuni.exam.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    // @Size(min = 2)
    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    // @Positive
    private BigDecimal price;

    @Column(name = "take_off")
    private LocalDateTime takeoff;


    @ManyToOne
    @JoinColumn(name = "from_town_id")
    private Town fromTown;
    @ManyToOne
    @JoinColumn(name = "to_town_id")
    private Town toTown;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    public Ticket() {}

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(LocalDateTime takeoff) {
        this.takeoff = takeoff;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }


    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Town getToTown() {
        return toTown;
    }

    public void setToTown(Town toTown) {
        this.toTown = toTown;
    }

    public Town getFromTown() {
        return fromTown;
    }

    public void setFromTown(Town fromTown) {
        this.fromTown = fromTown;
    }
    //takeoff –  а date and time of plane taking off.
    //Note: Tickets have two foreign keys to Town, because of fromTown(Town) and toTown(Town)
    //Note2: Tickets have relations with Towns, Passengers and Planes.
}
